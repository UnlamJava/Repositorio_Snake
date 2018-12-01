package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import util.ClienteConn;

class HiloAceptarClientes extends Thread {

	private Servidor sv;

	public HiloAceptarClientes(Servidor sv) {

		this.sv = sv;

	}

	public void run() {

		while (true) {
			try {
				Socket cliente = sv.aceptarCliente();

				DataOutputStream out = new DataOutputStream(cliente.getOutputStream());

				DataInputStream in = new DataInputStream(cliente.getInputStream());

				ClienteConn conn = new ClienteConn(in, out);

				System.out.println("Cliente enlazado");

				this.sv.agregarCliente(conn);

				HiloAtenderCliente ha = new HiloAtenderCliente(conn, sv);

				ha.start();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

}
