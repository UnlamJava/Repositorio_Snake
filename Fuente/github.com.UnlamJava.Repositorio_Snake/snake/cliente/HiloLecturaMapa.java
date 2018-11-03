package cliente;

import java.io.IOException;
import graficos.JVentanaGrafica;
import util.ClienteConn;

public class HiloLecturaMapa extends Thread {

	private JVentanaGrafica jVentana;
	
	private boolean salir = false;
	
	private ClienteConn conn;
	
	public HiloLecturaMapa(JVentanaGrafica jVentana, ClienteConn conn) {

		this.jVentana = jVentana;
		
		this.conn = conn;
	}

	public void run() {

		Integer mapa[][];
		
		try {

			mapa = (Integer[][]) this.conn.recibirInfo();
			
			this.jVentana = new JVentanaGrafica(mapa, this.conn); 
			
			this.jVentana.setVisible(true);

			
			while (!salir) { 
				
				mapa = (Integer[][]) this.conn.recibirInfo();
				
				this.jVentana.actualizarMapa(mapa);
				Thread.sleep(500);
			}

		} catch (ClassNotFoundException | InterruptedException | IOException e) {
			
			e.printStackTrace();
			
		}

	}
	
	public void detener() {
	
		this.salir = true;	
	}

}
