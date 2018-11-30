package snakePKG;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//import java.util.Timer;

//import javax.swing.plaf.synth.SynthSeparatorUI;

public class Vibora {

	public static int id = 1;

	private int idVibora;
	private int cantidadFrutaComida;
	// private int tamanio;
	private LinkedList<CuerpoVibora> cuerpo;
	private int desplazamientoEnX;
	private int desplazamientoEnY;
	private int desplazamiento;
	private boolean estoyVivo;
	private String sentidoMovActual;
	private Mapa mapa;
	private int puntajeJugador;
	private Posicion cola;

	public Vibora() {
		this.idVibora = Vibora.id;
		id++;
		this.cantidadFrutaComida = 0;
		this.cuerpo = new LinkedList<CuerpoVibora>();
		CuerpoVibora cabeza = new CuerpoVibora(this.idVibora);
		this.cuerpo.add(0, cabeza);
		// this.tamanio = 1;
		this.desplazamiento = 1;
		this.estoyVivo = true;
		this.puntajeJugador = 0;
	}
	// SOBRECARGA QUE ME PERMITE MANDAR EL ID DE LA VIBORA

	public Vibora(int idViboraJuego) {
		this.idVibora = idViboraJuego;

		this.cantidadFrutaComida = 0;
		this.cuerpo = new LinkedList<CuerpoVibora>();
		CuerpoVibora cabeza = new CuerpoVibora(this.idVibora);
		this.cuerpo.add(0, cabeza);
		// this.tamanio = 1;
		this.desplazamiento = 1;
		this.estoyVivo = true;
		this.sentidoMovActual = "Izquierda";
	}

	public void setDirMov(String dir) {
		this.sentidoMovActual = dir;
	}

	public int getTamanioVibora() {
		return this.cuerpo.size();
	}

	public CuerpoVibora getCuerpoVibora(int pos) {
		return this.cuerpo.get(pos);
	}

	public void cambiarDir(String dir) {

		if (dir.equals(opuestoDe(this.sentidoMovActual))) {
			return;
		}

		if (dir.equals("Arriba")) {
			this.desplazamientoEnX = 0;
			this.desplazamientoEnY = -this.desplazamiento;
		}
		if (dir.equals("Abajo")) {
			this.desplazamientoEnX = 0;
			this.desplazamientoEnY = this.desplazamiento;
		}
		if (dir.equals("Izquierda")) {
			this.desplazamientoEnX = -this.desplazamiento;
			this.desplazamientoEnY = 0;
		}
		if (dir.equals("Derecha")) {
			this.desplazamientoEnX = this.desplazamiento;
			this.desplazamientoEnY = 0;
		}

		this.sentidoMovActual = dir;
	}

	public void moverMejorado() {

		if (!this.estoyVivo)
			return;

		this.cambiarDir(this.sentidoMovActual);

		this.cola = new Posicion(this.cuerpo.get(this.cuerpo.size() - 1).getPocision());

		int x = this.cuerpo.get(0).getPocision().getPosicionX() + this.desplazamientoEnX;
		int y = this.cuerpo.get(0).getPocision().getPosicionY() + this.desplazamientoEnY;

		Posicion newPos = new Posicion(x, y);

		CuerpoVibora c = new CuerpoVibora(this.idVibora);

		c.setPocision(newPos);
		
		this.cuerpo.addFirst(c);
		
		if (this.caminoValido()) {

			

			if (mapa.HayFruta(this.cuerpo.get(0).getPocision()))

				this.crecer();

			else {
				mapa.setPosMatriz(this.cola, 0);

				this.cuerpo.removeLast();
			}

			this.mapa.setPosMatriz(newPos, this.idVibora);

		} else {

			this.morir();

		}

	}

	public Posicion getPosCabeza() {
		return this.cuerpo.get(0).getPocision();
	}

	private String opuestoDe(String sentidoMovActual) {
		String res = "";
		if (sentidoMovActual.equals("Arriba")) {
			res = "Abajo";
		} else if (sentidoMovActual.equals("Derecha")) {
			res = "Izquierda";
		} else if (sentidoMovActual.equals("Izquierda")) {
			res = "Derecha";
		} else if (sentidoMovActual.equals("Abajo")) {
			res = "Arriba";
		}
		return res;
	}

	public boolean caminoValido() {

		if (!mapa.estoyDentroDeMapa(this.cuerpo.get(0).getPocision()))
			return false;

		return mapa.getPosMatriz(this.cuerpo.get(0).getPocision()) == 0
				|| mapa.HayFruta(this.cuerpo.get(0).getPocision());

	}

	/// cambiar el metodo para que creer efectivamente agregue un pedazo de
	/// cuerpo
	public void crecer() {
		this.cantidadFrutaComida++;
		this.puntajeJugador += Fruta.PUNTAJE_FRUTA;
	}

	public int getPuntajeJugador() {
		return puntajeJugador;
	}

	public void setPuntajeJugador(int puntajeJugador) {
		this.puntajeJugador = puntajeJugador;
	}

	public int getIdVibora() {
		return idVibora;
	}

	public void setIdVibora(int idVibora) {
		this.idVibora = idVibora;
	}

	public int getCantidadFrutaComida() {
		return cantidadFrutaComida;
	}

	public void setCantidadFrutaComida(int cantidadFrutaComida) {
		this.cantidadFrutaComida = cantidadFrutaComida;
	}

	public boolean isEstoyVivo() {
		return estoyVivo;
	}

	public void setEstoyVivo(boolean estoyVivo) {
		this.estoyVivo = estoyVivo;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public void morir() {
		
		if(!this.estoyVivo) return;
		
		this.estoyVivo = false;

		this.mapa.LimpiarCuerpoVibora(this.cuerpo);

		mapa.setPosMatriz(this.cola, 0);

		// mapa.setPosMatriz(this.cuerpo.get(0).getPocision(), 0);

		this.cuerpo.clear();
	}

	
	public void morir2(){
		
		if(!this.estoyVivo) return;
		
		this.estoyVivo = false;

		this.mapa.LimpiarCuerpoVibora(this.cuerpo);

		mapa.setPosMatriz(this.cola, 0);

		mapa.setPosMatriz(this.cuerpo.get(0).getPocision(), 0);

		this.cuerpo.clear();
	}
}
