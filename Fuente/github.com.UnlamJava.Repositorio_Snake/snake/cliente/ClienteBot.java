package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import graficos.JVentanaInicio;
import graficos.JVentanaJuego;
import graficos.JVentanaLobby;
import graficos.JVentanaLogeo;
import graficos.JVentanaSala;
import util.ClienteConn;
import util.Mensaje;
import util.Puntaje;

public class ClienteBot extends Cliente{

	private Socket entrada;
	
	private String ipServer;
	private int puerto;

	private ClienteConn conn;
	private Gson gson;

	private JVentanaLogeo login;
	private JVentanaInicio inicio;
	private JVentanaLobby lobby;
	private JVentanaSala sala;
	private JVentanaJuego ventanaJuego;

	public ClienteBot(String ip, int port) {

		super(ip,port);
		
		this.inicio = new JVentanaInicio(this);

		inicio.setVisible(true);

	}

	public void jugarOnline() {

		try {
			
			this.gson = new Gson();

			this.entrada = new Socket(this.ipServer, this.puerto);

			DataOutputStream out = new DataOutputStream(entrada.getOutputStream());

			DataInputStream in = new DataInputStream(this.entrada.getInputStream());

			conn = new ClienteConn(in, out);

			//System.out.println("Conectado al sv");

			HiloLecturaMensajes hm = new HiloLecturaMensajes(this, this.conn);

			hm.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.login = new JVentanaLogeo(this);

		login.setVisible(true);
		

	}

	public void loguearse(String user, String pass) {

		try {

			this.conn.enviarInfo(new Mensaje("Loguearse", user + "-" + pass));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void registrarse(String user, String pass) {

		try {

			this.conn.enviarInfo(new Mensaje("Registrarse", user + "-" + pass));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void interpretarMensaje(Mensaje mensaje) throws IOException {

		String tipo = mensaje.getNombreMensaje();

		switch (tipo) {

		case "LogOk":

			String msgLog = this.gson.fromJson(mensaje.getJson(), String.class);

			System.out.println(msgLog);

			this.inicio.dispose();

			this.login.dispose();

			this.lobby = new JVentanaLobby(this);

			lobby.setVisible(true);
			
			this.conn.enviarInfo(new Mensaje("UnirseALobby", ""));

			break;

		case "LogErr":

			String msgErr = this.gson.fromJson(mensaje.getJson(), String.class);

			System.out.println(msgErr);
				
			this.login.mostrarError("No se encontro usuario","Lo sentimos");
			

			break;
			
		
			
			
		case "RegOk":

			String msgReg = this.gson.fromJson(mensaje.getJson(), String.class);

			System.out.println(msgReg);

			this.inicio.dispose();
			
			this.login.dispose();

			this.lobby = new JVentanaLobby(this);

			lobby.setVisible(true);
			
			this.conn.enviarInfo(new Mensaje("UnirseALobby", ""));

			break;

		case "RegErr":

			String msgRegError = this.gson.fromJson(mensaje.getJson(), String.class);

			System.out.println(msgRegError);
			
			this.login.mostrarError("Usuario ya existente","Lo sentimos");
			
			break;
			

		case "Salas":

			@SuppressWarnings("unchecked")

			Collection<String> salas = this.gson.fromJson(mensaje.getJson(), Collection.class);

			this.lobby.dibujarSalas(salas);

			break;

		case "CrearSalaOk":
			
			Integer idSalaCrear = gson.fromJson(mensaje.getJson(), Integer.class);
			
			this.lobby.dispose();

			this.sala = new JVentanaSala(this, idSalaCrear, true);

			this.sala.setVisible(true);
			
			break;

		case "Jugadores":

			@SuppressWarnings("unchecked") 
			Collection<String> jugadores = this.gson.fromJson(mensaje.getJson(), Collection.class);

			this.sala.actualizarJugadores(jugadores);

			break;

		case "UnirseASalaOk":
			
			Integer idSalaUnir = gson.fromJson(mensaje.getJson(), Integer.class);
			
			this.lobby.dispose();

			this.sala = new JVentanaSala(this, idSalaUnir, false);

			this.sala.setVisible(true);
			
			break;
			
		case "TerminarOk":
			
			conn.cerrar();
			
			this.entrada.close();
			
			break;
		
		case "EmpezarJuego":
			
			Integer[][] mapaInicial = this.gson.fromJson(mensaje.getJson(), Integer[][].class);
			
			Integer idSala = this.sala.getIdSala();
			
			this.sala.dispose();
			
			this.ventanaJuego = new JVentanaJuego(mapaInicial, this, idSala);
			
			this.ventanaJuego.setVisible(true);
			
			break;
			
		
		case "Mapa":
			
			Integer[][] mapa = this.gson.fromJson(mensaje.getJson(), Integer[][].class);
			
			this.ventanaJuego.actualizarMapa(mapa);
			
			break;
			
		case "Puntajes":
			
			LinkedList<Puntaje> lista = this.gson.fromJson(mensaje.getJson(),new TypeToken<LinkedList<Puntaje>>() {}.getType());
		
			
			this.ventanaJuego.actualizarPuntajes(lista);
			
			break;
		}

	}

	public void crearSala(Integer id) {

		try {

			this.conn.enviarInfo(new Mensaje("CrearSala", this.gson.toJson(id)));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void unirseASala(Integer idSala) {

		try {

			this.conn.enviarInfo(new Mensaje("UnirseASala", this.gson.toJson(idSala)));

		} catch (IOException e) {
			// TODO Auto-generated catch blocka
			e.printStackTrace();
		}

	}
	
	public void desconectar(String ventanaActual) {
		
		try {
		
			conn.enviarInfo(new Mensaje("TerminarConn", this.gson.toJson(ventanaActual)));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	
	
	public void empezarPartida(Integer idSala) {
		
		try {
			this.conn.enviarInfo(new Mensaje("EmpezarJuego", this.gson.toJson(idSala)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new Cliente("localhost", 10000); // Me conecto al server
	}

	
	public void movimientoAutonomo(Integer[][] mapa) {
		
		double aux =  Math.random();
		
		if(aux<=0.25) {
			enviarTeclaIzquierda(1);
		}
		if(aux>0.25 && aux <=0.5) {
			enviarTeclaDerecha(1);
		}
		if(aux>0.5 && aux <= 0.75) {
			enviarTeclaArriba(1);
		}
		if(aux>0.75) {
			enviarTeclaAbajo(1);
		}
		
	}
	
	
	
	public void enviarTeclaIzquierda(Integer idSala) {
		
		try {
			this.conn.enviarInfo(new Mensaje("TeclaIzquierda", this.gson.toJson(idSala)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void enviarTeclaDerecha(Integer idSala) {
		try {
			this.conn.enviarInfo(new Mensaje("TeclaDerecha", this.gson.toJson(idSala)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enviarTeclaArriba(Integer idSala) {
		try {
			this.conn.enviarInfo(new Mensaje("TeclaArriba", this.gson.toJson(idSala)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enviarTeclaAbajo(Integer idSala) {
		try {
		
			this.conn.enviarInfo(new Mensaje("TeclaAbajo", this.gson.toJson(idSala)));
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}
}

