package servidor;

import java.io.IOException;

public class HiloEnviarPuntaje extends Thread {

	private Juego juego;

	private boolean enCurso;
	
	public HiloEnviarPuntaje(Juego juego) {

		this.juego = juego;
		
		this.enCurso = true;
		
	}

	public void run() {
		try {
			
			while (enCurso) {

				this.juego.enviarPuntajesAtodos();

				Thread.sleep(300);

			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void detener(){
		this.enCurso = false;
	}

}
