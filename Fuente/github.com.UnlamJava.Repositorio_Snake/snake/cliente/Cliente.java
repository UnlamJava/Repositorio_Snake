package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import graficos.JVentanaInicio;
import graficos.JVentanaLogeo;
import graficos.JVentanaSala;
import sinlgePlayer.JuegoSingle;
import graficos.JVentanaLobby;
import graficos.JVentanaJuego;
import util.ClienteConn;
import util.Mensaje;
import util.Puntaje;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Cliente {

	private Socket entrada;

	private String ipServer;
	private int puerto;
	private boolean jugandoSolo;
	private ClienteConn conn;
	private Gson gson;
	private boolean estoyEnInicio;
	private JVentanaLogeo login;
	private JVentanaInicio inicio;
	private JVentanaLobby lobby;
	private JVentanaSala sala;
	private JVentanaJuego ventanaJuego;

	public Cliente(String ip, int port) {

		this.ipServer = ip;

		this.puerto = port;
		this.jugandoSolo = false;
		this.estoyEnInicio=true;

	}

	public void jugarOnline() {

		try {

			this.gson = new Gson();

			this.entrada = new Socket(this.ipServer, this.puerto);

			DataOutputStream out = new DataOutputStream(entrada.getOutputStream());

			DataInputStream in = new DataInputStream(this.entrada.getInputStream());

			conn = new ClienteConn(in, out);


			HiloLecturaMensajes hm = new HiloLecturaMensajes(this, this.conn);

			hm.start();
			
			this.login = new JVentanaLogeo(this);

			login.setVisible(true);

		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(this.inicio, "No se pudo conectar al servidor", "Error", JOptionPane.ERROR_MESSAGE);
	
		}
	
		

	}

	public void loguearse(String user, String pass) {

		try {

			this.conn.enviarInfo(new Mensaje("Loguearse", user + "-" + pass));

		} catch (IOException e) {
		
			JOptionPane.showMessageDialog(this.login, "Error al enviar mensaje al servidor", "Error", JOptionPane.ERROR_MESSAGE);
			
		}

	}

	public void registrarse(String user, String pass) {

		try {

			this.conn.enviarInfo(new Mensaje("Registrarse", user + "-" + pass));

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this.login, "Error al enviar mensaje al servidor", "Error", JOptionPane.ERROR_MESSAGE);
			
		}

	}

	public void interpretarMensaje(Mensaje mensaje) throws IOException {

		String tipo = mensaje.getNombreMensaje();

		switch (tipo) {

		case "LogOk":

			String msgLog = this.gson.fromJson(mensaje.getJson(), String.class);


			this.inicio.dispose();

			this.login.dispose();

			this.lobby = new JVentanaLobby(this);

			lobby.setVisible(true);

			this.conn.enviarInfo(new Mensaje("UnirseALobby", ""));

			break;

		case "LogErr":

			String msgErr = this.gson.fromJson(mensaje.getJson(), String.class);


			this.login.mostrarError("No se encontro usuario", "Lo sentimos");

			break;

		case "RegOk":

			String msgReg = this.gson.fromJson(mensaje.getJson(), String.class);


			this.inicio.dispose();

			this.login.dispose();

			this.lobby = new JVentanaLobby(this);

			lobby.setVisible(true);

			this.conn.enviarInfo(new Mensaje("UnirseALobby", ""));

			break;

		case "RegErr":

			String msgRegError = this.gson.fromJson(mensaje.getJson(), String.class);


			this.login.mostrarError("Usuario ya existente", "Lo sentimos");

			break;

		case "Salas":

			@SuppressWarnings("unchecked")

			Collection<String> salas = this.gson.fromJson(mensaje.getJson(), Collection.class);
			
			this.lobby.refrescar();
			
			this.lobby.dibujarSalas(salas);

			break;

		case "CrearSalaOk":

			Integer idSalaCrear = gson.fromJson(mensaje.getJson(), Integer.class);

			this.lobby.dispose();

			this.sala = new JVentanaSala(this, idSalaCrear, true);

			this.sala.setVisible(true);

			break;

		case "SalirDeSalaOk":
			
			this.sala.dispose();
			
			this.lobby = new JVentanaLobby(this);
			
			this.lobby.setVisible(true);
			
			break;
			
		case "Jugadores":
		
			Collection<String> jugadores = this.gson.fromJson(mensaje.getJson(),  new TypeToken<Collection<String>>() {
			}.getType());

			this.sala.actualizarJugadores(jugadores);

			break;

		case "UnirseASalaOk":

			Integer idSalaUnir = gson.fromJson(mensaje.getJson(), Integer.class);

			this.lobby.dispose();

			this.sala = new JVentanaSala(this, idSalaUnir, false);

			this.sala.setVisible(true);

			break;
			
		case "UnirseASalaErr":

			JOptionPane.showMessageDialog(this.lobby, gson.fromJson(mensaje.getJson(), String.class));
			
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

			LinkedList<Puntaje> lista = this.gson.fromJson(mensaje.getJson(), new TypeToken<LinkedList<Puntaje>>() {
			}.getType());

			this.ventanaJuego.actualizarPuntajes(lista);

			break;

		case "SalirJuegoOk":

			this.ventanaJuego.dispose();
			
			this.ventanaJuego.getJoystick().detener();

			this.lobby = new JVentanaLobby(this);

			this.lobby.setVisible(true);
			
			break;
			
		case "EresAdmin":
			
			this.sala.activarAdmin();
			
			break;
		}

	}

	public void crearSala(Integer id) {

		try {

			this.conn.enviarInfo(new Mensaje("CrearSala", this.gson.toJson(id)));

		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(this.login, "Error al enviar peticion", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
	}

	public void unirseASala(Integer idSala) {

		try {

			this.conn.enviarInfo(new Mensaje("UnirseASala", this.gson.toJson(idSala)));

		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(this.login, "Error al enviar peticion", "Error", JOptionPane.ERROR_MESSAGE);
			
		}

	}

	public void salirJuego(Integer idSala) {

		try {

			this.conn.enviarInfo(new Mensaje("SalirJuego", this.gson.toJson(idSala)));

		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(this.login, "Error al salir del juego", "Error", JOptionPane.ERROR_MESSAGE);
			
			System.exit(1);
		}

	}

	public void desconectar(String ventanaActual) {

		try {

			conn.enviarInfo(new Mensaje("TerminarConn", this.gson.toJson(ventanaActual)));

		} catch (IOException e) {

			System.exit(1);
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

		// new Cliente("10.11.3.13", 10000); // Me conecto al server
		Cliente c = new Cliente("localhost", 10000); // Me conecto al server
		c.inicio = new JVentanaInicio(c);
		JuegoSingle j = new JuegoSingle(c);
		while (c.estoyEnInicio) {

			while (c.jugandoSolo) {

				j.iniciar();
			}

			c.inicio.setVisible(true);
			

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

	public void salirDeSala(int idSala) {
		
		try {

			this.conn.enviarInfo(new Mensaje("SalirDeSala", this.gson.toJson(idSala)));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void jugarOffline(boolean s) {
		this.jugandoSolo = s;

	}

	public void estoyEnInicioOn() {
		// TODO Auto-generated method stub
		
	}

	public void estoyEnInicioOff() {
		this.estoyEnInicio=false;
	}

}