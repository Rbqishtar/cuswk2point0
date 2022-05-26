package uicontrol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffLoginCtrlTest {

    @Test
    void validateStaff() {
        assertTrue(new StaffLoginCtrl().validateStaff("qqqqq", "q"));
        assertTrue(new StaffLoginCtrl().validateStaff("wwwww", "w"));
        assertFalse(new StaffLoginCtrl().validateStaff("q123d", "q"));
        assertFalse(new StaffLoginCtrl().validateStaff("qqq29djnjkwdc oppodq", "q"));
        assertFalse(new StaffLoginCtrl().validateStaff("qqqwewqeq", "q"));
        assertFalse(new StaffLoginCtrl().validateStaff("qqqqwewqewqq", "q"));
        assertFalse(new StaffLoginCtrl().validateStaff("qqeddsxadqq", "q"));
        assertFalse(new StaffLoginCtrl().validateStaff("", ""));
        assertFalse(new StaffLoginCtrl().validateStaff("qqddqweqq", ""));
        assertFalse(new StaffLoginCtrl().validateStaff("", "q"));


    }
}