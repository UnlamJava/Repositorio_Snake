package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import util.ClienteConn;

public class Servidor {

	protected ServerSocket server;
	protected Collection<ClienteConn> clientes;
	protected Collection<Sala> salas;
	
	public Servidor(int puerto) {

		this.clientes = new ArrayList<>();

		try {

			this.server = new ServerSocket(puerto);
			System.out.println("SERVER INICIADO - Esperando conexiones de clientes ...");
			
			//while (true) {


				Socket cliente = server.accept();

				Socket clienteSalida = server.accept();

				ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

				ObjectInputStream in = new ObjectInputStream(clienteSalida.getInputStream());

				ClienteConn conn = new ClienteConn(in, out);

				System.out.println("Cliente enlazado");

				this.clientes.add(conn);
				
				HiloLectura hl=new HiloLectura(conn, this);
				
				
				
				
				
				JuegoServer juego = new JuegoServer(clientes);

				juego.iniciar();

				System.out.println("juego iniciado");

			//}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Servidor(10000);
	}
}
