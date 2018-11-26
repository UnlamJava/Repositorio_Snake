package servidor;


import java.io.IOException;

public class HiloActualizarSala extends Thread {
	
	private Sala sala;

	private boolean on = true;
	
	public HiloActualizarSala(Sala sala) {
		this.sala = sala;
	}

	public void run() {
		while (this.on) {
			try {
				this.sala.enviarSalasAtodos();
				Thread.sleep(1000);
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}

		}
	}
	
	public void detener() {
		this.on = false;
	}
	
}