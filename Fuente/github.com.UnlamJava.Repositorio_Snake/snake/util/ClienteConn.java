package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClienteConn {
	private String Usuario;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	
	public ClienteConn(ObjectInputStream cliente, ObjectOutputStream clienteSalida) throws IOException {
		
		this.in = cliente;
		this.out = clienteSalida;
		
	}
	

	public void enviarInfo(Object info) throws IOException {
		this.out.writeObject(info);
	}
	
	public Object recibirInfo() throws ClassNotFoundException, IOException {
		return this.in.readObject();
	}
	
	public void enviarInfo(String id, String mensaje) throws IOException {
		this.out.writeUTF(id);
		this.out.writeUTF(mensaje);;
	}
	
	public Mensaje recibirInfo(String s) throws ClassNotFoundException, IOException {
		return new Mensaje(this.in.readUTF(), this.in.readUTF());
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	
}
