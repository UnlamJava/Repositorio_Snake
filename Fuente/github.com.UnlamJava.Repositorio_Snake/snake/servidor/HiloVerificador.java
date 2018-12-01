package servidor;

public class HiloVerificador extends Thread {
	private Juego juego;

	private boolean enCurso;

	public HiloVerificador(Juego juego) {

		this.juego = juego;

		this.enCurso = true;

	}

	public void run() {

		while (enCurso) {

			this.juego.getMapa().verificarPosicionDeCabezas();
			
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void detener() {
		this.enCurso = false;
	}

}
