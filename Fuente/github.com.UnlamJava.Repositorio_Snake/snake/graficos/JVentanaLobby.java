package graficos;

import java.awt.Font;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import servidor.Sala;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;

public class JVentanaLobby extends JFrame {

	private JPanel mainPanel;
	private JLabel lblSalas;
	private JTextField idSalaTextField;
	private JPanel salasPanel;
	private Cliente cli;
	
	public JVentanaLobby(Cliente cli) {
		
		this.cli = cli;
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("SNAKE - LOBBY");
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				cli.desconectar("Lobby");
				dispose();
			}
		});
		
		setBounds(100, 100, 607, 607);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		salasPanel = new JPanel();
		salasPanel.setBackground(Color.DARK_GRAY);
		salasPanel.setBounds(0, 39, 434, 519);
		mainPanel.add(salasPanel);
		
		JPanel crearSalasPanel = new JPanel();
		crearSalasPanel.setBackground(Color.DARK_GRAY);
		crearSalasPanel.setBounds(0, 0, 434, 39);
		mainPanel.add(crearSalasPanel);
		crearSalasPanel.setLayout(null);
		
		this.lblSalas = new JLabel("SALAS");
		lblSalas.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 40));
		lblSalas.setForeground(Color.ORANGE);
		lblSalas.setBounds(58, 5, 159, 34);
		crearSalasPanel.add(lblSalas);
		
		idSalaTextField = new JTextField();
		idSalaTextField.setBounds(338, 6, 86, 20);
		crearSalasPanel.add(idSalaTextField);
		idSalaTextField.setColumns(10);

		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.setBounds(227, 5, 101, 34);
		crearSalasPanel.add(btnCrearSala);
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cli.crearSala(Integer.parseInt(idSalaTextField.getText()));
				
			}
		});
		
	}
	

	public void dibujarSalas(Collection<String> salas) {
		
		int x = 38;
		int y = 49;
		
		int ancho = 300;
		int alto = 60;
		
		
		for(String sala : salas) {
			
			JButton button = new JButton();
			
			button.setBounds(x, y, ancho, alto);
			
			button.setText(sala);
			
			button.addActionListener(new ActionListener() {
			
			Integer idSala;
				public void actionPerformed(ActionEvent e) {
					idSala = Integer.parseInt(sala.substring(sala.indexOf('[') + 1, sala.indexOf(']')));
					cli.unirseASala(idSala);
					
				}
			});
			
			salasPanel.add(button);
			
			salasPanel.repaint();
			
			y += 75;
		}
	
	}


	public void refrescar() {
	
		salasPanel.removeAll();	
		
		salasPanel.repaint();
		
	}
}
