package cliente;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

import graficos.JVentanaInicio;
import graficos.JVentanaLogeo;
import graficos.JVentanaSala;
import graficos.JVentanaLobby;
import util.ClienteConn;
import util.Mensaje;
import com.google.gson.Gson;

public class Cliente {

	private Socket entrada;

	private ObjectOutputStream out;
	private ObjectInputStream in;

	private String ipServer;
	private int puerto;

	private ClienteConn conn;

	private Gson gson;

	private JVentanaLogeo login;
	private JVentanaInicio inicio;
	private JVentanaLobby lobby;
	private JVentanaSala sala;

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

			this.out = new ObjectOutputStream(this.entrada.getOutputStream());

			this.in = new ObjectInputStream(this.entrada.getInputStream());

			conn = new ClienteConn(in, out);

			System.out.println("Conectado al sv");

			HiloLecturaMensajes hm = new HiloLecturaMensajes(this, this.conn);

			hm.start();

		} catch (IOException e) {

			e.printStackTrace();
		}

		this.login = new JVentanaLogeo(this);

		login.setVisible(true);

	}

	public void loguearse(String user, String pass) {

		try {

			this.conn.enviarInfo(new Mensaje("Loguearse", user + "-" + pass));

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void registrarse(String user, String pass) {

		try {

			this.conn.enviarInfo(new Mensaje("Registrarse", user + "|" + pass));

		} catch (IOException e) {

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

			this.sala = new JVentanaSala(this, idSalaCrear);

			this.sala.setVisible(true);

			break;

		case "Jugadores":

			Collection<String> jugadores = this.gson.fromJson(mensaje.getJson(), Collection.class);

			this.sala.actualizarJugadores(jugadores);

			break;

		case "UnirseASalaOk":
			
			Integer idSalaUnir = gson.fromJson(mensaje.getJson(), Integer.class);
			
			this.lobby.dispose();

			this.sala = new JVentanaSala(this, idSalaUnir);

			this.sala.setVisible(true);

			break;
		}

	}

	public void crearSala(Integer id) {

		try {

			this.conn.enviarInfo(new Mensaje("CrearSala", this.gson.toJson(id)));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void unirseASala(Integer idSala) {

		try {

			this.conn.enviarInfo(new Mensaje("UnirseASala", this.gson.toJson(idSala)));

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Cliente("localhost", 10000); // Me conecto al server
	}

}
