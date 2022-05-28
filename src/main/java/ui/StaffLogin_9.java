package ui;

import uicontrol.StaffLoginCtrl;

import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * The page where staff members can log in.
 *
 * */
public class StaffLogin_9 extends JFrame implements ActionListener  {

	private JPanel contentPane;
	private JTextField inputUsername, inputPassword;
	JButton proceedOption, clearOption, cancelOption;

	/**
	 * Create the frame.
	 */
	public StaffLogin_9() {
		this.setTitle("Flight kiosk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Please enter your information below:");
		label.setFont(new Font("Dialog", Font.PLAIN, 24));
		label.setBounds(90, 40, 450, 40);
		contentPane.add(label);
		
		inputUsername = new JTextField();
		inputUsername.setBounds(273, 132, 212, 40);
		inputUsername.addActionListener(this);
		contentPane.add(inputUsername);
		inputUsername.setColumns(10);
		
		Label label_1 = new Label("        Username");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		label_1.setBounds(75, 132, 150, 40);
		contentPane.add(label_1);
		
		inputPassword = new JPasswordField();
		inputPassword.setBounds(273, 203, 212, 40);
		inputPassword.addActionListener(this);
		contentPane.add(inputPassword);
		
		Label label_1_1 = new Label("        Password");
		label_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		label_1_1.setBounds(75, 203, 150, 40);
		contentPane.add(label_1_1);
		
		proceedOption = new JButton(">>  Confirm  <<");
		proceedOption.setFont(new Font("Dialog", Font.PLAIN, 20));
		proceedOption.setBounds(172, 281, 240, 50);
		proceedOption.addActionListener(this);
		contentPane.add(proceedOption);
		
		clearOption = new JButton("Clear");
		clearOption.setBounds(479, 266, 84, 35);
		clearOption.addActionListener(this);
		contentPane.add(clearOption);
		
		cancelOption = new JButton("Cancel");
		cancelOption.setBounds(479, 313, 84, 35);
		cancelOption.addActionListener(this);
		contentPane.add(cancelOption);
		
	}

	/**
	 * Defines the action.
	 * <ol>
	 *     <li>proceedOption</li>
	 *     The system will match the username and the password according to the internal files, and
	 *     bring staff to the next page if they are valid.
	 *     <li>clearOption</li>
	 *     All the input fields will be cleared.
	 *     <li>cancelOption</li>
	 *     Bring back to the top page.
	 * </ol>
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == proceedOption) {
			if (inputUsername.getText().equals("") || inputPassword.getText().equals("")) {
				JOptionPane.showConfirmDialog(null, "Enter all information pls", "wtf man", JOptionPane.OK_OPTION);
			} else {
				StaffLoginCtrl slc = new StaffLoginCtrl();
				if (slc.validateStaff(inputUsername.getText(), inputPassword.getText())) {
					this.setVisible(false);
					StaffDashboard_10 p10 = new StaffDashboard_10(inputUsername.getText());
					p10.setVisible(true);
				} else JOptionPane.showConfirmDialog(null, "Wrong password or username", "wtf man", JOptionPane.OK_OPTION);
			}
		} else if (e.getSource() == clearOption) {
			inputUsername.setText(null);
			inputPassword.setText(null);
		} else if (e.getSource() == cancelOption) {
			Welcome_0 p2 = new Welcome_0();
			this.setVisible(false);
			p2.setVisible(true);
		} else {
			JOptionPane.showConfirmDialog(null, "Error in event handling, please report");
		}
		
	}
}
