package ui;

import entity.Order;
import uicontrol.PsgrLoginCtrl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Creates the passenger 'login' or check-in page. <p> The passenger can begin checking in by entering his
 * id number or booking number into the <code>input</code> text field.
 * */
public class PsgrLogin_1 extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField input;
	private JButton backOption, proceedOption,forgetOption;
	private JPanel p1, p2, p3, p11, p12, p21, p22, p31, p32;
	private boolean state;
	/**
	 * The constructor
	 *
	 * */
	public PsgrLogin_1() {
		state = true;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));

		p1 = new JPanel(); p2 = new JPanel(); p3 = new JPanel();
		p11 = new JPanel(); p12 = new JPanel();
		p21 = new JPanel(); p22 = new JPanel();
		p31 = new JPanel(); p32 = new JPanel();


		for (JPanel jp: new JPanel[]{p1, p2, p3}) contentPane.add(jp);
		p2.add(p21);
		p2.add(p22);
		p3.add(p32);

		JLabel label = new JLabel("Enter your booking number");
		label.setFont(new Font("Dialog", Font.PLAIN, 28));
		p21.add(label);

		input = new JTextField();
		input.setColumns(10);
		input.setFont(new Font("Dialog", Font.PLAIN, 18));
		p22.add(input);

		backOption = new JButton("<< Back");
		backOption.addActionListener(this);
		p32.add(backOption);
		forgetOption = new JButton("Forget your booking number?");
		forgetOption.addActionListener(e -> {
			label.setText("Enter your ID number or surname");
			state = false;
		});
		p3.add(forgetOption);

		proceedOption = new JButton("Go");
		proceedOption.addActionListener(this);
		p22.add(proceedOption);


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
			} else JOptionPane.showConfirmDialog(null, "No booking information");
		}
		else if (e.getSource() == backOption) {
			Welcome_0 p0 = new Welcome_0();
			this.setVisible(false);
			p0.setVisible(true);
		}
	}
}
