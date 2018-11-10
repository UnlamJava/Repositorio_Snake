package graficos;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import util.ClienteConn;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JVentanaLogeo extends JDialog {
	
	private JTextField usuarioTextField;
	private JTextField contrase�aTextField;
	
	public JVentanaLogeo(Cliente cli) {
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 251, 434, 10);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
		}
		{
			usuarioTextField = new JTextField();
			usuarioTextField.setBounds(98, 50, 226, 43);
			getContentPane().add(usuarioTextField);
			usuarioTextField.setColumns(10);
		}
		{
			contrase�aTextField = new JTextField();
			contrase�aTextField.setBounds(98, 120, 221, 43);
			getContentPane().add(contrase�aTextField);
			contrase�aTextField.setColumns(10);
		}
		{
			JButton loguearseButton = new JButton("Loguearse");
			loguearseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cli.loguearse(usuarioTextField.getText(), contrase�aTextField.getText());
				}
			});
			loguearseButton.setBounds(98, 197, 89, 23);
			getContentPane().add(loguearseButton);
		}
		{
			JButton registrarseButton = new JButton("Registrarse");
			registrarseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cli.registrarse(usuarioTextField.getText(), contrase�aTextField.getText());
				}
			});
			registrarseButton.setBounds(226, 197, 89, 23);
			getContentPane().add(registrarseButton);
		}
	}

}

