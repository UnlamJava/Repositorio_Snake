package graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import cliente.Cliente;
import joystick.Joystick;
import snakePKG.Fruta;
import snakePKG.Obstaculo;
import util.ClienteConn;
import util.Mensaje;
import util.Puntaje;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JVentanaJuego extends JFrame {

	private static final long serialVersionUID = 1L;

	public static final int FRUTA_AGRANDA = 100;

	private JPanelGrafico panelGrafico;

	private JPanel panelPuntajes;

	private Cliente cli;

	private int idSala;

	private JLabel[] labelsPuntajes;

	private JPanel volver;
	
	private Joystick joystick;

	public JVentanaJuego(Integer mapa[][], Cliente cli, int idSala) {

		super("Ejemplo Básico de Graphics");

		this.cli = cli;

		this.idSala = idSala;
		
		this.joystick = new Joystick(this.cli, this.idSala);
		
		this.labelsPuntajes = new JLabel[6];
		
		new Thread(this.joystick).start();
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				cli.desconectar("Juego" + idSala);
				dispose();
			}
		});

		setResizable(false);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				setMovimiento(arg0);
			}
		});

		this.panelPuntajes = new JPanel();

		this.volver = new JPanel();
		JButton btnVolver = new JButton("Volver");

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cli.salirJuego(idSala);
			}
		});
		btnVolver.setBounds(10, 10, 20, 23);

		panelPuntajes.setBounds(Cuadrado.LADO * mapa.length + 20, 11, 150, Cuadrado.LADO * mapa.length );

		getContentPane().add(panelPuntajes);

		this.panelPuntajes.setBackground(new Color(51, 55, 64));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, Cuadrado.LADO * mapa.length + 40 + this.panelPuntajes.getWidth(),
				Cuadrado.LADO * mapa.length + 50);

		this.getContentPane().setBackground(new Color(140, 0, 0));

		setLocationRelativeTo(null);

		getContentPane().setLayout(null);

		this.dibujarMapa(mapa);

		this.panelPuntajes.setLayout(new BoxLayout(this.panelPuntajes, BoxLayout.PAGE_AXIS));
		this.panelPuntajes.setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 15));

		panelPuntajes.add(btnVolver);

		for (int i = 0; i < 6; i++) {

			this.labelsPuntajes[i] = new JLabel(System.lineSeparator());

			this.panelPuntajes.add(labelsPuntajes[i]);
		}

		this.setFocusable(true);
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
		panelGrafico.repaint();
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

	public static void main(String[] args) throws InterruptedException {

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

		LinkedList<Puntaje> lista = new LinkedList<>();

		lista.add(new Puntaje("A", 1, 100));
		lista.add(new Puntaje("B", 2, 60));
		lista.add(new Puntaje("C", 3, 40));

		jv.actualizarPuntajes(lista);

		Thread.sleep(1000);

		LinkedList<Puntaje> lista2 = new LinkedList<>();

		lista2.add(new Puntaje("A", 1, 100));
		lista2.add(new Puntaje("B", 2, 600));
		lista2.add(new Puntaje("C", 3, 400));

		jv.actualizarPuntajes(lista2);

	}
	
	public Joystick getJoystick() {
		return joystick;
	}
	
	public void actualizarPuntajes(LinkedList<Puntaje> lista) {

		Collections.sort(lista, new Comparator<Puntaje>() {

			@Override
			public int compare(Puntaje o1, Puntaje o2) {
				return o2.getPuntaje() - o1.getPuntaje();
			}
		});

		int i = 0;
		for (Puntaje p : lista) {

			this.labelsPuntajes[i].setForeground(Cuadrado.COLORES[p.getIdVibora() - 1]);
			this.labelsPuntajes[i].setText(p.getNombreUsuario() + ": " + p.getPuntaje() + System.lineSeparator());

			i++;
		}

		for (int j = i; j < this.labelsPuntajes.length; j++) {
			this.labelsPuntajes[j].setText("");
		}

	}
}