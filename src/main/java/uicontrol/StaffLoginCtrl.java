package uicontrol;

import entitydao.StaffDAO;
/**
 * The controller for staff login page, responsible for communicating between it and <code>StaffDAO</code>
 * */
public class StaffLoginCtrl {
    /**
     * Get the inputs from a staff login page and pass them to <code>StaffDAO</code>
     *
     * @param password  Input password
     * @param userName  Input username
     * @return Whether there exists a staff whose password and username matches the 2 inputs
     * */
    public boolean validateStaff(String userName, String password) {
        return new StaffDAO().validateStaff(userName, password);
    }
}
