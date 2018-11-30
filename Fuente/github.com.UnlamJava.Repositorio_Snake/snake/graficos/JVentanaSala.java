package graficos;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;

import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class JVentanaSala extends JFrame {

	private JPanel contentPane;
	private Cliente cli;
	private JList<String> list;
	private DefaultListModel<String> modelList;
	private int idSala;
	private JButton btnEmpezar;
	
	
	public JVentanaSala(Cliente cli, int idSala, boolean esCreador) {
		
		this.idSala = idSala;
		this.modelList= new DefaultListModel();
		this.cli=cli;
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		/*
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				cli.desconectar("Sala" + idSala);
				dispose();
			}
		});*/
		
		setBounds(100, 100, 559, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sala  " + idSala);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(23, 11, 280, 49);
		contentPane.add(lblNewLabel);
		
		list = new JList(this.modelList);
		list.setBounds(33, 71, 280, 208);
		contentPane.add(list);
		
		if(esCreador) {
			this.activarAdmin();
		}
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(404, 303, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cli.salirDeSala(idSala);			
			}
		});
		contentPane.add(btnVolver);
		
	}
	
	public void actualizarJugadores(Collection<String> jugadores) {
		
		this.modelList.removeAllElements();
		
		for(String usuario : jugadores) {
			modelList.addElement(usuario);
		}
		
		this.list.setModel(modelList);
	}
	
	public int getIdSala() {
		return this.idSala;
	}

	public void activarAdmin() {
		
		this.btnEmpezar = new JButton("Empezar");
		btnEmpezar.setBounds(385, 107, 119, 76);
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cli.empezarPartida(idSala);
			}
		});
		contentPane.add(btnEmpezar);
		contentPane.repaint();
		
	}
}
