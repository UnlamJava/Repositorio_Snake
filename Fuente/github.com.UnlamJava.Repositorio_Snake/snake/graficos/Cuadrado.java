package graficos;

class Cuadrado {
	
	public static final int LADO = 20;
	public static final int FRUTA = 7;
	public static final int JUGADOR_1 = 1;
	public static final int JUGADOR_2 = 2;
	public static final int JUGADOR_3 = 3;
	public static final int JUGADOR_4 = 4;
	public static final int OBSTACULO = 80;
	public static final int NADA = 0;
	
	private Punto pos;
	private int codElemento;
	
	public Cuadrado(Punto pos, int codElemento) {
		this.pos = pos;
		this.codElemento = codElemento;		
	}
	
	public int getX() {
		return pos.getX();
	}
	
	public int getY() {
		return pos.getY();
	}
	
	public boolean esFruta() {
		return this.codElemento == FRUTA;
	}
	
	public boolean esJugador() {
		return (this.codElemento == JUGADOR_1 || this.codElemento == JUGADOR_2);
	}
	
	public boolean esObstaculo() {
		return this.codElemento == OBSTACULO;
	}
	
	public int getcodElemento() {
		return this.codElemento;
	}

	
	
}
