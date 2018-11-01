package cliente;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import util.ClienteConn;

public class Cliente {
	
	private Socket entrada;
	private Socket salida;
	
	private ClienteConn conn;
	
	public Cliente(String ip, int port) {
		
		// Conectado
		
		try {
			
			// Inicializo sockets
			
			this.entrada = new Socket(ip, port);
			
			this.salida = new Socket(ip, port);
			
		    ObjectOutputStream out = new ObjectOutputStream(this.salida.getOutputStream());
			
			ObjectInputStream in = new ObjectInputStream(this.entrada.getInputStream());
			
			this.conn = new ClienteConn(in, out);
			
			System.out.println("Conectado al sv");
			
			// Empezar a usar a partir de ACA, para intercambiar datos con el sv
			// utilizar el Objecto conn ( connection) que tiene los metodos recibirInfo()
			// y enviarInfo(), no olvidar castearla en donde es invocada.

			// VentanaLogeo - Validar usuario - aca se puede mostrar una ventana para el logeo
			// y con el objecto conn enviar al sv el usuario y la pass
			
			// Ventana para mostrar salas - Boton entrar 
	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
		
	
		// iniciarJuego(); se ejecuta al tocar el boton entrar - enrealidad se ejecuta 
		// cuando el server diga que la sala esta lista para empezar.	
		this.iniciarJuego();
		
	}
	
	
	private void iniciarJuego(){
		
		try { // El server me avisa cuando empieza el juego
			Boolean res = (Boolean) this.conn.recibirInfo();
			
			System.out.println(res);
			
		} catch (ClassNotFoundException | IOException e) {
	
			e.printStackTrace();
		}
		
		Juego juegoSnake = new Juego(this.conn);
		
		juegoSnake.iniciar(); // Muestra Ventana del Mapa y empieza el juego
		
		// Sale de iniciar cuando terminen las rondas, muera, salga a voluntad, u otro criterio.
		
		// Vuelve a las salas - Ventana para motrar sala
	}
	
	
	
	public static void main(String[] args) {
		
		new Cliente("localhost", 10000); // Me conecto al server
		
	}
	
}
