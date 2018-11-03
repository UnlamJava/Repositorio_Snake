package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import graficos.JVentanaGrafica;
import util.ClienteConn;

public class Juego {
	
	private JVentanaGrafica jVentana;

	private ClienteConn conn;
	
	public Juego(ClienteConn conn) {
		
		this.conn = conn;
	}
	
	public void iniciar() {
		
		System.out.println("juego cliente iniciado");
		
		HiloLecturaMapa hl = new HiloLecturaMapa(this.jVentana, this.conn);
		HiloEnviarInfo he;// = new Hil(jVentana, conn)
		hl.start();
		
		Boolean juegoOn = true;
		
		while(juegoOn){
			
		}
		
	}
	
	
	
}
