package servidor;

import java.io.IOException;

import snakePKG.Vibora;
import util.ClienteConn;

public class HiloLecturaJuego extends Thread {
	
	private Vibora v;
	private ClienteConn con;
	private boolean juegoOn;
	
	public HiloLecturaJuego(Vibora v, ClienteConn con) {
		this.v = v;
		this.con = con;
		this.juegoOn=true;
	}

	public void run() {
		String dir = "";
		 while(juegoOn) { try {
		  
			 dir = (String) con.recibirInfo();
			 System.out.println(dir);
			 this.v.cambiarDir(dir);
		  
			 Thread.sleep(500);
		  
		  } catch (ClassNotFoundException | IOException | InterruptedException e) {
		  
		  e.printStackTrace(); break; } }
	}

}
