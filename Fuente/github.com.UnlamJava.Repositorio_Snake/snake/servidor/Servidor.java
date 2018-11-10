package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import com.google.gson.Gson;
import util.ClienteConn;
import util.Mensaje;

public class Servidor {

	private Gson gson;

	private ServerSocket server;

	private Collection<ClienteConn> clientes;
	
	private Lobby lobby;
	
    //private UsuariosDB db;

	public Servidor(int puerto) {

		this.clientes = new ArrayList<>();

		this.gson = new Gson();
		
		this.lobby = new Lobby();
		
		try {

			this.server = new ServerSocket(puerto);

			System.out.println("SERVER INICIADO - Esperando conexiones de clientes ...");
			
			HiloAceptarClientes ha = new HiloAceptarClientes(this);
			
			ha.start();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	
	public void interpretarMensaje(Mensaje msg, ClienteConn conn) throws IOException {

		String tipo = msg.getNombreMensaje();

		Mensaje res;

		switch (tipo) {

			case "Loguearse":
	
				String user[] = gson.fromJson(msg.getJson(), String.class).split("-");
				
				res = this.loguear(user[0],user[1]);
				
				conn.setUsuario(user[0]);
				
				conn.enviarInfo(res);
	
				break;
	
			case "Registrarse":
				
				String userReg[] = new String[2];
	
				userReg = gson.fromJson(msg.getJson(), String.class).split("-");
	
				res = this.registrar(userReg[0], userReg[1]);
	
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
				
				conn.enviarInfo(new Mensaje("CrearSalaOk",gson.toJson(idSalaCrear)));
				
				this.lobby.agregarJugadorASala(idSalaCrear, conn);
				
				this.lobby.quitarCliente(conn);
		
				break;

		}

	}

	private Mensaje loguear(String user, String pass) {


		if (user.equals("invalido")) {
			
			return new Mensaje("LogErr", this.gson.toJson("Logueo invalido"));
		} else {
			
			return new Mensaje("LogOk", this.gson.toJson("Logueo exitoso"));
		}

	}
	
	private Mensaje registrar(String user, String pass) {

		if (user.equals("invalid")) {
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
