package snakeSinglePlayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class template extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					template frame = new template();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public template() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 607);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("crear sala");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(52, 535, 500, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("elegir sala");
		btnNewButton_1.setBounds(52, 501, 500, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("crear usuario");
		btnNewButton_2.setBounds(52, 467, 500, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("ingresar");
		btnNewButton_3.setBounds(52, 433, 500, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("jugar online");
		btnNewButton_4.setBounds(52, 399, 500, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("jugar");
		btnNewButton_5.setBounds(52, 365, 500, 23);
		contentPane.add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 24, 571, 84);
		contentPane.add(lblNewLabel);
	}
}
