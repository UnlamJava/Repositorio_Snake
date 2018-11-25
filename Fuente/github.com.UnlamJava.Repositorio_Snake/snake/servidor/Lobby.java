package servidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;

import util.ClienteConn;
import util.Mensaje;

public class Lobby {
	
	private Collection<ClienteConn> clientes;

	private Collection<Sala> salas;
	
	private Gson gson;

	public Lobby() {
		
		this.clientes = new ArrayList<>();
		
		this.salas = new ArrayList<>();
		
		this.gson = new Gson();
		
		HiloActualizarLobby ha = new HiloActualizarLobby(this);
		
		ha.start();
	}
	
	public int cantJugadoresTest() {
		return this.clientes.size();
	}
	
	public String cantSalas() {
		String res = "";
		
		for(Sala s: this.salas) {
			res += s.getId() + " " + s.getCantJugadores() + System.lineSeparator();
		}
		
		return res;
	}
	
	public void enviarSalasAtodos() throws IOException {
		
		Collection<String> sals = this.obtenerSalas();
	
	
		for(ClienteConn c: this.clientes) {

			c.enviarInfo(new Mensaje("Salas", this.gson.toJson(sals)));
			
		}
		
	}
	
	public void crearSala(Integer id) {
		
		this.salas.add(new Sala(id, 0, 10));	
	}
	
	public void eliminarSala(Integer id) {
		
		
	}
	
	public void agregarJugadorASala(Integer idSala, ClienteConn jugador) {
		
		
		for(Sala s : this.salas) {
			
			if(s.getId().equals(idSala)) {
				
				s.agregarJugador(jugador);
				
				break;
			}
				
		}	
	}
	
	public void quitarJugadorDeSala(Integer idSala, ClienteConn cli) {
		
		for(Sala s : this.salas) {
			
			if(s.getId().equals(idSala)) {
				
				s.quitarJugador(cli);
				
				break;
			}
			
			
		}
		
	}
	
	public void agregarCliente(ClienteConn cli) {
		
		this.clientes.add(cli);
		
	}
	
	public void quitarCliente(ClienteConn cli) {
		
		this.clientes.remove(cli);
	}


	public Collection<String> obtenerSalas() {
		
		Collection<String> res = new ArrayList<>();
		
		for(Sala s : this.salas) {
			
			res.add(s.toString());
			
		}
		
		return res;
	}

	public void iniciarJuegoSala(Integer salaJuego) {
				
		for(Sala sala : this.salas) {
			
			if(sala.getId().equals(salaJuego)) {
				
				sala.iniciarJuego();
				
				break;
			}	
		}
		
	}
	
	
}
