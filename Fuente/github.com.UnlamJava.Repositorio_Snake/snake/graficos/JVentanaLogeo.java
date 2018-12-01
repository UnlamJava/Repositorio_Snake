package graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import util.ClienteConn;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
public class JVentanaLogeo extends JDialog {

	private JTextField usuarioTextField;
	private JTextField contraseñaTextField;



	public JVentanaLogeo(Cliente cli) {
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("SNAKE - LOGIN");
		
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				cli.desconectar("Login");
				dispose();
			}
		});
		
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{

			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 261, 434, 0);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
		}
		{
			usuarioTextField = new JTextField();
			usuarioTextField.setBounds(98, 50, 221, 43);
			getContentPane().add(usuarioTextField);
			usuarioTextField.setColumns(10);
		}
		{
			contraseñaTextField = new JTextField();
			contraseñaTextField.setBounds(98, 120, 221, 43);
			getContentPane().add(contraseñaTextField);
			contraseñaTextField.setColumns(10);
		}
		{
			JButton loguearseButton = new JButton("Loguearse");
			loguearseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
						if(usuarioTextField.getText().equals("")|| contraseñaTextField.getText().equals("")) 
							JOptionPane.showMessageDialog(null,"UPs, por favor complete todos los campos");					
						
						else 							
							cli.loguearse(usuarioTextField.getText(), contraseñaTextField.getText());
					
					
				}
			});
			loguearseButton.setBounds(98, 197, 103, 23);
			getContentPane().add(loguearseButton);
		}
		{
			JButton registrarseButton = new JButton("Registrarse");
			registrarseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					

						if(usuarioTextField.getText().equals("")||contraseñaTextField.getText().equals(""))
							JOptionPane.showMessageDialog(null,"UPs, por favor complete todos los campos");		
						else 
							cli.registrarse(usuarioTextField.getText(), contraseñaTextField.getText());

				}
			});
			registrarseButton.setBounds(211, 197, 104, 23);
			getContentPane().add(registrarseButton);
		
		}
		getContentPane().setBackground(Color.DARK_GRAY);
		{
			JLabel lblNewLabel = new JLabel("CREDENCIALES");
			lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 20));
			lblNewLabel.setForeground(Color.ORANGE);
			lblNewLabel.setBounds(98, 11, 219, 30);
			getContentPane().add(lblNewLabel);
			
		}
		this.setLocationRelativeTo(null);
	
	}
	
	public void mostrarError(String mensaje,String titulo) {
		JOptionPane.showMessageDialog(null,mensaje,titulo,JOptionPane.WARNING_MESSAGE);
	}

}
