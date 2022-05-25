package ui;

import entity.Order;
import uicontrol.PsgrLoginCtrl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Font;
/**
 * Creates the passenger 'login' or check-in page. <p> The passenger can begin checking in by entering his
 * id number or booking number into the <code>input</code> text field.
 * */
public class PsgrLogin_1 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField input;
	private JButton backOption, proceedOption;
	/**
	 * The constructor
	 *
	 * */
	public PsgrLogin_1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label label = new Label("Please enter your ID number or booking number");
		label.setFont(new Font("Dialog", Font.PLAIN, 24));
		label.setBounds(68, 120, 460, 72);
		contentPane.add(label);

		backOption = new JButton("<< Back");
		backOption.setBounds(94, 212, 159, 38);
		backOption.addActionListener(this);
		contentPane.add(backOption);

		proceedOption = new JButton("Go");
		proceedOption.setBounds(321, 212, 159, 38);
		proceedOption.addActionListener(this);
		contentPane.add(proceedOption);

		input = new JTextField();
		input.setBounds(113, 280, 352, 38);
		contentPane.add(input);
		input.setColumns(10);
	}

	/**
	 * Defines the action to be performed after pressing 2 buttons on this frame.<p>
	 If the <code>proceedOption</code> is pressed, the system will check:
	 <ol>
	 	<li>Whether the passenger has entered anything</li>
	 	<li>Whether the passenger has checked in before</li>
	 	<li>Whether the input matches the data recorded in files or not</li>
	 </ol>
	 If everything is OK, the user will be brought to the next page. If not, he will receive the cprresponding
	 alert and will not go to thenext page.<p>
	 If the <code>backOption</code> is pressed, the user will be brought to welcome page.

	 @param e  The source of the event, can be either <code>psgrOption</code> or <code>staffOption</code>
	  * */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == proceedOption) {
			PsgrLoginCtrl plc = new PsgrLoginCtrl();
			if (plc.canGetDetail(input.getText())) {
				String[] idNumAndFlightNo = plc.getIdnumAndFlightNo(input.getText());
				Order checkodr = plc.getExistingOrder(idNumAndFlightNo[0], idNumAndFlightNo[1]);
				if (checkodr.getSeatno() == null) {
					ShowFlightInfo_2 p2 = new ShowFlightInfo_2(idNumAndFlightNo);
					p2.setVisible(true);
					this.setVisible(false);
				} else {
					plc.alertCheckedIn(checkodr);
				}
			} else JOptionPane.showConfirmDialog(null, "No booking");
		}
		else if (e.getSource() == backOption) {
			Welcome_0 p0 = new Welcome_0();
			this.setVisible(false);
			p0.setVisible(true);
		}
	}
}
