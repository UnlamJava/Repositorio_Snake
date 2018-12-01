package servidor;

public class HiloMoverViboras extends Thread {

	private Juego juego;

	public HiloMoverViboras(Juego juego) {

		this.juego = juego;
	}

	public void run() {

		try {
			
			while (true) {

				this.juego.moverViboras();

				Thread.sleep(1000);

			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}