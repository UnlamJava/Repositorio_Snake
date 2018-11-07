
package snakeSinglePlayer;

import snakePKG.Mapa;

public class HiloGenerarFruta extends Thread {

	private Mapa mapa;
	
	public HiloGenerarFruta(Mapa mapa) {
		this.mapa = mapa;
	}

	public void run() {
		
		
		while(true) {
			
			mapa.generarFruta();
			
			try {
				Thread.sleep(1500);
			
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
		}
		
		
	}
	
}
