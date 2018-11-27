package util;

import java.io.Serializable;

public class Puntaje implements Serializable {

	private static final long serialVersionUID = 4694417674393784205L;

	private String nombreUsuario;
	
	private int idVibora;
	
	private int puntaje;

	public Puntaje(String nombreUsuario, int idVibora, int puntaje) {
		this.nombreUsuario = nombreUsuario;
		this.idVibora = idVibora;
		this.puntaje = puntaje;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public int getIdVibora() {
		return idVibora;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setIdVibora(int idVibora) {
		this.idVibora = idVibora;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	
	
}
