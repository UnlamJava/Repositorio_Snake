package servidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;

import util.ClienteConn;
import util.Mensaje;

public class Sala {

	private int id;
	
	private ClienteConn admin;
	
	private Collection<ClienteConn> jugadores;

	private int cantJugadores;

	private int limite;

	private boolean enPartida;
	
	private Gson gson;

	private HiloActualizarSala ha;
	
	private Juego juego;
	
	public Sala(int id, int cantJugadores, int limite, ClienteConn admin) {

		this.jugadores = new ArrayList<>();

		this.admin = admin;
		
		this.id = id;

		this.cantJugadores = cantJugadores;

		this.limite = limite;

		this.enPartida = false;
		
		this.gson = new Gson();
		
		this.ha = new HiloActualizarSala(this);

		ha.start();
		
		
	}
	
	public void iniciarJuego() {
		
		this.enPartida = true;
		
		this.ha.detener();
		
		this.juego = new Juego(this.jugadores);
		
		this.juego.iniciar();
		
	}
	
	
	public void moverTodosALobby(Lobby lobby){
		
		for(ClienteConn c : this.jugadores){
			
			lobby.agregarCliente(c);

		}
		
		
		this.jugadores.clear();
		
	}
	
	public void agregarJugador(ClienteConn j) {

		if (this.cantJugadores == limite)
			return;

		this.jugadores.add(j);

		this.cantJugadores++;

	}

	public void quitarJugador(ClienteConn cli) {

		if(cli.equals(this.admin) && this.jugadores.size() > 1){
	
			this.jugadores.remove(cli);
			
			this.admin = this.jugadores.iterator().next();
		
			try {
			
				this.admin.enviarInfo(new Mensaje("EresAdmin", this.gson.toJson("")));
			
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			
			this.cantJugadores--;
			
			return;
		}
		
		System.out.println("ultimo");

		this.jugadores.remove(cli);

		this.cantJugadores--;
	}
	

	public void quitarjugadorDeJuegoSala(ClienteConn j) {


		this.jugadores.remove(j);
		
		this.juego.quitarJugadorJuego(j);
		
		this.cantJugadores--;
		
		
	}
	
	public Integer getId() {

		return this.id;
	}

	public int getLimite() {

		return this.limite;
	}

	public int getCantJugadores() {

		return this.cantJugadores;
	}

	public boolean getEstado() {

		return this.enPartida;
	}

	public String toString() {

		return "Sala [" + this.id + "] J = " + this.cantJugadores + "/" + this.limite + " " + this.getEstado();

	}

	public void enviarSalasAtodos() throws IOException {
		
		Collection<String> jugadores = this.obtenerJugadores();
		
		for (ClienteConn c : this.jugadores) {
			
			c.enviarInfo(new Mensaje("Jugadores", this.gson.toJson(jugadores)));
		}
		
	}

	
	
	public void detenerHilosJuego() {
	
		this.juego.detenerHilos();
	}
	
	private Collection<String> obtenerJugadores() {
		
		Collection<String> jugadores = new ArrayList<>();
		
		for(ClienteConn c :  this.jugadores) {
			
			jugadores.add(c.getUsuario());
		}
		
		return jugadores;
	}

	public void teclaJuego(String dir, ClienteConn conn) {
		
		this.juego.cambiarDir(conn, dir);
		
	}

	public boolean salaLLena() {
		return this.cantJugadores == this.limite;
	}
	
}
