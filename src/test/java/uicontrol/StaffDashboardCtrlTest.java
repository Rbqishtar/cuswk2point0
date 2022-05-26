package uicontrol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffDashboardCtrlTest {

    @Test
    void inputIsValid() {
        assertTrue(new StaffDashboardCtrl().inputIsValid("AA1111"));
        assertTrue(new StaffDashboardCtrl().inputIsValid("BB2222"));
        assertTrue(new StaffDashboardCtrl().inputIsValid("CC3333"));
        assertFalse(new StaffDashboardCtrl().inputIsValid(""));
        assertFalse(new StaffDashboardCtrl().inputIsValid("A11"));
        assertFalse(new StaffDashboardCtrl().inputIsValid("1"));
        assertFalse(new StaffDashboardCtrl().inputIsValid("A"));
        assertFalse(new StaffDashboardCtrl().inputIsValid("A1ewqewqeqwe1"));
        assertFalse(new StaffDashboardCtrl().inputIsValid("A jkcniouwedjio3dhuwekbckjd cnweiocbie11"));

    }

}