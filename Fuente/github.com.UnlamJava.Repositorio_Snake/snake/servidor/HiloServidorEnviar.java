package servidor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import snakePKG.Mapa;
import snakePKG.Vibora;
import util.ClienteConn;

public class HiloServidorEnviar extends Thread {

	private Mapa mapa;
	private HashMap<ClienteConn, Vibora> viboras;
	
	
	public HiloServidorEnviar(Mapa mapa, HashMap<ClienteConn, Vibora> viboras) {
		this.mapa = mapa;
		this.viboras = viboras;
	}


	public void run() {
		
		
		Integer[][] aux = new Integer[mapa.getTamanioX()][mapa.getTamanioX()];
		Integer[][] aux2 = new Integer[mapa.getTamanioX()][mapa.getTamanioX()];
		
		while(true) {
			
			try {
				
				for(Entry<ClienteConn, Vibora> v : viboras.entrySet()) {
				
					Integer[][] map = mapa.getMapa();
	
					for(int i = 0; i < map.length; i++) {
						aux[i] = map[i].clone();
					}
					
					aux2 = aux.clone();
			
					v.getKey().enviarInfo(aux2);
				
					Thread.sleep(500);
				}
				
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				break;
			}	
		
		}
		
	}
}
