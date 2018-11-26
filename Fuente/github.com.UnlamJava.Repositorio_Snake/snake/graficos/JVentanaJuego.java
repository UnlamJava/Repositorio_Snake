package graficos;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

import cliente.Cliente;
import util.ClienteConn;

import java.util.ArrayList;
import java.util.Collection;

public class JVentanaJuego extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final int FRUTA_AGRANDA = 7;

	private JPanelGrafico contentPane;

	private Cliente cli;

	private int idSala;
	
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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Cuadrado.LADO * mapa.length + 7, Cuadrado.LADO * mapa.length + 30);
		setBackground(Color.WHITE);
		setLocationRelativeTo(null);
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