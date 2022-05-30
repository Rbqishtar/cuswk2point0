package ui;

import entity.Order;
import uiutility.PageFinalisation;
import uiutility.PageSwitchHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;

/**
 * The page where the passenger can confirm all his choices and edit if necessary.
 *
 *
 * */
public class OrderInfo_5 extends JFrame implements ActionListener {

	private Order odr;
	private JPanel contentPane, p1, p2, p3, p4, p11, p12, p13, p21, p22, p23, p24, p31, p32, p33, p34, p41, p42, p43;
	private JButton editSeatOption, editFoodOption, cancelOption, confirmOption;
	private JTextArea note;

	/**
	 * Create the frame.
	 *
	 * @param odr  Bring the information from prior pages and display them.
	 */
	public OrderInfo_5(Order odr) {
		this.odr = odr;
		this.setTitle("Flight kiosk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 0, 0, 0));
		p1 = new JPanel(); p2 = new JPanel(); p3 = new JPanel(); p4 = new JPanel();
		p11 = new JPanel(); p12 = new JPanel(); p13 = new JPanel();
		p21 = new JPanel(); p22 = new JPanel(); p23 = new JPanel(); p24 = new JPanel();
		p31 = new JPanel(); p32 = new JPanel(); p33 = new JPanel(); p34 = new JPanel();
		p41 = new JPanel(); p42 = new JPanel(); p43 = new JPanel();

		HashMap<JPanel, Integer> hm = new HashMap<>();
		hm.put(p1, 3); hm.put(p2, 4); hm.put(p3, 4); hm.put(p4, 3);

		JPanel[] bigPanels = new JPanel[]{p1, p2, p3, p4};
		JPanel[][] smallPanels = new JPanel[][]{{p11, p12, p13}, {p21, p22, p23, p24}, {p31, p32, p33, p34}, {p41, p42, p43}};
		for (int i=0; i<4; i++) {
			JPanel bigJp = bigPanels[i];
			contentPane.add(bigJp);
			JPanel[] smallJps = smallPanels[i];
			bigJp.setLayout(new GridLayout(hm.get(bigJp), 1, 0, 0));
			for (JPanel jp: smallJps) {
				bigJp.add(jp);
			}
		}

		JLabel title = new JLabel("Confirm your order: ");
		title.setFont(new Font("Dialog", Font.PLAIN, 28));
		p12.add(title);

		p21.add(new JLabel("ID number: "));
		p21.add(new JLabel(odr.getIdnum()));

		p22.add(new JLabel("Flight number: "));
		p22.add(new JLabel(odr.getFlightNo()));

		p23.add(new JLabel("Seat: "));
		p23.add(new JLabel(odr.getSeatno()));
		editSeatOption = new JButton("Edit");
		editSeatOption.addActionListener(this);
		p23.add(editSeatOption);

		p24.add(new JLabel("Seat extra: "));
		p24.add(new JLabel("" + odr.getSeatExtra()));

		p31.add(new JLabel("Food & Drink: "));
		p31.add(new JLabel(odr.getFoodMenu() + ", " + odr.getFoodType() + "; " + odr.getDrink()));
		editFoodOption = new JButton("Edit");
		editFoodOption.addActionListener(this);
		p31.add(editFoodOption);

		p32.add(new JLabel("Food extra: "));
		p32.add(new JLabel("" + odr.getMealExtra()));

		p34.add(new JLabel("Special notes: "));

		note = new JTextArea(9, 20);
		p41.add(note);


		cancelOption = new JButton("Cancel check in");
		confirmOption = new JButton("Confirm");
		cancelOption.addActionListener(this);
		confirmOption.addActionListener(this);
		p42.add(cancelOption);
		p42.add(confirmOption);

		
	}

	/**
	 * Defines the actions.
	 * <ol>
	 *     <li>editSeatOption</li>
	 *     The passenger will be brought back to the seat selection page, with <code>seatNo and seatExtra</code>
	 *     being reset in <code>odr</code>.
	 *     <li>editFoodOption</li>
	 *     The passenger will be brought back to the food selection page, with <code>drink, foodType, foodMenu and foodExtra</code>
	 * 	   being reset in <code>odr</code>.
	 * 	   <li>cancelOption</li>
	 * 	   <code>odr</code> will be discarded, and the passenger will be brought to the top page.
	 * 	   <li>confirmOption</li>
	 * 	   If <code>seatExtra and mealExtra</code> are all <code>0</code>, the order will be finalised.
	 * 	   Otherwise, the passenger will be brought to the payment page.
	 * </ol>
	 *
	 *
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == editSeatOption) {
			odr.setSeatExtra(0);
			odr.setSeatno("-");
			PageSwitchHelper.goToPage(this, odr, 3);
		} else if (e.getSource() == editFoodOption) {
			PageSwitchHelper.goToPage(this, odr, 4);
		} else if (e.getSource() == cancelOption) {
			if (JOptionPane.showConfirmDialog(null, "Sure?", "Sure?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				PageSwitchHelper.goToPage(this, null, 0);
		} else if (e.getSource() == confirmOption) {
			if (note.getText().equals("")) odr.setNote("---");
			else odr.setNote(note.getText());
			if (odr.getMealExtra() == 0 && odr.getSeatExtra() == 0) {
				PageFinalisation pf = new PageFinalisation();
				this.setVisible(false);
				try {
					pf.finaliseEverything(odr);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} else {
				PageSwitchHelper.goToPage(this, odr, 6);
			}
		}
		
	}

}
