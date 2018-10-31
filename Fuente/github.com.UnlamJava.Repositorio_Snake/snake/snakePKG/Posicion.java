package snakePKG;

public class Posicion {
	private int posicionX;
	private int posicionY;
	
	
	public Posicion(int posicionX, int posicionY) {
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
	
public void setPosiciones(int x, int y){
	this.posicionX = x;
	this.posicionY = y;
	
	
}
		public boolean equals(Posicion pos){
			return  pos.posicionX == this.posicionX && 
					pos.posicionY == this.posicionY;
		}


@Override
public String toString() {
	return "[posicionX=" + posicionX + ", posicionY=" + posicionY + "]";
}

public Posicion(Posicion otro){ //CONSTRUCTOR DE COPIA
	this(otro.posicionX,otro.posicionY);
}

}
