package cliente;


import java.io.IOException;

import util.ClienteConn;
import util.Mensaje;

public class HiloLecturaMensajes extends Thread{
	
	private Cliente cliente;

	private ClienteConn conn;
	
	public HiloLecturaMensajes(Cliente cliente, ClienteConn conn) {
		
		this.cliente = cliente;
		
		this.conn = conn;
	}
	
	
	public void run() {
		

		while(true) {
			
			Mensaje msg;
		
			try {
				
				msg = conn.recibirInfo();
		
				cliente.interpretarMensaje(msg);

			} catch (ClassNotFoundException | IOException e) {

				e.printStackTrace();
				break;
			}
			
		}
	}
	
}
