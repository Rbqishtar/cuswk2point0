package ui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import uicontrol.StaffDashboardCtrl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

/**
 * The page where the staff can have a view of the information about passengers of a given flight.<p>
 * The information contains:
 * <ol>
 *     <li>Whether the passenger has checked in</li>
 *     <li>The name, sex, age, flight number of the passenger</li>
 *     <li>Whether the passenger is a disabled person</li>
 *     <li>The seat number, food, drink and special note of the passenger</li>
 * </ol>
 * Also, there are 3 filters provided to filter the table by certain conditions.
 * */
public class StaffDashboard_10 extends JFrame implements ActionListener {

	private JPanel contentPane, p1, p11, p12, p13, p3, p31, p32, p33, p34;
	private JTextField inputFlightno;
	private JButton showOption, filterOption, exitOption;
	private JCheckBox filterKids, filterDisabled, filterCheckedIn;
	private JScrollPane p2;
	private JTable jt;
	private String[] columns = {"Name", "Sex", "Age", "Disabled", "Flight", "Date", "Check in", "Seat", "Food Type", "Food Menu", "Drink", "Note"};
	private String[][] data;
	private String[] conds = {"", "", ""};
	private DefaultTableModel model;

	/**
	 * Create the frame.
	 *
	 * @param uname  The username of the staff, used in welcome title
	 */
	public StaffDashboard_10(String uname) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));

		data = new String[200][7];
		model = new DefaultTableModel();
		jt = new JTable(model);
		jt.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		jt.setEnabled(false);

		p1 = new JPanel(); p11 = new JPanel(); p12 = new JPanel(); p13 = new JPanel();
		p3 = new JPanel(); p31 = new JPanel(); p32 = new JPanel(); p33 = new JPanel(); p34 = new JPanel();
		p2 = new JScrollPane(jt);
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);
		for (JPanel jp: new JPanel[]{p11, p12, p13}) p1.add(jp);
		for (JPanel jp: new JPanel[]{p31, p32, p33, p34}) p3.add(jp);
		p1.setLayout(new GridLayout(3, 0, 0, 0));
		p3.setLayout(new GridLayout(4, 0, 0, 0));

		JLabel welcome = new JLabel("Welcome, " + uname);
		welcome.setFont(new Font("Dialog", Font.PLAIN, 18));
		p11.add(welcome);

		JLabel enterFlight = new JLabel("Enter the flight number you want to query: ");
		enterFlight.setFont(new Font("Dialog", Font.PLAIN, 18));
		p12.add(enterFlight);

		inputFlightno = new JTextField();
		inputFlightno.setColumns(10);
		p12.add(inputFlightno);

		showOption = new JButton("GO");
		showOption.addActionListener(e -> {
			generateConditions();
			updateTable();
		});
		p12.add(showOption);

		JLabel stats = new JLabel("Stats");
		stats.setFont(new Font("Dialog", Font.PLAIN, 18));
		p13.add(stats);

		JLabel filter = new JLabel("Filter: ");
		filter.setFont(new Font("Dialog", Font.PLAIN, 18));
		p31.add(filter);

		filterKids = new JCheckBox("Kids");
		filterKids.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		p31.add(filterKids);

		filterDisabled = new JCheckBox("Disabled");
		filterDisabled.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		p31.add(filterDisabled);

		filterCheckedIn = new JCheckBox("Not checked in");
		filterCheckedIn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		p31.add(filterCheckedIn);

		filterOption = new JButton("Filter");
		filterOption.addActionListener(e -> {
			generateConditions();
			updateTable();
		});
		p31.add(filterOption);

		exitOption = new JButton("Exit");
		exitOption.addActionListener(e -> System.exit(0));
		p33.add(exitOption);

	}

	/**
	 * Generate the filter conditions according to the selection in 3 radio buttons. For the purpose of behaving more dynamic,
	 * the conditions are stored in a global variable <code>cond</code>. One condition corresponds to one slot in the array. If
	 * one is selected, set text to it. If it is not selected, manually empty it by assigning an empty String.
	 * */
	public void generateConditions() {
		if (filterDisabled.isSelected()) conds[0] = "Disabled";
		else conds[0] = "";
		if (filterKids.isSelected()) conds[1] = "Kids";
		else conds[1] = "";
		if (filterCheckedIn.isSelected()) conds[2] = "Checked In";
		else conds[2] = "";
	}

	/**
	 * Update the data in the table. <p>If the input flight exists, the page will call its control class <code>StaffDashboardCtrl</code> to fetch the needed data and pass it to a
	 * <code>writeTableContents</code> to write the data onto the table.
	 * */
	public void updateTable() {
		StaffDashboardCtrl sdctrl = new StaffDashboardCtrl();
		if (sdctrl.inputIsValid(inputFlightno.getText())) {
			JsonArray tableContents = sdctrl.getTableContents(inputFlightno.getText());
			writeTableContents(tableContents);
		}
		else JOptionPane.showConfirmDialog(null, "no such flight");
	}

	/**
	 * A dedicated method to input data into the table.<p>
	 * The page will parse the obtained <code>JsonArray</code> to a 2D String array and filter it according to the conditions set in <code>cond</code>.<p>
	 * Then, the page will update the <code>TableModel</code> of the table, resulting in an update in the data
	 *
	 * @param tableContents  The <code>JsonArray</code> object fetched by <code>StaffDashboardCtrl</code>
	 * */
	public void writeTableContents(JsonArray tableContents) {
		int row = tableContents.size();
		int col = columns.length;
		String[][] data = new String[row][col];

		for (int i=0; i<row; i++) {
			JsonObject jsonObj = tableContents.get(i).getAsJsonObject();
			data[i][0] = jsonObj.get("Name").getAsString();
			data[i][1] = jsonObj.get("Sex").getAsString();
			data[i][2] = jsonObj.get("Age").getAsString();
			data[i][3] = jsonObj.get("Disabled").getAsString();
			data[i][4] = jsonObj.get("Flight").getAsString();
			data[i][5] = jsonObj.get("Date").getAsString();
			data[i][6] = jsonObj.get("Check in").getAsString();
			data[i][7] = jsonObj.get("Seat").getAsString();
			data[i][8] = jsonObj.get("Food Type").getAsString();
			data[i][9] = jsonObj.get("Food Menu").getAsString();
			data[i][10] = jsonObj.get("Drink").getAsString();
			data[i][11] = jsonObj.get("Note").getAsString();
		}
		model.setDataVector(data, columns);
		for (int i = row-1; i >= 0; i--) {
			if (conds[0].equals("Disabled")){
				if (data[i][3].equals("false")) {
					model.removeRow(i); continue;
				}
			}
			if (conds[1].equals("Kids")){
				if (Integer.parseInt(data[i][2]) >= 12) {
					model.removeRow(i); continue;
				}
			}
			if (conds[2].equals("Checked In")){
				if (data[i][6].equals("true")) {
					model.removeRow(i); continue;
				}
			}
		}

		model.fireTableDataChanged();

	}

	/**
	 * Placeholder. All the actions here are implemented by lambda expression within the constructor.
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
