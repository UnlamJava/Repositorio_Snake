package servidor;

public class HiloLecturaJuego extends Thread {
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
