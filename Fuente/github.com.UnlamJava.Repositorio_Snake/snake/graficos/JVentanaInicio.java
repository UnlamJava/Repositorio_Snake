package graficos;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;

import cliente.Cliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JVentanaInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public JVentanaInicio(Cliente cli) {

		setTitle("SNAKE - JAVA TEAM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 607, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Jugar Solo");
		btnNewButton_1.setBounds(173, 471, 109, 33);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cli.jugarOffline(true);

			}
		});
		contentPane.add(btnNewButton_1);

		JButton btnNewButton = new JButton("Jugar Online");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cli.estoyEnInicioOff();
				cli.jugarOnline();

			}
		});
		
		btnNewButton.setBounds(292, 471, 109, 33);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("SNAKE - JAVA TEAM");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD | Font.ITALIC, 45));
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setBounds(83, 57, 414, 62);
		contentPane.add(lblNewLabel);
		this.setLocationRelativeTo(null);
	
	}
}
