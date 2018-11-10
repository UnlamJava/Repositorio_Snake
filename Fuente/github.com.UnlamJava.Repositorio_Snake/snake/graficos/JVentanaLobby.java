package graficos;


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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 444);
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		salasPanel = new JPanel();
		salasPanel.setBackground(Color.WHITE);
		salasPanel.setBounds(0, 39, 434, 366);
		mainPanel.add(salasPanel);
		
		JPanel crearSalasPanel = new JPanel();
		crearSalasPanel.setBounds(0, 0, 434, 39);
		mainPanel.add(crearSalasPanel);
		crearSalasPanel.setLayout(null);
		
		this.lblSalas = new JLabel("SALAS");
		lblSalas.setBounds(175, 9, 31, 14);
		crearSalasPanel.add(lblSalas);
		
		idSalaTextField = new JTextField();
		idSalaTextField.setBounds(338, 6, 86, 20);
		crearSalasPanel.add(idSalaTextField);
		idSalaTextField.setColumns(10);
		
		JButton btnCrearSala = new JButton("Crear Sala");
		btnCrearSala.setBounds(245, 5, 83, 23);
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
		
		salasPanel.removeAll();
		
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
}
