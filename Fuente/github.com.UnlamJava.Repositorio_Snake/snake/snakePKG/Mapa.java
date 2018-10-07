package snakePKG;

public class Mapa {

	//nombre para diferenciar los distintos mapas q hay disponibles
	public static final String MAPA_1="Arena";
	public static final String MAPA_2="Bosque";
	protected static final int ID_OBSTACULO=1;
	protected static final int ID_FRUTA=2;
	
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
			this.objetosDelMapa[0]=new Fruta(15, 25, ID_FRUTA);
			this.matriz[15][25]=ID_FRUTA;
			
			
			}
			
			
		}
}
