package ui;

import com.google.gson.JsonObject;
import entity.Order;
import uicontrol.ShowInfoCtrl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

/**
 * Creates the frame where the passenger can confirm that the information is all correct.
 *
 * */
public class ShowFlightInfo_2 extends JFrame implements ActionListener {

	private JPanel contentPane, p1, p2, p3, p4, p5, p11, p12, p13, p21, p22 ,p23, p31, p32, p33, p41, p42, p43, p51, p52, p53;
	private JButton backOption, nextOption, seePlaneInfo;
	private Order odr;

	/**
	 * Create the frame.
	 *
	 * @param idNumAndFlightNo  The extracted <code>idnum</code> and <code>flightno</code> of the passenger from the previous page.
	 */
	public ShowFlightInfo_2(String[] idNumAndFlightNo) {
		odr = new Order();
		ShowInfoCtrl dCtrl = new ShowInfoCtrl();
		JsonObject jsonObj = dCtrl.fetchDetail(idNumAndFlightNo);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 1, 0, 0));

		p1 = new JPanel(); p2 = new JPanel(); p3 = new JPanel(); p4 = new JPanel(); p5 = new JPanel();
		p11 = new JPanel(); p12 = new JPanel(); p13 = new JPanel();
		p21 = new JPanel(); p22 = new JPanel(); p23 = new JPanel();
		p31 = new JPanel(); p32 = new JPanel(); p33 = new JPanel();
		p41 = new JPanel(); p42 = new JPanel(); p43 = new JPanel();
		p51 = new JPanel(); p52 = new JPanel(); p53 = new JPanel();

		JPanel[] bigPanels = new JPanel[]{p1, p2, p3, p4, p5};
		JPanel[][] smallPanels = new JPanel[][]{{p11, p12, p13}, {p21, p22, p23}, {p31, p32, p33}, {p41, p42, p43}, {p51, p52, p53}};
		for (int i=0; i<5; i++) {
			JPanel bigJp = bigPanels[i];
			contentPane.add(bigJp);
			JPanel[] smallJps = smallPanels[i];
			bigJp.setLayout(new GridLayout(3, 1, 0, 0));
			for (JPanel jp: smallJps) {
				bigJp.add(jp);
			}
		}
		JLabel title = new JLabel("Confirm your information:");
		title.setFont(new Font("Dialog", Font.PLAIN, 24));

		p12.add(title);

		p21.add(new JLabel("ID number: "));
		p21.add(new JLabel(jsonObj.get("ID number").getAsString()));
		odr.setIdnum(jsonObj.get("ID number").getAsString());

		p22.add(new JLabel("Name: "));
		p22.add(new JLabel(jsonObj.get("Name").getAsString()));

		p23.add(new JLabel("Flight number: "));
		p23.add(new JLabel(jsonObj.get("Flight number").getAsString()));
		odr.setFlightNo(jsonObj.get("Flight number").getAsString());

		seePlaneInfo = new JButton("More");
		seePlaneInfo.addActionListener(e -> dCtrl.seePlane(jsonObj));
		p31.add(new JLabel("Plane register number: "));
		p31.add(new JLabel(jsonObj.get("Registration number").getAsString()));
		p31.add(seePlaneInfo);

		p32.add(new JLabel("Flight departure time: "));
		p32.add(new JLabel(jsonObj.get("Departure time").getAsString()));

		p33.add(new JLabel("Flight duration: "));
		p33.add(new JLabel(jsonObj.get("Flight duration").getAsString()));

		p41.add(new JLabel("Destination: "));
		p41.add(new JLabel(jsonObj.get("Destination").getAsString()));

		p42.add(new JLabel("Flight date: "));
		p42.add(new JLabel(jsonObj.get("Flight date").getAsString()));
		odr.setFlightDate(jsonObj.get("Flight date").getAsString());

		p43.add(new JLabel("Estimated boarding time: "));
		JLabel boardingTime = new JLabel(jsonObj.get("Estimated boarding time").getAsString());
		boardingTime.setFont(new Font("Dialog", Font.BOLD, 12));
		boardingTime.setForeground(Color.RED);
		p43.add(boardingTime);

		backOption = new JButton("back");
		nextOption = new JButton("next");
		backOption.addActionListener(this);
		nextOption.addActionListener(this);
		p52.add(backOption);
		p52.add(nextOption);

	}
	/**
	 * Defines the actions of buttons.
	 * <ol>
	 *     <li>backOption</li>
	 *     Once clicked, the passenger will be brought back to the passenger login page.
	 *     <li>seePlaneInfo</li>
	 *     Once clicked, the passenger can see the details about the plane he is going to take.
	 *     <li>nextOption</li>
	 *     Once clicked, the passenger can proceed to check in.
	 * </ol>
	 *
	 * @param e  The source of the events i.e. determines which button was pressed
	 *
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backOption) {
			this.setVisible(false);
			JFrame f1 = new PsgrLogin_1();
			f1.setVisible(true);
		} else if (e.getSource() == nextOption) {
			this.setVisible(false);
			JFrame f3 = new ChooseSeat_3(odr);
			f3.setVisible(true);
		}
		
	}

}
