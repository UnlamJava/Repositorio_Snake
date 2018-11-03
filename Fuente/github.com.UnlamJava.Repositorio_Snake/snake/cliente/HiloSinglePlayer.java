package cliente;

import servidor.Servidor;

public class HiloSinglePlayer extends Thread {
	public void run() {

		Servidor server= new Servidor(10000);
	}
}
