package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Window;

public class JVentanaBasica extends JFrame {

	private JPanel contentPane;
	private JButton btnCrearUser;
	private JButton btnElegirSala;
	private JButton btnCrearSala;
	private JLabel nombreJugador;
	private JButton btnIngrear;
	public void setOpcionElegida(String opcionElegida) {
		this.opcionElegida = opcionElegida;
	}

	private JButton btnJugar;
	private JButton btnJugarOnline;

	// VARIABLE USADA POR EL MENU
	private String opcionElegida;

	/**
	 * Create the frame.
	 */
	public JVentanaBasica() {

		setTitle("SNAKE - VERSION BETA");
		opcionElegida = "Inicio";
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 607);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.orange);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		nombreJugador = new JLabel("SANKE - JAVA TEAM");
		nombreJugador.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 41));
		nombreJugador.setToolTipText("");
		nombreJugador.setForeground(Color.BLUE);
		nombreJugador.setHorizontalAlignment(SwingConstants.CENTER);
		nombreJugador.setBounds(10, 24, 571, 84);
		contentPane.add(nombreJugador);

		btnCrearUser = new JButton("CREAR USUARIO");
		btnCrearUser.setBounds(52, 467, 500, 23);
		btnCrearUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaSecundaria("CREAR USUARIO");
			}
		});
		contentPane.setLayout(null);
		btnCrearUser.setEnabled(false);
		contentPane.add(btnCrearUser);

		btnElegirSala = new JButton("ELEGIR SALA");
		btnElegirSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnElegirSala.setBounds(52, 501, 500, 23);
		contentPane.add(btnElegirSala);

		btnCrearSala = new JButton("CREAR SALA");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearSala.setBounds(52, 535, 500, 23);
		contentPane.add(btnCrearSala);
		btnElegirSala.setEnabled(false);
		btnCrearSala.setEnabled(false);

		btnIngrear = new JButton("INGRESAR");
		btnIngrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaSecundaria("INGRESAR");
			}
		});
		btnIngrear.setBounds(52, 433, 500, 23);
		btnIngrear.setEnabled(false);
		contentPane.add(btnIngrear);

		btnJugarOnline = new JButton("JUGAR ONLINE");
		btnJugarOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCrearUser.setEnabled(true);
				btnElegirSala.setEnabled(true);
				btnCrearSala.setEnabled(true);
				btnIngrear.setEnabled(true);
				btnJugarOnline.setEnabled(false);
				btnJugar.setEnabled(false);

			}
		});
		btnJugarOnline.setBounds(52, 399, 500, 23);
		contentPane.add(btnJugarOnline);

		btnJugar = new JButton("JUGAR");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opcionElegida = "JUGAR";
				setVisible(false);
				//dispose();
				

			}
		});
		btnJugar.setBounds(52, 365, 500, 23);
		contentPane.add(btnJugar);

		setLocationRelativeTo(null);

	}

	public static JVentanaBasica iniciandoMenu() {
		JVentanaBasica menu = new JVentanaBasica();
		menu.setVisible(true);
		return menu;
	}

	private void abrirVentanaSecundaria(String boton) {
		new JVentanaSecundaria(this, boton);
	}

	public void escribeTextoEnLabel(String texto) {

		if (!texto.isEmpty()) {
			nombreJugador.setText("Hola " + texto);
			btnCrearUser.setEnabled(true);
			btnElegirSala.setEnabled(true);
			btnCrearSala.setEnabled(true);
			btnIngrear.setEnabled(true);
			btnJugar.setEnabled(true);
		}
	}

	public String opcionDeMenu() {

		return this.opcionElegida;
	}

}
