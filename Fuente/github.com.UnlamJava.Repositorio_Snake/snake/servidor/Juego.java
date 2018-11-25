package servidor;

import java.util.Collection;
import java.util.HashMap;

import snakePKG.Mapa;
import snakePKG.Vibora;
import util.ClienteConn;

public class Juego {

	private Mapa mapa;
	
	private HashMap<ClienteConn, Vibora> viboras;

	public Juego(Collection<ClienteConn> clientes) {
		
		//TODO: Generar el mapa (instanciarlo), y ubicar las viboras, enlazadas con sus respectivos clientes.
		
	}

	public void iniciar() {
		
		this.avisarATodos();
		
		//hiloenviarmapa iniciar
		//hiloMoverViboras iniciar
		
		//condicion hasta q el juego termine
		
		this.terminar();
	}
	
	public void terminar() {
		
		//limpio todo
		
		//los clientes vuelven a la sala o al lobby
		
	}
	
	public void cambiarDir(ClienteConn conn, String dir) {
		
		// get vibora de conn
		//vibora.cambiarDir(dir)
		
	}
	
	
	
	
}
