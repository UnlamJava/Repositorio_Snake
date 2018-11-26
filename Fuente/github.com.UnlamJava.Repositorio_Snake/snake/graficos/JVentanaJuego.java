package graficos;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;

import cliente.Cliente;
import snakePKG.Fruta;
import snakePKG.Obstaculo;
import util.ClienteConn;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class JVentanaJuego extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final int FRUTA_AGRANDA = 7;

	private JPanelGrafico panelGrafico;

	private JPanel panelPuntajes;

	private Cliente cli;

	private int idSala;

	private JLabel lblPuntaje;

	public JVentanaJuego(Integer mapa[][], Cliente cli, int idSala) {

		super("Ejemplo Básico de Graphics");

		this.cli = cli;

		this.idSala = idSala;

		setResizable(false);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				setMovimiento(arg0);
			}
		});

		this.panelPuntajes = new JPanel();

		panelPuntajes.setBounds(Cuadrado.LADO * mapa.length + 20, 11, 150, Cuadrado.LADO * mapa.length);

		getContentPane().add(panelPuntajes);

		this.panelPuntajes.setBackground(new Color(51, 55, 64));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, Cuadrado.LADO * mapa.length + 40 + this.panelPuntajes.getWidth(),
				Cuadrado.LADO * mapa.length + 50);

		this.getContentPane().setBackground(new Color(140, 0, 0));

		setLocationRelativeTo(null);

		getContentPane().setLayout(null);

		this.dibujarMapa(mapa);
	}

	public void setMovimiento(KeyEvent evento) {

		if (evento.getKeyCode() == KeyEvent.VK_LEFT) {

			cli.enviarTeclaIzquierda(idSala);
		}
		if (evento.getKeyCode() == KeyEvent.VK_RIGHT) {
			cli.enviarTeclaDerecha(idSala);
		}
		if (evento.getKeyCode() == KeyEvent.VK_UP) {
			cli.enviarTeclaArriba(idSala);
		}
		if (evento.getKeyCode() == KeyEvent.VK_DOWN) {

			cli.enviarTeclaAbajo(idSala);
		}

	}

	public void actualizarMapa(Integer[][] mapa) {

		int n = mapa.length;
		Collection<Cuadrado> c = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				c.add(new Cuadrado(new Punto(j, i), mapa[i][j]));

			}
		}

		panelGrafico.setCuadrados(c);
		repaint();
	}

	public void dibujarMapa(Integer[][] mapa) {

		int n = mapa.length;
		Collection<Cuadrado> c = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				c.add(new Cuadrado(new Punto(j, i), mapa[i][j]));

			}
		}

		this.panelGrafico = new JPanelGrafico(c);
		panelGrafico.setBounds(10, 11, Cuadrado.LADO * mapa.length, Cuadrado.LADO * mapa.length);
		getContentPane().add(panelGrafico);

	}

	public static void main(String[] args) {

		Integer[][] mapa = new Integer[35][35];

		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa.length; j++) {
				mapa[i][j] = (int) (Math.random() * 2);
			}
		}

		mapa[14][15] = Fruta.FRUTA_AGRANDA;
		mapa[20][20] = Obstaculo.ID_OBSTACULO;

		JVentanaJuego jv = new JVentanaJuego(mapa, null, 12);
		jv.setVisible(true);

	}

	public void actualizarPuntajes(Integer[] puntajes) {

	}
}