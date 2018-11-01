package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClienteConn {
	
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

}
