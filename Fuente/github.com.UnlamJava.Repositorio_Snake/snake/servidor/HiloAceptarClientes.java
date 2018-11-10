package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import util.ClienteConn;

class HiloAceptarClientes extends Thread {

	private Servidor sv;

	public HiloAceptarClientes(Servidor sv) {

		this.sv = sv;
	
	}
	
	public void run() {
		
		try {

			while (true) {

				Socket cliente = sv.aceptarCliente();

				ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

				ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());

				ClienteConn conn = new ClienteConn(in, out);

				System.out.println("Cliente enlazado");

				this.sv.agregarCliente(conn);

				HiloAtenderCliente ha = new HiloAtenderCliente(conn, sv);

				ha.start();

			}
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
