package graficos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import util.ClienteConn;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private JTextField textFUsuario;
	private JTextField textFContraseña;
	private ClienteConn con;
	
public Login(ClienteConn con) {
		this.con=con;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(40, 40, 79, 27);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(40, 116, 79, 27);
		frame.getContentPane().add(lblContrasea);
		
		textFUsuario = new JTextField();
		textFUsuario.setBounds(129, 43, 120, 20);
		frame.getContentPane().add(textFUsuario);
		textFUsuario.setColumns(10);
		
		textFContraseña = new JTextField();
		textFContraseña.setBounds(129, 119, 120, 20);
		frame.getContentPane().add(textFContraseña);
		textFContraseña.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con.enviarInfo("Iniciar Seccion", textFUsuario.getText());
				} catch (IOException e1) {
					new JOptionPane("Error al ingresar, intentar nuevamente",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIngresar.setBounds(40, 203, 89, 23);
		frame.getContentPane().add(btnIngresar);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(166, 203, 89, 23);
		frame.getContentPane().add(btnRegistrarse);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
		btnCancelar.setBounds(292, 203, 89, 23);
		frame.getContentPane().add(btnCancelar);
	}
}
