
package snakeSinglePlayer;

import snakePKG.Mapa;

public class HiloGenerarFruta extends Thread {

	private Mapa mapa;
	
	private boolean enCurso = true;
	
	public HiloGenerarFruta(Mapa mapa) {
		this.mapa = mapa;
	}

	public void run() {
		
		while(enCurso) {
			
			mapa.generarFruta();
	
			try {
				Thread.sleep(1500);
			
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void detener() {
		
		this.enCurso = false;
		
	}
	
}
