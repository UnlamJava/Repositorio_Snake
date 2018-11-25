package servidor;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import com.google.gson.Gson;

import snakePKG.Mapa;
import snakePKG.Vibora;
import util.ClienteConn;
import util.Mensaje;

public class Juego {

	private Mapa mapa;
	
	private HashMap<ClienteConn, Vibora> viboras;
	
	private Gson gson;
	
	public Juego(Collection<ClienteConn> clientes) {
		
		this.gson = new Gson();
		
		this.viboras = new HashMap<>();
		
		this.mapa = new Mapa(Mapa.MAPA_1);
		
		for(ClienteConn cli : clientes) {
			
			this.viboras.put(cli, new Vibora());
			
			
		}
		
		
		//TODO: Generar el mapa (instanciarlo), y ubicar las viboras, enlazadas con sus respectivos clientes.
		
		
		
		
	}

	public void iniciar() {
		
		try {
		
			this.avisarATodos();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		HiloEnviarMapa hiloMapa = new HiloEnviarMapa(this);
		
		//HiloMoverViboras hiloMoverViboras = new HiloMoverViboras();
		
		hiloMapa.start();
		
		//hiloMoverViboras.start();
		
		//condicion hasta q el juego termine
		
		//this.terminar();
	}
	
	private void avisarATodos() throws IOException {
		
		for(ClienteConn cli : this.viboras.keySet()) {
			
			cli.enviarInfo(new Mensaje("EmpezarJuego", this.gson.toJson(this.mapa.getMapa())));
			
		}
		
	}

	public void terminar() {
		
		//limpio todo
		
		//los clientes vuelven a la sala o al lobby
		
	}
	
	public void cambiarDir(ClienteConn conn, String dir) {
		
		this.viboras.get(conn).cambiarDir(dir);
		
	}
	
	public void enviarMapaAtodos() throws IOException {
		
		for(ClienteConn cli : this.viboras.keySet()) {
			
			cli.enviarInfo(new Mensaje("Mapa", this.gson.toJson(this.mapa.getMapa())));
			
		}
		
		
	}
	
	
}
