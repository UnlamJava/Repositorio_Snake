package cliente;


import java.io.IOException;

import util.ClienteConn;
import util.Mensaje;

public class HiloLecturaMensajes extends Thread{
	
	private Cliente cliente;

	private ClienteConn conn;
	
	private boolean clienteConectado = true;
	
	public HiloLecturaMensajes(Cliente cliente, ClienteConn conn) {
		
		this.cliente = cliente;
		
		this.conn = conn;
	}
	
	
	public void run() {
		

		while(clienteConectado) {
			
			Mensaje msg;
		
			try {
				
				msg = conn.recibirInfo();
		
				if(msg.getNombreMensaje().equals("TerminarOk")) {
					this.clienteConectado = false;
				}
				
				cliente.interpretarMensaje(msg);

			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Perdida de Mensaje");
			
			}
			
		}
	}
	
	
	
}
