package graficos;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

//import util.ClienteConn;

import java.util.ArrayList;
import java.util.Collection;
import snakePKG.Vibora;
import snakeSinglePlayer.HiloMover;

public class JVentanaGrafica extends JFrame {

	public static final int FRUTA_AGRANDA = 7;

	private JPanelGrafico contentPane;
	
	//private ClienteConn conn;
	
	private Vibora vibora;
	
	private HiloMover hiloMover;
	
	private int bandera;
	
	public JVentanaGrafica(Integer mapa[][],Vibora vibora,HiloMover hiloMover/*, ClienteConn conn*/) {

		this.vibora = vibora;
		this.bandera=0;
		this.hiloMover = hiloMover;
		
		//super("Ejemplo Básico de Graphics");
		
		//this.conn = conn;
		
		setResizable(false);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				setMovimiento(arg0);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Cuadrado.LADO * mapa.length + 7, Cuadrado.LADO * mapa.length + 30);
		setBackground(Color.ORANGE);
		setLocationRelativeTo(null);
		this.dibujarMapa(mapa);
	}

	public void setMovimiento(KeyEvent evento) {
		
			
			String res;
			
			if (evento.getKeyCode() == KeyEvent.VK_LEFT) {
				res = "Izquierda";
				//conn.enviarInfo(res);
				this.vibora.cambiarDir(res);
				
				if(bandera==0) {
					this.hiloMover.start();
					bandera=1;
				}
			}
			if (evento.getKeyCode() == KeyEvent.VK_RIGHT) {
				res = "Derecha";
				//conn.enviarInfo(res);
				this.vibora.cambiarDir(res);
				
				if(bandera==0) {
					this.hiloMover.start();
					bandera=1;
				}
			}
			if (evento.getKeyCode() == KeyEvent.VK_UP) {
				res = "Arriba";
				//conn.enviarInfo(res);
				this.vibora.cambiarDir(res);
				
				if(bandera==0) {
					this.hiloMover.start();
					bandera=1;
				}
			}
			if (evento.getKeyCode() == KeyEvent.VK_DOWN) {
				res = "Abajo";
				//conn.enviarInfo(res);
				this.vibora.cambiarDir(res);
				
				if(bandera==0) {
					this.hiloMover.start();
					bandera=1;
				}
			}
			
	
	}

	public void actualizarMapa(Integer[][] mapa) {

		int n = mapa.length;
		Collection<Cuadrado> c = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (mapa[i][j] != 0) {
					c.add(new Cuadrado(new Punto(j, i), mapa[i][j] == 7));
				}
			}
		}

		contentPane.setCuadrados(c);
		repaint();
	}

	public void dibujarMapa(Integer[][] mapa) {

		int n = mapa.length;
		Collection<Cuadrado> c = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (mapa[i][j] != 0) {
					c.add(new Cuadrado(new Punto(j, i), mapa[i][j] == 7));
				}
			}
		}

		contentPane = new JPanelGrafico(c);
		setContentPane(contentPane);

	}
}