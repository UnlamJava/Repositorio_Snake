package util;


public class Mensaje {
	
	private String nombreMensaje;
	private String json;

	public Mensaje(String mensaje, String obj) {
		nombreMensaje = mensaje;
		json = obj;
	}

	public void cambiarMensaje(String mensaje, String jsonObj) {
		nombreMensaje = mensaje;
		json = jsonObj;
	}

	public String getNombreMensaje() {
		return nombreMensaje;
	}

	public String getJson() {
		return json;
	}

	public void setNombreMensaje(String mensaje) {
		this.nombreMensaje = mensaje;
	}
}