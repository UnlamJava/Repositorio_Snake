package servidor;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

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
	
	class HiloEnviarMapa extends Thread{
		
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
	
	class HiloCambiarDireccion extends Thread{
		
		private ClienteConn con;
		private Vibora vibora;
		
		public HiloCambiarDireccion(ClienteConn con, Vibora vibora) {
			this.con = con;
			this.vibora = vibora;
		}
		
		public void run() {
			boolean juegoOn = true;
			String dir = "";
			
			while(juegoOn) {
				try {
					
					dir = (String) con.recibirInfo();
					
					this.vibora.cambiarDir(dir);
					
					Thread.sleep(500);
				
				} catch (ClassNotFoundException | IOException | InterruptedException e) {
		
					e.printStackTrace();
					break;
				}
			}
			
			
			
		}
		
		
	}

	public void iniciar() {
		
		//Les aviso a todos que empieza el juego
		
		HiloCambiarDireccion hc = null;
		for(Entry<ClienteConn, Vibora> v : viboras.entrySet()) {
			
			try {
				
				Boolean info = true;
				
				v.getKey().enviarInfo(info);
				hc = new HiloCambiarDireccion(v.getKey(), v.getValue());
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		hc.start();
		
		
		
		
		//crear hilo para mover constantemente
		//crear hilo para mandar a todos los clientes el mapa
		
		HiloEnviarMapa hm = new HiloEnviarMapa();
		HiloMover hmv = new HiloMover();
	
		
		hm.start();
		hmv.start();
	
		
		Boolean juegoOn = true;
	
		while(juegoOn) {
			
		}
	}
	
	
	
	

}
