package graficos;


class Cuadrado {
	
	
	public static final int LADO = 50;
	
	private Punto pos;
	private boolean esFruta;
	
	public Cuadrado(Punto pos, boolean esFruta) {
		this.pos = pos;
		this.esFruta = esFruta;
	}
	
	public int getX() {
		return pos.getX();
	}
	
	public int getY() {
		return pos.getY();
	}
	
	public boolean esFruta() {
		return this.esFruta;
	}
	
	
}
