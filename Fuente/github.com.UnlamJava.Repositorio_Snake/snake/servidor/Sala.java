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

	public Sala(int id, int cantJugadores, int limite) {

		this.jugadores = new ArrayList<>();

		this.id = id;

		this.cantJugadores = cantJugadores;

		this.limite = limite;

		this.enPartida = false;
		
		this.gson = new Gson();
		
		HiloActualizarSala ha = new HiloActualizarSala(this);

		ha.start();
	}
	
	public void iniciarJuego() {
		
		Juego juego = new Juego(this.jugadores);
		
		juego.iniciar();
		
	}
	
	public void agregarJugador(ClienteConn j) {

		if (this.cantJugadores == limite)
			return;

		this.jugadores.add(j);

		this.cantJugadores++;

	}

	public void quitarJugador(ClienteConn j) {

		this.jugadores.remove(j);

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
	
	
	
	private Collection<String> obtenerJugadores() {
		
		Collection<String> jugadores = new ArrayList<>();
		
		for(ClienteConn c :  this.jugadores) {
			
			jugadores.add(c.getUsuario());
		}
		
		return jugadores;
	}
	
}
