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

import snakePKG.Fruta;
import snakePKG.Vibora;
import snakeSinglePlayer.HiloGenerarFruta;
import snakeSinglePlayer.HiloMover;
import snakeSinglePlayer.Juego;

import javax.swing.JLabel;

public class JVentanaGrafica extends JFrame {

	public static final int FRUTA_AGRANDA = 7;

	private JPanelGrafico contentPane;
		
	private Vibora vibora;
	
	private Vibora viboraBot;
	
	private HiloMover hiloMover;
	
	private HiloMover hiloBot;
	
	private int bandera;
	
	private HiloGenerarFruta hGen;
	
	private JLabel lblPuntaje;
	
	public JVentanaGrafica(Integer mapa[][],Vibora vibora,HiloMover hiloMover, HiloMover hiloBot, HiloGenerarFruta hGen, Vibora bot) {

		this.vibora = vibora;
		this.bandera=0;
		this.hiloMover = hiloMover;
		this.hiloBot = hiloBot;
		this.hGen = hGen;
		this.viboraBot = bot;
		
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
			
			if(evento.getKeyCode() == KeyEvent.VK_2) {
				if(Juego.GAMELOOP == 100) return;
				Juego.GAMELOOP -= 100;
			}
			if(evento.getKeyCode() == KeyEvent.VK_1) {
				if(Juego.GAMELOOP == 3000) return;
				Juego.GAMELOOP += 100;
			}


			if (evento.getKeyCode() == KeyEvent.VK_A) {
				res = "Izquierda";
				this.viboraBot.cambiarDir(res);	
			}
			if (evento.getKeyCode() == KeyEvent.VK_D) {
				res = "Derecha";
				this.viboraBot.cambiarDir(res);
			}
			if (evento.getKeyCode() == KeyEvent.VK_W) {
				res = "Arriba";
				this.viboraBot.cambiarDir(res);
			}
			if (evento.getKeyCode() == KeyEvent.VK_S) {
				res = "Abajo";
				this.viboraBot.cambiarDir(res);
			}
		
			
			if (evento.getKeyCode() == KeyEvent.VK_LEFT) {
			
				res = "Izquierda";
				
				if(bandera==0) {
					this.vibora.setDirMov(res);
					this.hiloMover.start();
					this.hiloBot.start();
					this.hGen.start();
					bandera=1;
				}else {
					
					this.vibora.cambiarDir(res);
				}
				
				
			}
			if (evento.getKeyCode() == KeyEvent.VK_RIGHT) {
				res = "Derecha";
				if(bandera==0) {
					this.vibora.setDirMov(res);
					this.hiloMover.start();
					this.hiloBot.start();
					this.hGen.start();
					bandera=1;
				}else {
					
					this.vibora.cambiarDir(res);
				}
			}
			if (evento.getKeyCode() == KeyEvent.VK_UP) {
				res = "Arriba";
				if(bandera==0) {
					this.vibora.setDirMov(res);
					this.hiloMover.start();
					this.hiloBot.start();
					this.hGen.start();
					bandera=1;
				}else {
					
					this.vibora.cambiarDir(res);
				}
			}
			if (evento.getKeyCode() == KeyEvent.VK_DOWN) {
				res = "Abajo";
				if(bandera==0) {
					this.vibora.setDirMov(res);
					this.hiloMover.start();
					this.hiloBot.start();
					this.hGen.start();
					bandera=1;
				}else {
					
					this.vibora.cambiarDir(res);
				}
			}
			
	
	}
	
	
	public void actualizarMapa(Integer[][] mapa) {

		int n = mapa.length;
		Collection<Cuadrado> c = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (mapa[i][j] != 0) {
					c.add(new Cuadrado(new Punto(j, i), mapa[i][j]));//==7
				}
			}
		}

		contentPane.setCuadrados(c);
		lblPuntaje.setText("Puntaje: " + this.vibora.getPuntajeJugador());
		repaint();
	}

	public void dibujarMapa(Integer[][] mapa) {

		int n = mapa.length;
		Collection<Cuadrado> c = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (mapa[i][j] != 0) {
					c.add(new Cuadrado(new Punto(j, i), mapa[i][j]));//==7
				}
			}
		}

		contentPane = new JPanelGrafico(c);
		setContentPane(contentPane);
		
		lblPuntaje = new JLabel("");
		contentPane.add(this.lblPuntaje);
		lblPuntaje.setText("Puntaje: ");

	}
}