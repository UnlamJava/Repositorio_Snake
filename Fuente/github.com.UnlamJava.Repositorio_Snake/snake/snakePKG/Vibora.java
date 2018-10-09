package snakePKG;

import java.util.ArrayList;

public class Vibora {

	public static final String DIRECCION_DERECHA = "Derecha";
	public static final String DIRECCION_IQUIERDA = "Izquierda";
	public static final String DIRECCION_ARRIBA = "Arriba";
	public static final String DIRECCION_ABAJO = "Abajo";
	private static int TAMANIO_INICIAL = 3;

	private int cantidadFrutaComida;
	private int tamanio;
	private ArrayList<Posicion> posicion;
	private String sentidoDireccion;
	
	private Mapa mapa;
	private int id;
	
	public Vibora(Mapa mapa, int id) {
		this.cantidadFrutaComida = 0;
		this.tamanio = TAMANIO_INICIAL;
		this.posicion = new ArrayList<Posicion>();
		this.mapa = mapa;
		
		mapa.agregarVibora(this);
		this.id = id;
	}

	public String getSentidoDireccion() {
		return sentidoDireccion;
	}
	
	//solamte para testear movimiento vibora
	public void setSentidoDireccion(String sentidoDireccion) {
		this.sentidoDireccion = sentidoDireccion;
	}

	public int getCantidadFrutaComida() {
		return cantidadFrutaComida;
	}

	public int getTamanio() {
		return tamanio;
	}

	public ArrayList<Posicion> getPosicion() {
		return posicion;
	}

	// ubico la vibora en forma horizontal mirando para el centro del mapa;
	public void UbicarViboraEnMapa(int posicionX, int posicionY, int centromapaX) {
		// falta validar la posicion incial que no haya nada y que entre la vivora(esto
		// se realiza en la clase ronda o mapa

		// si la posicion inicial esta a la izquierda del centro del mapa
		if (posicionX <= centromapaX) {
			this.sentidoDireccion = DIRECCION_DERECHA;
			for (int i = 0; i < tamanio; i++)
				this.posicion.add(i, new Posicion(posicionX - i, posicionY));
		}
		// Si estoy a la derecha del centro del mapa
		else {
			this.sentidoDireccion = DIRECCION_IQUIERDA;
			for (int i = 0; i < tamanio; i++)
				this.posicion.add(i, new Posicion(posicionX + i, posicionY));
		}
	}

	// validar si puedo moverme y despues de moverme si choque con algo -- Clase
	// Mapa
	public boolean moverse() {
		// si no apreto ninguna direccion tiene que seguir en la direccion que iba
		this.posicion.remove(tamanio - 1);
		if (this.sentidoDireccion == DIRECCION_ARRIBA)
			this.posicion.add(0,new Posicion(this.posicion.get(0).getPosicionX(), this.posicion.get(0).getPosicionY() + 1));
		if (this.sentidoDireccion == DIRECCION_ABAJO)
			this.posicion.add(0,new Posicion(this.posicion.get(0).getPosicionX(), this.posicion.get(0).getPosicionY() - 1));
		if (this.sentidoDireccion == DIRECCION_DERECHA)
			this.posicion.add(0,new Posicion(this.posicion.get(0).getPosicionX() + 1, this.posicion.get(0).getPosicionY()));
		if (this.sentidoDireccion == DIRECCION_IQUIERDA)
			this.posicion.add(0,new Posicion(this.posicion.get(0).getPosicionX() - 1, this.posicion.get(0).getPosicionY()));
		
		return this.validarCamino();
	}

	public void cambiarDireccion(String direccion) {
		this.sentidoDireccion = direccion;
	}

	public void comerFruta(Fruta fruta) {
		this.cantidadFrutaComida++;
		if(fruta.getIdFruta()==Fruta.FRUTA_AGRANDA)
			this.crecer();
		else 
			this.achicarse();
		
	}
	
	private void achicarse() {
		this.posicion.remove(this.tamanio-1);
		this.tamanio--;
		
	}

	public void crecer() {
		this.tamanio++;
		// si como una fruta le agrego una posicion mas en el sentido que se movia
		if (this.sentidoDireccion == DIRECCION_ARRIBA)
			this.posicion.add(0,new Posicion(this.posicion.get(0).getPosicionX(), this.posicion.get(0).getPosicionY() + 1));
		if (this.sentidoDireccion == DIRECCION_ABAJO)
			this.posicion.add(0,new Posicion(this.posicion.get(0).getPosicionX(), this.posicion.get(0).getPosicionY() - 1));
		if (this.sentidoDireccion == DIRECCION_DERECHA)
			this.posicion.add(0,new Posicion(this.posicion.get(0).getPosicionX() + 1, this.posicion.get(0).getPosicionY()));
		if (this.sentidoDireccion == DIRECCION_IQUIERDA)
			this.posicion.add(0,new Posicion(this.posicion.get(0).getPosicionX() - 1, this.posicion.get(0).getPosicionY()));

	}

	public void choco() {
		this.tamanio = 0;
		this.posicion.clear();

	}

	public boolean validarCamino() {
		
		if(this.mapa.verificarColisionesConFruta(this.posicion.get(0))) {
			this.crecer();
		}
		
		if(this.mapa.verificarFueraDeLimite(this.posicion.get(0)) || this.mapa.verificarColisionesConViboras(this)) {
			this.choco();
			return false;
		}
		
		return true;
	}

	public boolean verificarSiTienePosicion(Vibora vibora) {

		boolean tiene = false;
		int i = (this.id == vibora.id)? 1: 0;
		
		Posicion posicion = vibora.posicion.get(0);
		Posicion pos;
		
		while( i < this.posicion.size()) {
			pos = this.posicion.get(i);
			tiene = posicion.equals(pos);
			if(tiene) {
				break;
			}
			i++;
		}
		
		return tiene;
	}
}
