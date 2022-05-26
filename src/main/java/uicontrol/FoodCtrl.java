package uicontrol;

import entity.Order;
import entitydao.MenuDAO;
import uiutility.PageSwitchHelper;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The controller for food selection page, responsible for communicating between it and <code>MenuDAO</code>
 * */
public class FoodCtrl {

    /**
     * Gets the extra price for the given type of food. Gourmet need 10 extra bucks, while Hyperfresh needs 100.
     *
     * @param type  The type of the food
     * @return The corresponding extra fee
     * */
    public int calculateMealExtra(String type) {
        switch (type) {
            case "Gourmet" -> {
                return 10;
            }
            case "Hyperfresh" -> {
                return 100;
            }
            default -> {
                return 0;
            }
        }
    }

    /**
     * Refreshes the <code>foodMenu</code> combo box in the page according to the food type selected by passenger in <code>foodType</code> combo box.
     * <p>Data were fetched by calling <code>getMenu</code> method of <code>MenuDAO</code></p>
     *
     * @param jcb  The <code>foodType</code> combo box in the page
     * @param jcbmenu  The<code>foodMenu</code> combo box in the page
     *                 * */
    public void refreshMenu(JComboBox jcb, JComboBox jcbmenu) {
        ArrayList<String> menuArrayList = new MenuDAO().getMenu((String)jcb.getSelectedItem());
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        String[] menuList = new String[5];
        if (jcb.getSelectedIndex() != 0) {
            menuList[0] = "---";
            for (int i = 1; i < 5; i++) {
                menuList[i] = menuArrayList.get(i-1);
            }
            model = new DefaultComboBoxModel<>(menuList);
        } else {
            model = new DefaultComboBoxModel<>(new String[]{"---"});
        }
        jcbmenu.setModel(model);
    }

    public boolean confirmNothingOption(Order odr) {
        boolean noDrink = true, noFood = true;
        if (odr.getDrink().equals("Nothing"))
            if (JOptionPane.showConfirmDialog(null, "No drink?", "?", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                noDrink = false;
        if (odr.getFoodType().equals("Nothing"))
            if (JOptionPane.showConfirmDialog(null, "No food?","?", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                noFood = false;

        return noDrink && noFood;
    }

}
