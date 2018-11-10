package servidor;


import java.io.IOException;

public class HiloActualizarSala extends Thread {
	private Sala sala;

	public HiloActualizarSala(Sala sala) {
		this.sala = sala;
	}

	public void run() {
		while (true) {
			try {
				this.sala.enviarSalasAtodos();
				Thread.sleep(1000);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				break;
			}

		}
	}
}