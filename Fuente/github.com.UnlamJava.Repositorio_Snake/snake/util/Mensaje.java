package util;

public class Mensaje {
	private String id;
	private String contenido;
	public Mensaje(String id, String contenido) {
		this.id = id;
		this.contenido = contenido;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	
}
