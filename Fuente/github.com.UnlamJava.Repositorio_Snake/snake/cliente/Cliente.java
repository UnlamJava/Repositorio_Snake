package cliente;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collection;

import graficos.JVentanaInicio;
import graficos.JVentanaLogeo;
import graficos.JVentanaSala;
import graficos.JVentanaLobby;
import graficos.JVentanaJuego;
import util.ClienteConn;
import util.Mensaje;
import com.google.gson.Gson;

public class Cliente {

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
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public Cliente(String ip, int port) {

		this.ipServer = ip;

		this.puerto = port;

		this.inicio = new JVentanaInicio(this);

		inicio.setVisible(true);

	}

	public void jugarOnline() {

		try {
			
			this.gson = new Gson();

			this.entrada = new Socket(this.ipServer, this.puerto);

			ObjectOutputStream out = new ObjectOutputStream(this.entrada.getOutputStream());

			ObjectInputStream in = new ObjectInputStream(this.entrada.getInputStream());

			conn = new ClienteConn(in, out);

			System.out.println("Conectado al sv");

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

			this.conn.enviarInfo(new Mensaje("Registrarse", user + "|" + pass));

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
			
			this.sala.dispose();
			
			this.ventanaJuego = new JVentanaJuego(mapaInicial, this);
			
			this.ventanaJuego.setVisible(true);
			
			break;
			
		case "Mapa":
			
			Integer[][] mapa = this.gson.fromJson(mensaje.getJson(), Integer[][].class);
			
			/*
			for(int i = 0; i < mapa.length; i++) {
				System.out.println(Arrays.toString(mapa[i]));
			}
			
			System.out.println();
			*/
			
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
			// TODO Auto-generated catch block
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

	public void enviarTeclaIzquierda() {
		
		try {
			this.conn.enviarInfo(new Mensaje("TeclaIzquierda", ""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void enviarTeclaDerecha() {
		try {
			this.conn.enviarInfo(new Mensaje("TeclaDerecha", ""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enviarTeclaArriba() {
		try {
			this.conn.enviarInfo(new Mensaje("TeclaArriba", ""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enviarTeclaAbajo() {
		try {
			this.conn.enviarInfo(new Mensaje("TeclaAbajo", ""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
