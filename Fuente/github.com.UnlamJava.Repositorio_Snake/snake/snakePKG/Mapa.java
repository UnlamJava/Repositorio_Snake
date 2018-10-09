package snakePKG;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Mapa {

	//nombre para diferenciar los distintos mapas q hay disponibles
	public static final String MAPA_1="Arena";
	public static final String MAPA_2="Bosque";
	
	private int tamanioX;
	private int tamanioY;
	private int[][] matriz;//las coordenadas son las pocisiones del objeto, y el entero es el id del objeto
	private ObjetoDelMapa[] objetosDelMapa;
	
	private ArrayList<Vibora> viboras;
	
	public Mapa(String nombreMapa) {		
		if(nombreMapa==MAPA_1) {
			this.tamanioX=50;
			this.tamanioY=50;
			this.matriz=new int [this.tamanioX][this.tamanioY];
			
			//OBJETOS LOS DEFINIMOS NOSOTROS EN EL MAPA..
			this.objetosDelMapa = new ObjetoDelMapa[1];
			this.objetosDelMapa[0]=new Fruta(15, 25, ObjetoDelMapa.ID_FRUTA, Fruta.FRUTA_AGRANDA);

			this.viboras = new ArrayList<Vibora>();
			
			}
	}

	public void agregarVibora(Vibora v) {
		this.viboras.add(v);
	}
	
	public boolean verificarFueraDeLimite(Posicion posicion) {
		int x = posicion.getPosicionX();
		int y = posicion.getPosicionY();
		
		return x < 0 || y < 0 || x > this.tamanioX || y > this.tamanioY;
	}
	
	public boolean verificarColisionesConFruta(Posicion posicion) {
		
		boolean colisiona = false;
		
		for(ObjetoDelMapa o : this.objetosDelMapa) {
			colisiona = o.getPosicion().equals(posicion);			
		}
		
		return colisiona;
	}

	public boolean verificarColisionesConViboras(Vibora v) {
		
		int i = 0;
		while(i < this.viboras.size() && !this.viboras.get(i).verificarSiTienePosicion(v)) {
			i++;
		}
		
		return i < this.viboras.size();
	}
}
