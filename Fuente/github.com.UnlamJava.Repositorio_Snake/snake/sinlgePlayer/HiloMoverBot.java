package sinlgePlayer;

import snakePKG.Mapa;
import snakePKG.Vibora;

public class HiloMoverBot extends Thread {
	private Vibora v;
	private Mapa mapa;
	private boolean enCurso = true;

	public HiloMoverBot(Vibora viboraBot, Mapa mapa) {
		this.v = viboraBot;
		this.mapa = mapa;
	}

	public void run() {
		while (v.isEstoyVivo()) {

			try {
				v.cambiarDirBot(this.mapa);
				
				v.moverMejorado();

				Thread.sleep(JuegoSingle.GAMELOOP);
				System.out.println("asdaa");
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
