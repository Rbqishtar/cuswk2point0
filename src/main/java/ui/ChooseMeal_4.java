package ui;

import entity.Order;
import uicontrol.FoodCtrl;
import uiutility.PageSwitchHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The page where the passenger can choose his drink and food onboard. To be more precise:
 * <ol>
 *     <li>He can choose a drink</li>
 *     <li>He can choose the type of the onboard food, with many free options and 2 options with a extra fee</li>
 *     <li>He can choose between 4 menus for each type of food</li>
 *     <li>He can randomly choose a type of food</li>
 *     <li>He can randomly choose a menu for the given food type</li>
 *     <li>If he selected the First Class, he get bundled with a free premium meal with no additional charges</li>
 * </ol>
 * The first class can be identified via <code>seatNo</code> in <code>odr</code>.
 * */
public class ChooseMeal_4 extends JFrame implements ActionListener {

	private Order odr;
	private boolean isWealthyAss;
	private final JPanel contentPane, p1, p2, p11, p12, p13, p21, p22, p23;
	private final JButton okType, randomType, randomMenu, backOption, proceedOption;
	private JComboBox<String> foodTypeBox, foodMenuBox, drinkBox;


	/**
	 * Create the frame.
	 *
	 * @param odr  Bring the information from prior pages, and record <code>foodType, foodExtra, foodMenu and drink</code> in this page.
	 */
	public ChooseMeal_4(Order odr) {

		this.odr = odr;
		FoodCtrl fCtrl = new FoodCtrl();
		isWealthyAss = (Integer.parseInt(String.valueOf(odr.getSeatno().charAt(0))) <= 3) && (odr.getSeatno().length() == 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		p1 = new JPanel();
		p2 = new JPanel();
		p11 = new JPanel();
		p12 = new JPanel();
		p13 = new JPanel();
		p21 = new JPanel();
		p22 = new JPanel();
		p23 = new JPanel();
		JPanel[] bigPanels = new JPanel[]{p1, p2};
		JPanel[][] smallPanels = new JPanel[][]{{p11, p12, p13}, {p21, p22, p23}};
		for (int i=0; i<2; i++) {
			JPanel bigJp = bigPanels[i];
			contentPane.add(bigJp);
			JPanel[] smallJps = smallPanels[i];
			bigJp.setLayout(new GridLayout(3, 1, 0, 0));
			for (JPanel jp: smallJps) {
				bigJp.add(jp);
			}
		}
		JLabel title = new JLabel();
		title.setText("Select your food and drink: ");
		title.setFont(new Font("Dialog", Font.PLAIN, 24));
		p11.add(title);

		String[] drinkType = {"Nothing", "Coffee", "Tea", "Water", "Orange Juice"};
		drinkBox = new JComboBox<>(drinkType);
		p12.add(new JLabel("Drink: "));
		p12.add(drinkBox);

		String[] foodtype = {"Nothing", "Regular", "Vegetarian", "Halal", "Gourmet", "Hyperfresh"};
		foodTypeBox = new JComboBox(foodtype);
		foodTypeBox.addActionListener(e -> {
			if (!isWealthyAss) {
				fCtrl.refreshMenu(foodTypeBox, foodMenuBox);
				p22.setVisible(true);
				p23.setVisible(true);
			}
		});
		JLabel menuNotice = new JLabel("Gourmet: +$10; Hyperfresh: +$100");
		menuNotice.setFont(new Font("Dialog", Font.BOLD, 12));
		p13.add(new JLabel("Food type: "));
		p13.add(foodTypeBox);
		if (!isWealthyAss) p13.add(menuNotice);

		okType = new JButton("OK");
		randomType = new JButton("Random");
		okType.addActionListener(this);
		randomType.addActionListener(e -> {
			foodTypeBox.setSelectedIndex(new Random().nextInt(4) + 1);
			if (!isWealthyAss) {
				fCtrl.refreshMenu(foodTypeBox, foodMenuBox);
				p22.setVisible(true);
				p23.setVisible(true);
			}
		});
		p21.add(okType);
		p21.add(randomType);
		if (!isWealthyAss) okType.setVisible(false);

		foodMenuBox = new JComboBox();
		randomMenu = new JButton("Random");
		randomMenu.addActionListener(e -> {
			foodMenuBox.setSelectedIndex(new Random().nextInt(4) + 1);
		});
		p22.add(new JLabel("Food menu: "));
		p22.add(foodMenuBox);
		p22.add(randomMenu);
		p22.setVisible(false);

		backOption = new JButton("Back");
		proceedOption = new JButton("Proceed");
		backOption.addActionListener(this);
		proceedOption.addActionListener(this);
		p23.add(backOption);
		p23.add(proceedOption);
		p23.setVisible(false);

	}

	public void serveFirstClass() {
		odr.setMealExtra(0);
		odr.setDrink((String)drinkBox.getSelectedItem());
		odr.setFoodType((String)foodTypeBox.getSelectedItem());
		odr.setFoodMenu("Premium");
		if (odr.getDrink().equals("Nothing")) JOptionPane.showConfirmDialog(null, "You chose to drink nothing");
		if (odr.getFoodType().equals("Nothing")) JOptionPane.showConfirmDialog(null, "You chose to eat nothing");
		PageSwitchHelper.goToPage(this, odr, 5);
	}

	/**
	 * Defines the actions of buttons and combo boxes.
	 * <ol>
	 *     <li>okType</li>
	 *     <ul>
	 *         <li>seat is first class, menu not shown</li>
	 *         If the passenger selected a drink and a type for his food, he will be directly brought to the next
	 *         page, which is the order confirmation page.
	 *         <li>seat is not first class, menu not shown</li>
	 *         The system will fetch menus and show them in <code>foodMenu</code> combo box below, according to selected item in the <code>foodType</code> combo box.
	 *         Buttons for further actions will also be shown.
	 *         <li>seat is not first class, menu shown</li>
	 *         The menus will be refreshed according to the newly selected item.
	 *     </ul>
	 *     Also, the passenger will receive an alert if he has chosen the "No food" option.<p>
	 *     <li>randomType</li>
	 *     The system will randomly select an item in the <code>foodType</code> combo box.
	 *     <ul>
	 *         <li>If the seat is first class, no further actions.</li>
	 *         <li>If the menus are shown below, they will be refreshed according to the newly selected item.</li>
	 *     </ul>
	 *     <li>randomMenu</li>
	 *     Select a random menu.
	 *     <li>backOption</li>
	 *     Fall back to the previous page, with <code>seatNo</code> in <code>odr</code> being reset.
	 *     <li>proceedOption</li>
	 *     If the passenger has not chosen the "No food" option in <code>foodType</code> and did not choose
	 *     anything in <code>foodMenu</code> ("---"), he will receive an alert. Otherwise, proceed to the next page.
	 * </ol>
	 * When proceeding to the next page, the <code>drink</code>, <code>foodType</code>, <code>foodExtra</code> and <code>foodMenu</code> in <code>odr</code> will be set according
	 * to the choices made by customer. <p>
	 * Specially, if the passenger is in first class, the <code>foodExtra</code> would be <code>0</code>, and <code>foodMenu</code> would be <code>premium</code>.
	 *
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okType) {
			serveFirstClass();
		}
		else if (e.getSource() == backOption) {
			odr.setSeatExtra(0);
			odr.setSeatno("");
			PageSwitchHelper.goToPage(this, odr, 3);
		}
		else if (e.getSource() == proceedOption) {
			if (foodMenuBox.getSelectedIndex() != 0 || foodTypeBox.getSelectedIndex() == 0) {
				FoodCtrl fCtrl = new FoodCtrl();
				String type = (String)foodTypeBox.getSelectedItem();
				odr.setMealExtra(fCtrl.calculateMealExtra(type));
				odr.setDrink((String)drinkBox.getSelectedItem());
				odr.setFoodType(type);
				odr.setFoodMenu((String)foodMenuBox.getSelectedItem());

				if (fCtrl.confirmNothingOption(odr))
					PageSwitchHelper.goToPage(this, odr, 5);
			} else JOptionPane.showConfirmDialog(null, "choose a menu man", "?", JOptionPane.DEFAULT_OPTION);
		}
	}

	public static void main(String[] args) {
		Order odr = new Order();
		odr.setSeatno("4A");
		ChooseMeal_4 f4 = new ChooseMeal_4(odr);
		f4.setVisible(true);
	}

}
