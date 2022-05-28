package uiutility;

import entity.Order;
import ui.*;

import javax.swing.*;

public class PageSwitchHelper {

    public static void goToPage(JFrame currentFrame, Order odr, int flag) {
        currentFrame.setVisible(false);
        JFrame newFrame = new JFrame();
        newFrame.setTitle("Flight kiosk");
        switch (flag) {
            case 0 -> newFrame = new Welcome_0();
            case 1 -> newFrame = new PsgrLogin_1();
            case 3 -> newFrame = new ChooseSeat_3(odr);
            case 4 -> newFrame = new ChooseMeal_4(odr);
            case 5 -> newFrame = new OrderInfo_5(odr);
            case 6 -> newFrame = new Pay_6(odr);
            case 9 -> newFrame = new StaffLogin_9();
            default -> JOptionPane.showConfirmDialog(null, "pageSwitchHelper error");
        }
        newFrame.setVisible(true);
    }

}
