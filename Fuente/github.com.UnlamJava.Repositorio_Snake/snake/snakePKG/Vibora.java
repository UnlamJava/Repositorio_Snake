package snakePKG;

import java.util.ArrayList;

public class Vibora {

	
	public static final String DIRECCION_DERECHA="Derecha";
	public static final String DIRECCION_IQUIERDA="Izquierda";
	public static final String DIRECCION_ARRIBA="Arriba";
	public static final String DIRECCION_ABAJO="Abajo";
	private static int TAMANIO_INICIAL = 3;
	
	private int cantidadFrutaComida;
	private int tamanio;
	private ArrayList<Posicion> posicion;
	private String sentidoDireccion;

	public Vibora() {
		this.cantidadFrutaComida = 0;
		this.tamanio = TAMANIO_INICIAL;
		this.posicion = new ArrayList();
	}
	
	public int getCantidadFrutaComida() {
		return cantidadFrutaComida;
	}

	public void setCantidadFrutaComida(int cantidadFrutaComida) {
		this.cantidadFrutaComida = cantidadFrutaComida;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public ArrayList<Posicion> getPosicion() {
		return posicion;
	}
	
	//ubico la vibora en forma horizontal mirando para el centro del mapa;
	public void UbicarViboraEnMapa(int posicionX, int posicionY, int centromapaX) {
		//falta validar la posicion incial que no haya nada y que entre la vivora(esto se realiza en la clase ronda o mapa
		
		
		//si la posicion inicial esta a la izquierda del centro del mapa
		if(posicionX<=centromapaX) {
			this.sentidoDireccion=DIRECCION_DERECHA;
			for(int i=0;i<tamanio;i++)
				this.posicion.add(i,new Posicion(posicionX-i, posicionY));
		}
		//Si estoy a la derecha del centro del mapa
		else {
			this.sentidoDireccion=DIRECCION_IQUIERDA;
			for(int i=0;i<tamanio;i++)
				this.posicion.add(i,new Posicion(posicionX+i, posicionY));
		}
	}
	
	
	//validar si puedo moverme y despues de moverme si choque con algo -- Clase Mapa
	public void Moverse(String direccion) {
			//si no apreto ninguna direccion tiene que seguir en la direccion que iba
			if(direccion!="")
				this.sentidoDireccion=direccion;
			
			this.posicion.remove(tamanio-1);
			if(this.sentidoDireccion==DIRECCION_ARRIBA)
				this.posicion.add(0, new Posicion(this.posicion.get(0).getPosicionX(), this.posicion.get(0).getPosicionY()+1) );	
			if(this.sentidoDireccion==DIRECCION_ABAJO)
				this.posicion.add(0, new Posicion(this.posicion.get(0).getPosicionX(), this.posicion.get(0).getPosicionY()-1) );
			if(this.sentidoDireccion==DIRECCION_DERECHA)
				this.posicion.add(0, new Posicion(this.posicion.get(0).getPosicionX()+1, this.posicion.get(0).getPosicionY()) );	
			if(this.sentidoDireccion==DIRECCION_IQUIERDA)
				this.posicion.add(0, new Posicion(this.posicion.get(0).getPosicionX()-1, this.posicion.get(0).getPosicionY()) );	
		}
		
	public void ComerFruta() {
		this.cantidadFrutaComida++;
		this.tamanio++;
		//si como una fruta le agrego una posicion mas en el sentido que se movia
		if(this.sentidoDireccion==DIRECCION_ARRIBA)
			this.posicion.add(0, new Posicion(this.posicion.get(0).getPosicionX(), this.posicion.get(0).getPosicionY()+1) );	
		if(this.sentidoDireccion==DIRECCION_ABAJO)
			this.posicion.add(0, new Posicion(this.posicion.get(0).getPosicionX(), this.posicion.get(0).getPosicionY()-1) );
		if(this.sentidoDireccion==DIRECCION_DERECHA)
			this.posicion.add(0, new Posicion(this.posicion.get(0).getPosicionX()+1, this.posicion.get(0).getPosicionY()) );	
		if(this.sentidoDireccion==DIRECCION_IQUIERDA)
			this.posicion.add(0, new Posicion(this.posicion.get(0).getPosicionX()-1, this.posicion.get(0).getPosicionY()) );	
	
	}
	public void Choco() {
		this.tamanio=0;
		this.posicion.clear();
		
	}
	public void validarCamino() {
		
	}
}
