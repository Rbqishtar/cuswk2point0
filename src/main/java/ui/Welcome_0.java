package ui;

import entity.Order;
import uiutility.PageSwitchHelper;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Creates the Welcome page, where the user can enter the program in 2 modes: Passenger mode and Staff
 * mode.
 */
public class Welcome_0 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton psgrOption, staffOption;

	/**
	 * Constructor
	 */
	public Welcome_0() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("    Welcome");
		label.setFont(new Font("Dialog", Font.PLAIN, 32));
		label.setBounds(200, 40, 200, 32);
		contentPane.add(label);
		
		Label label_1 = new Label("Please choose your role to log in");
		label_1.setBounds(200, 80, 220, 28);
		contentPane.add(label_1);
		
		psgrOption = new JButton("Passenger");
		psgrOption.addActionListener(e -> PageSwitchHelper.goToPage(this, null, 1));
		psgrOption.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		psgrOption.setBounds(75, 140, 200, 180);
		contentPane.add(psgrOption);
		
		staffOption = new JButton("Staff");
		staffOption.addActionListener(e -> PageSwitchHelper.goToPage(this, null, 9));
		staffOption.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		staffOption.setBounds(325, 140, 200, 180);
		contentPane.add(staffOption);
	}

	/**
	* Defines the action to be performed after pressing 2 buttons on this frame.<p>
	 If the <code>psgrOption</code> is pressed, the user will be brought to passenger information enter page.<p>
	 If the <code>staffOption</code> is pressed, the user will be brought to staff login page.

	 @param e  The source of the event, can be either <code>psgrOption</code> or <code>staffOption</code>
	* */
	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
