package graficos;

import java.awt.Color;

public class Cuadrado {
	
	public static final int LADO = 20;
	public static final int FRUTA = 100;
	public static final int JUGADOR_1 = 1;
	public static final int JUGADOR_2 = 2;
	public static final int JUGADOR_3 = 3;
	public static final int JUGADOR_4 = 4;
	public static final int JUGADOR_5 = 5;
	public static final int JUGADOR_6 = 6;
	public static final int OBSTACULO = 80;
	public static final int NADA = 0;
	
	public static final Color[] COLORES = {Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE,
									Color.YELLOW, Color.MAGENTA};
	
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
		
		return this.codElemento >= 1 && this.codElemento <= 6;
	}
	
	public boolean esObstaculo() {
		return this.codElemento == OBSTACULO;
	}
	
	public int getcodElemento() {
		return this.codElemento;
	}

	
	
}
