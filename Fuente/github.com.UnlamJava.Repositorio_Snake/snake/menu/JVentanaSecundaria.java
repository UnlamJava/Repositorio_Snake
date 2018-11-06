package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loggin.Jugador;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JVentanaSecundaria extends JDialog {

	private JPanel contentPanel;
	private JTextField textFieldNom;
	private JTextField textFieldClave;
	private JLabel lblUssere;
	private JLabel lblPassword;
	private JButton btnNewButton;
	private JVentanaBasica principal;
	private DatosUsuarios db;
	private String opcion;

	public JVentanaSecundaria(final JVentanaBasica principal, String op) {
		setModal(true);
		this.principal = principal;
		contentPanel = new JPanel();

		try {

			db = new DatosUsuarios();
		} catch (Exception e1) {
			e1.getMessage();
		}

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 399, 150);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.orange);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		lblUssere = new JLabel("usuario");
		lblUssere.setBounds(10, 14, 58, 14);
		contentPanel.add(lblUssere);

		lblPassword = new JLabel("clave");
		lblPassword.setBounds(10, 50, 58, 14);
		contentPanel.add(lblPassword);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(80, 11, 287, 20);
		contentPanel.add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldClave = new JTextField();
		textFieldClave.setBounds(80, 50, 287, 20);
		contentPanel.add(textFieldClave);
		textFieldClave.setColumns(10);

		btnNewButton = new JButton("Aceptar");
		if (op.equals("CREAR USUARIO")) {

			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					Jugador player = new Jugador();
					player.setUsuario(textFieldNom.getText());
					player.setClave(textFieldClave.getText());

					if (!(db.setCrearUsuario(player))
							|| (textFieldNom.getText().equals("") || textFieldClave.getText().equals(""))) {

						textFieldNom.setText("");
						textFieldClave.setText("");

						JOptionPane.showMessageDialog(null,
								"UPs,ya existe ese usuario, por favor intente con otros valores");
					}

					principal.escribeTextoEnLabel(textFieldNom.getText());
					dispose();
				}
			});
		} else {
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					Jugador player = new Jugador();
					player.setUsuario(textFieldNom.getText());
					player.setClave(textFieldClave.getText());

					if (!(db.getUsuario(player))
							|| (textFieldNom.getText().equals("") || textFieldClave.getText().equals(""))) {

						textFieldNom.setText("");
						textFieldClave.setText("");

						JOptionPane.showMessageDialog(null,
								"UPs,no se encontro usuario, por favor intente con otros valores");
					}

					principal.escribeTextoEnLabel(textFieldNom.getText());
					dispose();
				}
			});
		}

		btnNewButton.setBounds(278, 80, 89, 23);
		contentPanel.add(btnNewButton);
		setLocationRelativeTo(principal);

		setVisible(true);
	}

}
