package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;


public class ClienteConn {
	
	private String Usuario;
	
	private ObjectInputStream in;

	private ObjectOutputStream out;
	
	private Gson gson;
	
	
	public ClienteConn(ObjectInputStream cliente, ObjectOutputStream clienteSalida) throws IOException {
		
		this.Usuario= "Player";
		
		this.in = cliente;
		
		this.out = clienteSalida;
	
		this.gson = new Gson();
	}

	public void enviarInfo(Mensaje mensaje) throws IOException {
		
		String salida = gson.toJson(mensaje);
		
		this.out.writeUTF(salida);
		
		this.out.flush();
		
	}
	
	public Mensaje recibirInfo() throws ClassNotFoundException, IOException {
	
		String entrada = this.in.readUTF();
		
		Mensaje msg = gson.fromJson(entrada, Mensaje.class);
		
		return msg;
	}

	public String getUsuario() {
		
		return Usuario;
		
	}

	public void setUsuario(String usuario) {
		
		Usuario = usuario;
		
	}

	
}
