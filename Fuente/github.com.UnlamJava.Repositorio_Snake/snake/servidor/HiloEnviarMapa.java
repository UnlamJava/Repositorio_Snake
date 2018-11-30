package servidor;

import java.io.IOException;

public class HiloEnviarMapa extends Thread{

	private Juego juego;

	public HiloEnviarMapa(Juego juego) {

		this.juego = juego;

	}

	public void run() {
		
		try {

			while (true) {
				
								
				juego.mapa.generarFruta();
				
				juego.enviarMapaAtodos();

				Thread.sleep(300);// GAME_LOOP

			}
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
