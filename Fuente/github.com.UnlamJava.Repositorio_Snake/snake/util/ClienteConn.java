package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;

public class ClienteConn {
	private String Usuario;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Gson gson = new Gson();
	
	
	public ClienteConn(ObjectInputStream cliente, ObjectOutputStream clienteSalida) throws IOException {
		
		this.in = cliente;
		this.out = clienteSalida;
		
	}
	
/*
	public void enviarInfo(Object info) throws IOException {
		this.out.writeObject(info);
	}
	
	public Object recibirInfo() throws ClassNotFoundException, IOException {
		return this.in.readObject();
	}
*/	
	public void enviarInfo(Mensaje mensaje) throws IOException {
		String salida = gson.toJson(mensaje);
		out.writeUTF(salida);
		out.flush();
	}
	
	public Mensaje recibirInfo() throws ClassNotFoundException, IOException {
		return new Mensaje(this.in.readUTF(), this.in.readUTF());
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	
}
