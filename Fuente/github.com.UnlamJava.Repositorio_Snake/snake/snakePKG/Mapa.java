package snakePKG;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Mapa {

	public static final String MAPA_1 = "Arena";
	public static final String MAPA_2 = "Bosque";

	private int tamanioX;
	private int tamanioY;
	private Integer[][] matriz;// las coordenadas son las pocisiones del objeto, y el entero es el id del
								// objeto
	private ObjetoDelMapa[] objetosDelMapa;

	private ArrayList<Vibora> ListaViboras;

	public Mapa(String nombreMapa) {

		if (nombreMapa == MAPA_1) {
			this.tamanioX = 30;
			this.tamanioY = 30;

			this.matriz = new Integer[this.tamanioX][this.tamanioY];

			for (int i = 0; i < this.matriz.length; i++) {
				for (int j = 0; j < this.matriz.length; j++) {
					this.matriz[i][j] = 0;
				}
			}

			// OBJETOS LOS DEFINIMOS NOSOTROS EN EL MAPA..
			this.objetosDelMapa = new ObjetoDelMapa[6];
			this.objetosDelMapa[0] = new Fruta(1, 2, Fruta.FRUTA_AGRANDA);
			this.objetosDelMapa[1] = new Fruta(6, 3, Fruta.FRUTA_AGRANDA);
			this.objetosDelMapa[2] = new Fruta(8, 8, Fruta.FRUTA_AGRANDA);
			this.objetosDelMapa[3] = new Obstaculo(15, 15, Obstaculo.ID_OBSTACULO);
			this.objetosDelMapa[4] = new Obstaculo(15, 16, Obstaculo.ID_OBSTACULO);
			this.objetosDelMapa[5] = new Obstaculo(15, 17, Obstaculo.ID_OBSTACULO);

			setMapa();

			this.ListaViboras = new ArrayList<Vibora>();

		}

	}

	public void setMapa() {
		for (int i = 0; i < objetosDelMapa.length; i++)
			this.matriz[this.objetosDelMapa[i].getPosicionY()][this.objetosDelMapa[i]
					.getPosicionX()] = this.objetosDelMapa[i].getIdObjeto();

	}

	public void generarFruta() {

		int x = (int) (Math.random() * this.tamanioX);
		int y = (int) (Math.random() * this.tamanioY);

		while (this.matriz[x][y] != 0) {
			x = (int) (Math.random() * this.tamanioX);
			y = (int) (Math.random() * this.tamanioY);
		}

		this.matriz[x][y] = Fruta.FRUTA_AGRANDA;

	}

	public void verificarPosicionDeCabezas() {

		boolean mate;

		for (int i = 0; i < this.ListaViboras.size() - 1; i++) {

			mate = false;

			if (this.ListaViboras.get(i).isEstoyVivo()) {

				for (int j = 1; j < this.ListaViboras.size(); j++) {

					if (this.ListaViboras.get(j).isEstoyVivo()) {

						if (this.ListaViboras.get(i).getPosCabeza().equals(this.ListaViboras.get(j).getPosCabeza())) {
							this.ListaViboras.get(j).morir();
							mate = true;
						}

					}

					if (mate) {

						this.matriz[this.ListaViboras.get(i).getPosCabeza().getPosicionY()][this.ListaViboras.get(i)
								.getPosCabeza().getPosicionX()] = 0;
						this.ListaViboras.get(i).morir();
					}
				}
			}

		}

	}

	public boolean estoyDentroDeMapa(Posicion pos) {
		if ((pos.getPosicionX() >= this.tamanioX || pos.getPosicionX() < 0)
				|| (pos.getPosicionY() >= this.tamanioY || pos.getPosicionY() < 0))
			return false;
		return true;

	}

	public int getPosMatriz(Posicion pos) {

		return this.matriz[pos.getPosicionY()][pos.getPosicionX()];
	}

	public void setPosMatriz(Posicion posicion, int idObjeto) {

		this.matriz[posicion.getPosicionY()][posicion.getPosicionX()] = idObjeto;
	}

	public boolean HayFruta(Posicion pos) {

		return this.matriz[pos.getPosicionY()][pos.getPosicionX()] == Fruta.FRUTA_ACHICA
				|| this.matriz[pos.getPosicionY()][pos.getPosicionX()] == Fruta.FRUTA_AGRANDA;
	}

	public void mostrarMapa() {

		for (int i = 0; i < this.matriz.length; i++)
			System.out.println(Arrays.toString(this.matriz[i]));
	}

	public void ubicarViboraEnMapa(Vibora vibora){
	
		int x, y;
		double dist;
		
		int otroX;
		int otroY;
		
		boolean posOk = true;
		
		do {
			
			posOk = true;
			x = (int) (Math.random() * this.tamanioX);
			y = (int) (Math.random() * this.tamanioY);
			
			for(Vibora v : this.ListaViboras) {
					
				otroX = v.getPosCabeza().getPosicionX();
				otroY = v.getPosCabeza().getPosicionY();
				
				dist = Math.sqrt(Math.pow(x - otroX, 2) + Math.pow(y - otroY, 2));
			
				if(dist < 5) {
					posOk = false;
				}
			}
				
		}while(!posOk);
		
		vibora.setMapa(this);
		
		Posicion pos = new Posicion(x,y);
		
		this.matriz[y][x] = vibora.getIdVibora();

		vibora.getCuerpoVibora(0).setPocision(pos);
	
		this.ListaViboras.add(vibora);	
		
		
	}

	public void ubicarViboraEnMapa(Vibora vibora, int x, int y) {

		vibora.setMapa(this);

		this.matriz[y][x] = vibora.getIdVibora();

		vibora.getCuerpoVibora(0).setPocision(new Posicion(x, y));

		this.ListaViboras.add(vibora);

	}

	public Vibora getViboraDePosicion(Posicion pos) {

		int idVibora = this.matriz[pos.getPosicionY()][pos.getPosicionX()];

		for (int i = 0; i < this.ListaViboras.size(); i++) {
			if (this.ListaViboras.get(i).getIdVibora() == idVibora)
				return this.ListaViboras.get(i);
		}

		return null;
	}

	public int getTamanioX() {
		return tamanioX;
	}

	public void setTamanioX(int tamanioX) {
		this.tamanioX = tamanioX;
	}

	public int getTamanioY() {
		return tamanioY;
	}

	public void setTamanioY(int tamanioY) {
		this.tamanioY = tamanioY;
	}

	public Integer[][] getMapa() {
		return this.matriz;
	}

	public void LimpiarCuerpoVibora(List<CuerpoVibora> cuerpoViboraMuerta) {

		for (int i = 1; i < cuerpoViboraMuerta.size(); i++)
			this.matriz[cuerpoViboraMuerta.get(i).getPocision().getPosicionY()][cuerpoViboraMuerta.get(i).getPocision()
					.getPosicionX()] = 0;

	}

}