package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import com.google.gson.Gson;

import login.Jugador;
import login.UsuariosDB;
import util.ClienteConn;
import util.Mensaje;

public class Servidor {

	private Gson gson;

	private ServerSocket server;

	private Collection<ClienteConn> clientes;

	private Lobby lobby;

	private UsuariosDB db;

	public Servidor(int puerto) {

		this.clientes = new ArrayList<>();

		this.gson = new Gson();

		this.lobby = new Lobby();
		 
		this.db = new UsuariosDB();
		
		try {
			
			
			this.server = new ServerSocket(puerto);

			System.out.println("SERVER INICIADO - Esperando conexiones de clientes ...");
/*
			HiloTestClientes h = new HiloTestClientes(this);
			h.start();
*/
			HiloAceptarClientes ha = new HiloAceptarClientes(this);

			ha.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void interpretarMensaje(Mensaje msg, ClienteConn conn) throws IOException {

		String tipo = msg.getNombreMensaje();

		Mensaje res;
		
		

		switch (tipo) {

		case "Loguearse":

			String user[] = gson.fromJson(msg.getJson(), String.class).split("-");

			res = this.loguear(user[0], user[1]);

			conn.setUsuario(user[0]);

			conn.enviarInfo(res);

			break;

		case "Registrarse":

			String userReg[] = new String[2];

			userReg = gson.fromJson(msg.getJson(), String.class).split("-");
						
			res = this.registrar(userReg[0], userReg[1]);
			
			conn.setUsuario(userReg[0]);
            
			conn.enviarInfo(res);

			break;

		case "ObtenerSalas":

			Collection<String> salas = this.lobby.obtenerSalas();

			res = new Mensaje("Salas", this.gson.toJson(salas));

			conn.enviarInfo(res);

			break;

		case "UnirseALobby":

			this.lobby.agregarCliente(conn);

			this.quitarCliente(conn);

			break;

		case "SalirDeLobby":

			this.lobby.quitarCliente(conn);

			break;

		case "UnirseASala":

			Integer idSala = this.gson.fromJson(msg.getJson(), Integer.class);

			conn.enviarInfo(new Mensaje("UnirseASalaOk", gson.toJson(idSala)));

			this.lobby.agregarJugadorASala(idSala, conn);

			this.lobby.quitarCliente(conn);

			break;

		case "CrearSala":

			Integer idSalaCrear = this.gson.fromJson(msg.getJson(), Integer.class);

			this.lobby.crearSala(idSalaCrear);

			conn.enviarInfo(new Mensaje("CrearSalaOk", gson.toJson(idSalaCrear)));

			this.lobby.agregarJugadorASala(idSalaCrear, conn);

			this.lobby.quitarCliente(conn);

			break;

		case "TerminarConn":

			String ventanaActual = this.gson.fromJson(msg.getJson(), String.class);

			if (ventanaActual.equals("Login")) {

				this.quitarCliente(conn);

			} else if (ventanaActual.substring(0, 4).equals("Sala")) {

				this.lobby.quitarJugadorDeSala(Integer.parseInt(ventanaActual.substring(4)), conn);

			} else if (ventanaActual.equals("Lobby")) {

				this.lobby.quitarCliente(conn);
			}

			conn.enviarInfo(new Mensaje("TerminarOk", ""));
			
			conn.cerrar();

		case "EmpezarJuego":

			Integer salaJuego = this.gson.fromJson(msg.getJson(), Integer.class);

			this.lobby.iniciarJuegoSala(salaJuego);

			break;
			
			
			
		case "SalirJuego":

			Integer idJuego = this.gson.fromJson(msg.getJson(), Integer.class);

			conn.enviarInfo(new Mensaje("SalirJuegoOk", gson.toJson(idJuego)));
			
			this.lobby.quitarjugadorDeJuego(conn, idJuego);
			
			this.lobby.agregarCliente(conn);
			
						
			break;



		case "TeclaDerecha":

			Integer salaId = this.gson.fromJson(msg.getJson(), Integer.class);

			this.lobby.teclaJuego("Derecha", salaId, conn);

			break;

		case "TeclaIzquierda":

			Integer salaId2 = this.gson.fromJson(msg.getJson(), Integer.class);

			this.lobby.teclaJuego("Izquierda", salaId2, conn);

			break;

		case "TeclaAbajo":

			Integer salaId3 = this.gson.fromJson(msg.getJson(), Integer.class);

			this.lobby.teclaJuego("Abajo", salaId3, conn);

			break;

		case "TeclaArriba":

			Integer salaId4 = this.gson.fromJson(msg.getJson(), Integer.class);

			this.lobby.teclaJuego("Arriba", salaId4, conn);

			break;
		}

	}

	class HiloTestClientes extends Thread {

		private Servidor sv;

		public HiloTestClientes(Servidor sv) {
			this.sv = sv;
		}

		public void run() {

			while (true) {

				System.out.println("sv : " + sv.clientes.size());

				System.out.println("lob : " + sv.lobby.cantJugadoresTest());

				System.out.println("Salas : " + sv.lobby.cantSalas());

				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	private Mensaje loguear(String user, String pass) {

		
		Jugador player = new Jugador();
		
		player.setUsuario(user);
		 
        player.setClave(pass);
        
     	if (!db.getUsuario(player)) {

			return new Mensaje("LogErr", this.gson.toJson("Logueo invalido"));
		} else {

			return new Mensaje("LogOk", this.gson.toJson("Logueo exitoso"));
		}

	}

	private Mensaje registrar(String user, String pass) {

		
		Jugador player = new Jugador();
		
		player.setUsuario(user);
		 
        player.setClave(pass);
		
		if (!db.setCrearUsuario(player)) {
			return new Mensaje("RegErr", this.gson.toJson("Registro invalido"));
		} else {
			return new Mensaje("RegOk", this.gson.toJson("Registro exitoso"));
		}

	}

	public void quitarCliente(ClienteConn cli) {

		this.clientes.remove(cli);

	}

	public Socket aceptarCliente() throws IOException {

		return this.server.accept();

	}

	public void agregarCliente(ClienteConn cli) {
		this.clientes.add(cli);
	}

	public static void main(String[] args) {
		new Servidor(10000);
	}

}