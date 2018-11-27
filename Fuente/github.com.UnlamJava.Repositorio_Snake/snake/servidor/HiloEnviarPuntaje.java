package servidor;

import java.io.IOException;

public class HiloEnviarPuntaje extends Thread {

	private Juego juego;

	public HiloEnviarPuntaje(Juego juego) {

		this.juego = juego;
	}

	public void run() {
		try {
			
			while (true) {

				this.juego.enviarPuntajesAtodos();

				Thread.sleep(300);

			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
