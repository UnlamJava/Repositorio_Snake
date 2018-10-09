package snakePKG;

import java.awt.Rectangle;

public class Posicion extends Rectangle {


	private int posicionX;
	private int posicionY;
	
	
	public Posicion(int posicionX, int posicionY) {
		super(posicionX,posicionY,1,1);//creo un rectangulo en la cebeza de la vibora
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	
	}


	public int getPosicionX() {
		return posicionX;
	}


	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}


	public int getPosicionY() {
		return posicionY;
	}


	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}
	
}
