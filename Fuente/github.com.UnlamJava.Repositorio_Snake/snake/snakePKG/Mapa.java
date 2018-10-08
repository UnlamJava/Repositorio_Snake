package snakePKG;

public class Mapa {

	//nombre para diferenciar los distintos mapas q hay disponibles
	public static final String MAPA_1="Arena";
	public static final String MAPA_2="Bosque";
	
	private int tamanioX;
	private int tamanioY;
	private int[][] matriz;//las coordenadas son las pocisiones del objeto, y el entero es el id del objeto
	private ObjetoDelMapa[] objetosDelMapa;
	
	public Mapa(String nombreMapa) {
		if(nombreMapa==MAPA_1) {
			this.tamanioX=50;
			this.tamanioY=50;
			this.matriz=new int [this.tamanioX][this.tamanioY];
			//OBJETOS LOS DEFINIMOS NOSOTROS EN EL MAPA..
			this.objetosDelMapa[0]=new Fruta(15, 25, ObjetoDelMapa.ID_FRUTA, Fruta.FRUTA_AGRANDA);

			
			
			}
			
			
		}
}
