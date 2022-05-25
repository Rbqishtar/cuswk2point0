package uicontrol;

import entitydao.StaffDAO;

public class StaffLoginCtrl {
    public boolean validateStaff(String userName, String password) {
        return new StaffDAO().validateStaff(userName, password);
    }
}
