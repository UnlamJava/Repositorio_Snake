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
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

public class JVentanaSala extends JFrame {

	private JPanel contentPane;
	private Cliente cli;
	private JList<String> list;
	private DefaultListModel<String> modelList;
	private int idSala;
	
	public JVentanaSala(Cliente cli, int idSala) {
		this.idSala = idSala;
		this.modelList= new DefaultListModel();
		this.cli=cli;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		list.setBounds(33, 71, 299, 163);
		contentPane.add(list);
		
	}
	public void actualizarJugadores(Collection<String> jugadores) {
		
		this.modelList.removeAllElements();
		
		for(String usuario : jugadores) {
			modelList.addElement(usuario);
		}
		
		this.list.setModel(modelList);
	}
}
