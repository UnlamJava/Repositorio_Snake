package sinlgePlayer;

import snakePKG.Vibora;

import snakePKG.Mapa;

public class HiloMover extends Thread{
	
	private Vibora v;
	private Mapa mapa;
	private boolean enCurso = true;
	
	public HiloMover(Vibora vibora, Mapa mapa){
		this.v = vibora;
		this.mapa = mapa;
		
	}
	

	public void run() {
			
			while(enCurso) {
				
				try {
						v.moverMejorado();
						
						Thread.sleep(JuegoSingle.GAMELOOP);
					}
					
				 catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}	
	
			}
			
	}
	
	public void detener() {
		this.enCurso = false;
	}
	
}