package ui;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
/**
 * Final page
 * */
public class Finish_8 extends JFrame implements ActionListener {

	private JPanel contentPane;
	JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public Finish_8() {
		this.setTitle("Flight kiosk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		
		Label label = new Label("Congratulations!");
		panel_5.add(label);
		label.setFont(new Font("Dialog", Font.PLAIN, 28));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		
		Label label_1 = new Label("You are all set. Good bye!");
		panel_7.add(label_1);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(this);
		panel_3.add(btnNewButton);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			System.exit(0);
		}
		
	}

}
