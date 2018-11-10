package graficos;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JVentanaInicio extends JFrame {

	private JPanel contentPane;

	public JVentanaInicio(Cliente cli) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Jugar Solo");
		btnNewButton_1.setBounds(138, 76, 109, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Jugar Online");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cli.jugarOnline();
				
			}
		});
		btnNewButton.setBounds(138, 146, 109, 33);
		contentPane.add(btnNewButton);
	}
}

