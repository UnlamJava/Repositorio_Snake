package servidor;

import snakePKG.Vibora;

public class HiloMoverVibora extends Thread {

	
	private Vibora vibora;
	
	
	public HiloMoverVibora(Vibora vibora) {
		this.vibora = vibora;
	}

	public void run(){
		
		while(vibora.isEstoyVivo()){
		
			vibora.moverMejorado();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
}
