package sinlgePlayer;

import snakePKG.Mapa;
import snakePKG.Vibora;

public class HiloMoverBot extends Thread {
	private Vibora v;
	private Vibora principal;
	private Mapa mapa;
	private boolean enCurso = true;

	public HiloMoverBot(Vibora viboraBot, Mapa mapa, Vibora principal) {
		this.v = viboraBot;
		this.mapa = mapa;
		this.principal=principal;
	}

	public void run() {
		while (principal.isEstoyVivo()) {

			try {
				v.cambiarDirBot(this.mapa);
				
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
