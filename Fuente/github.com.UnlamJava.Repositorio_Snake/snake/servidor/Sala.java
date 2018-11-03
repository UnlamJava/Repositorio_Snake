package servidor;

import java.util.Collection;

import util.ClienteConn;

public class Sala {
	private Collection<ClienteConn> usuarios;

	public Sala(ClienteConn usuarios) {
		this.usuarios.add(usuarios);
	}
	public void agregarUsuario(ClienteConn usuarios) {
		this.usuarios.add(usuarios);
	}
	public void quitarUsuario(ClienteConn usuarios) {
		this.usuarios.remove(usuarios);
	}
	
	
}
