package servidor;

import java.io.IOException;

import util.ClienteConn;
import util.Mensaje;

public class HiloAtenderCliente extends Thread {

	private ClienteConn conn;
	
	private Servidor sv;
	
	private boolean clienteConectado = true;
	
	public HiloAtenderCliente(ClienteConn conn, Servidor sv) {

		this.conn = conn;
		
		this.sv = sv;
		
	}
	
	public void run() {
		
		Mensaje msg;

		while(clienteConectado) {
			
			 try {
			
				msg = conn.recibirInfo();
			
				if(msg.getNombreMensaje().equals("TerminarConn")) {
					this.clienteConectado = false;
				
				}
		
				sv.interpretarMensaje(msg, conn);
				
			 } catch (ClassNotFoundException | IOException e) {
			
				e.printStackTrace();
				break;
			 }
			 
		}
	}

}
