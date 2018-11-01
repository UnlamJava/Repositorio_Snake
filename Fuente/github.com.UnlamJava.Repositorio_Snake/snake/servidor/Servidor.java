package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import util.ClienteConn;

public class Servidor {
	
	
	private ServerSocket server;
	private Collection<ClienteConn> clientes;
	
	
	public Servidor(int puerto) {
		
		this.clientes = new ArrayList<>();
		
		try {
			this.server = new ServerSocket(puerto);
			
			System.out.println("SERVER INICIADO - Esperando conexiones de clientes ...");
			
			Socket cliente = server.accept();
			
			Socket clienteSalida = server.accept();
			
			ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
	
			ObjectInputStream in = new ObjectInputStream(clienteSalida.getInputStream());
			
			ClienteConn conn = new ClienteConn(in, out);
			
			System.out.println("Cliente enlazado");
			
			this.clientes.add(conn);
			
			JuegoServer juego = new JuegoServer(clientes);
			
			juego.iniciar();
			
			System.out.println("juego iniciado");
			
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new Servidor(10000);
	}
}
