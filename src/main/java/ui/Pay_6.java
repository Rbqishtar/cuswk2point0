package ui;

import entity.Order;
import uicontrol.PaymentCtrl;
import uiutility.PageFinalisation;
import uiutility.PageSwitchHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.HashMap;

/**
 * The page where the user makes his payment.
 * */
public class Pay_6 extends JFrame implements ActionListener {

	private Order odr;
	private JPanel contentPane, p1, p2, p3, p4, p11, p12, p13, p21, p22, p23, p24, p31, p32, p33, p34, p41, p42, p43;
	private JTextField input1, input2, input3;
	private JLabel label1, label2, label3;
	private JButton cancelOption, backOption, proceedOption;
	private JComboBox<String> payMethod;

	/**
	 * Create the frame.
	 *
	 * @param odr  Brought from previous pages, contains the extra amount money to be paid.
	 */
	public Pay_6(Order odr) {
		this.odr = odr;
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

		JLabel title = new JLabel("Make your payment: ");
		title.setFont(new Font("Dialog", Font.PLAIN, 28));
		p12.add(title);

		p21.add(new JLabel("Seat: " + odr.getSeatExtra()));
		p22.add(new JLabel("Meal: " + odr.getMealExtra()));
		p23.add(new JLabel("Total: " + (odr.getMealExtra() + odr.getSeatExtra())));

		JLabel selectMethod = new JLabel("Select a payment method: ");
		String[] s = new String[]{"---", "Credit Card", "Wechat", "Alipay"};
		payMethod = new JComboBox<>(s);
		payMethod.addActionListener(a -> refreshInputs());
		p24.add(selectMethod);
		p24.add(payMethod);

		label1 = new JLabel();
		input1 = new JTextField();
		input1.setColumns(10);
		p31.add(label1);
		p31.add(input1);

		label2 = new JLabel();
		input2 = new JTextField();
		input2.setColumns(10);
		p32.add(label2);
		p32.add(input2);

		label3 = new JLabel();
		input3 = new JTextField();
		input3.setColumns(10);
		p33.add(label3);
		p33.add(input3);

		cancelOption = new JButton("Cancel");
		backOption = new JButton("Back");
		proceedOption = new JButton("Proceed");
		for (JButton jb: new JButton[]{cancelOption, backOption, proceedOption}) {
			jb.addActionListener(this);
			p42.add(jb);
		}

	}

	/**
	 * A method to refresh the labels of the input fields according to the selected payment method.
	 *
	 * */
	public void refreshInputs() {

		String s = (String)payMethod.getSelectedItem();
		switch (s) {
			case "Credit Card" -> {
				p3.setVisible(true);
				label1.setText("Card number: ");
				label2.setText("Expiration date: ");
				label3.setText("CVV: ");
			}
			case "Wechat" -> {
				p3.setVisible(true);
				label1.setText("Wechat id: ");
				label2.setText("password: ");
				label3.setText("payment PIN: ");
			}
			case "Alipay" -> {
				p3.setVisible(true);
				label1.setText("Alipay id: ");
				label2.setText("password: ");
				label3.setText("payment PIN: ");
			}
			case "---" -> p3.setVisible(false);
			default -> JOptionPane.showConfirmDialog(null, "Payment method error");
		}
	}

	/**
	 * Defines the action.
	 * <ol>
	 *     <li>cancelOption</li>
	 *     Cancel everything, discard <code>odr</code> and return to top page.
	 *     <li>backOption</li>
	 *     Bring passenger to order confirmation page, where he can make some changes to the order.
	 *     <li>proceedOption</li>
	 *     Check the validity of inputs. If they are valid, the order will be finalised. If not, the passenger will receive an alert.
	 * </ol>
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		PaymentCtrl pc = new PaymentCtrl();
		String s1 = input1.getText();
		String s2 = input2.getText();
		String s3 = input3.getText();

		if (e.getSource() == cancelOption) {
			if (JOptionPane.showConfirmDialog(null, "Sure?", "Sure?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				PageSwitchHelper.goToPage(this, null, 0);
		} else if (e.getSource() == backOption) {
			PageSwitchHelper.goToPage(this, odr, 5);
		} else if (e.getSource() == proceedOption) {
			if (pc.verifyPayment((String)payMethod.getSelectedItem(), s1, s2, s3)) {
				PageFinalisation pf = new PageFinalisation();
				pf.finaliseEverything(odr);
				this.setVisible(false);
			} else JOptionPane.showConfirmDialog(null, "Info cannot match");

		}
	}


}
