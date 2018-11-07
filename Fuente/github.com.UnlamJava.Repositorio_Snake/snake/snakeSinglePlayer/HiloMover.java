package snakeSinglePlayer;

import java.util.Map.Entry;

import snakePKG.Vibora;

import snakePKG.Mapa;
//import util.ClienteConn;

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
						v.mover(mapa);

						Thread.sleep(100);
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