package servidor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.google.gson.Gson;

import snakePKG.Mapa;
import snakePKG.Vibora;
import util.ClienteConn;
import util.Mensaje;
import util.Puntaje;

public class Juego {

	// private Mapa mapa;
	private Mapa mapa;

	private HashMap<ClienteConn, Vibora> viboras;

	private Gson gson;

	private LinkedList<Puntaje> puntajes;

	private ArrayList<HiloMoverVibora> hilosViboras;

	private boolean juegoEnCurso;

	private HiloEnviarMapa hiloMapa;

	private HiloEnviarPuntaje hiloPuntos;

	private HiloGenerarFrutas hiloFrutas;

	public Juego(Collection<ClienteConn> clientes) {

		this.gson = new Gson();

		this.viboras = new HashMap<>();

		this.mapa = new Mapa(Mapa.MAPA_1);

		this.puntajes = new LinkedList<>();

		this.hilosViboras = new ArrayList<>();

		Vibora v;

		int i = 1;

		for (ClienteConn cli : clientes) {

			v = new Vibora(i++);

			this.viboras.put(cli, v);

			mapa.ubicarViboraEnMapa(v);

			this.hilosViboras.add(new HiloMoverVibora(v));
		}

	}

	public void iniciar() {

		try {

			this.avisarATodos();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.juegoEnCurso = true;

		this.hiloMapa = new HiloEnviarMapa(this);

		this.hiloPuntos = new HiloEnviarPuntaje(this);

		this.hiloFrutas = new HiloGenerarFrutas(this);

		//HiloMoverViboras hm = new HiloMoverViboras(this);
		
		hiloPuntos.start();

		hiloMapa.start();

		//hm.start();
		
		hiloFrutas.start();
/*
		for (HiloMoverVibora h : this.hilosViboras) {
			h.start();
		}
*/
	}

	public void detenerHilos() {

		this.hiloFrutas.detener();

		this.hiloPuntos.detener();

		this.hiloMapa.detener();
	}

	public boolean juegoEnCurso() {

		if (this.viboras.size() == 0)
			return false;

		boolean vivos = false;

		for (Vibora v : this.viboras.values()) {

			vivos = v.isEstoyVivo() || vivos;
			
			if (vivos) {
				break;
			}
		}

		return vivos;
	}

	private void avisarATodos() throws IOException {

		for (ClienteConn cli : this.viboras.keySet()) {

			cli.enviarInfo(new Mensaje("EmpezarJuego", this.gson.toJson(this.mapa.getMapa())));

		}

	}

	public void terminar() {

		this.hiloMapa.detener();

		this.hiloPuntos.detener();

		for (Vibora v : this.viboras.values()) {
			v.morir();
		}

		this.viboras.clear();

		// los clientes vuelven al lobby
	}

	public void cambiarDir(ClienteConn conn, String dir) {

		this.viboras.get(conn).cambiarDir(dir);

	}

	public Mapa getMapa() {
		
		return this.mapa;

	}

	public void enviarMapaAtodos() throws IOException {

		for (ClienteConn cli : this.viboras.keySet()) {

			cli.enviarInfo(new Mensaje("Mapa", this.gson.toJson(this.mapa.getMapa())));

		}

	}

	public void quitarJugadorJuego(ClienteConn c1) {

		Vibora v = this.viboras.get(c1);

		this.viboras.remove(c1);

		v.morir2();

	}

	public void enviarPuntajesAtodos() throws IOException {

		this.puntajes.clear();

		for (Map.Entry<ClienteConn, Vibora> entry : this.viboras.entrySet()) {

			this.puntajes.add(new Puntaje(entry.getKey().getUsuario(), entry.getValue().getIdVibora(),
					entry.getValue().getPuntajeJugador()));

		}

		for (ClienteConn cli : this.viboras.keySet()) {

			cli.enviarInfo(new Mensaje("Puntajes", this.gson.toJson(this.puntajes)));

		}

	}

	public void moverViboras() {

		for (Vibora v : this.viboras.values()) {

			v.moverMejorado();
		}

	}

	public void generarFrutas() {

		this.mapa.generarFruta();
	}

}
