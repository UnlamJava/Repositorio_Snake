package servidor;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import snakePKG.Mapa;
import snakePKG.Vibora;
import util.ClienteConn;

public class JuegoServer {
	
	private Mapa mapa;
	private HashMap<ClienteConn, Vibora> viboras;
	
	
	public JuegoServer(Collection<ClienteConn> clientes) {
		
		this.mapa = new Mapa("Arena");
	
		this.viboras = new HashMap<>();
		
		for(ClienteConn c : clientes) {
			
			Vibora v = new Vibora(4);
			
			this.viboras.put(c, v);
			
			this.mapa.ubicarViboraEnMapa(v, 5, 5);
		}
		
		this.mapa.mostrarMapa();
	}
	
	class HiloMover extends Thread{
		
		public void run() {
				
				while(true) {
					
					try {
						
						for(Entry<ClienteConn, Vibora> v : viboras.entrySet()) {
							v.getValue().mover(mapa);
							Thread.sleep(500);
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}	
				
				}
				
			}
		
	}
	

	public void iniciar() {
		
		//Les aviso a todos que empieza el juego
		
		HiloLectura hc = null;
		for(Entry<ClienteConn, Vibora> v : viboras.entrySet()) {
			
			try {
				
				Boolean info = true;
				
				v.getKey().enviarInfo(info);
				hc = new HiloLecturaJuego(v.getKey(), v.getValue());
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		hc.start();
		
		
		
		//crear hilo para mover constantemente
		//crear hilo para mandar a todos los clientes el mapa
		
		HiloServidorEnviar hm = new HiloServidorEnviar(this.mapa, this.viboras);
		HiloMover hmv = new HiloMover();
	
		
		hm.start();
		hmv.start();
	
		
		Boolean juegoOn = true;
	
		while(juegoOn) {
			
		}
	}
	
	
	
	

}
