package snakePKG;

import java.util.Arrays;

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
				this.tamanioX=10;
				this.tamanioY=10;
				this.matriz=new int [this.tamanioX][this.tamanioY];
				//OBJETOS LOS DEFINIMOS NOSOTROS EN EL MAPA..
				this.objetosDelMapa = new ObjetoDelMapa[2];
				this.objetosDelMapa[0]=new Fruta(1, 2, Fruta.FRUTA_AGRANDA);
				this.objetosDelMapa[1]=new Fruta(9, 5, Fruta.FRUTA_AGRANDA);
				setMapa();
				}
				
			}
		
		public void setMapa(){
			for(int i=0;i<objetosDelMapa.length;i++)
				this.matriz[this.objetosDelMapa[i].getPosicionY()][this.objetosDelMapa[i].getPosicionX()]=this.objetosDelMapa[i].getIdObjeto();
			
		}
		public int getPosMatriz(Posicion pos){
			
			return this.matriz[pos.getPosicionY()][pos.getPosicionX()];
		}
		
		public void setPosMatriz(Posicion posicion, int idObjeto){
			
			this.matriz[posicion.getPosicionY()][posicion.getPosicionX()]=idObjeto;
		}
		
		public boolean HayFruta(Posicion pos){
			
			return this.matriz[pos.getPosicionY()][pos.getPosicionX()]==Fruta.FRUTA_ACHICA || this.matriz[pos.getPosicionY()][pos.getPosicionX()]==Fruta.FRUTA_AGRANDA;
		}
		
		public void mostrarMapa(){
			
			for(int i=0;i<this.matriz.length;i++)
				System.out.println(Arrays.toString(this.matriz[i]));
		}
		
		public void ubicarViboraEnMapa(Vibora vibora){
			Posicion pos = new Posicion(0,0);
			this.matriz[pos.getPosicionX()][pos.getPosicionY()]=vibora.getIdVibora();
			vibora.getCuerpoVibora(0).setPocision(pos);
		}
}