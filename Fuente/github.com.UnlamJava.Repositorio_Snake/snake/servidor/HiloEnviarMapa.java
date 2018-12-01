package servidor;

import java.io.IOException;

public class HiloEnviarMapa extends Thread{

	private Juego juego;

	private boolean enCurso;
	
	public HiloEnviarMapa(Juego juego) {

		this.juego = juego;
		
		this.enCurso = true;

	}

	public void run() {
		
		try {

			while (enCurso) {
				
				juego.moverViboras();
				
		
				juego.enviarMapaAtodos();
			
				Thread.sleep(100);// GAME_LOOP

			}
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void detener(){
		
		this.enCurso = false;
		
	}
}
