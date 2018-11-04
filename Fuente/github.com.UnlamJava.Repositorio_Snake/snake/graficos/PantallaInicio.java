package graficos;

import java.awt.EventQueue;

import javax.swing.JFrame;

import cliente.Cliente;
import util.ClienteConn;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaInicio {

	private JFrame frame;
	private Cliente c;
	
	
	public PantallaInicio(Cliente c) {
		this.c=c;
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
		
		JButton btnJugarSolo = new JButton("Jugar Solo");
		btnJugarSolo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("boton presionado");
				
				c.jugarSolo();
			}
		});
		btnJugarSolo.setBounds(136, 57, 155, 23);
		frame.getContentPane().add(btnJugarSolo);	
		JButton btnNewButton = new JButton("Conectarse a servidor");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				c.conectarASerivodor();
				Login login=new Login(c.getConn());
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(136, 132, 155, 23);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
}
