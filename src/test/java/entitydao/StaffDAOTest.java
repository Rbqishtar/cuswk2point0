package entitydao;

import entity.Plane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffDAOTest {

    @Test
    void validateStaff() {
        assertTrue(new StaffDAO().validateStaff("qqqqq", "q"));
    }

    @Test
    void validateStaff1() {
        assertFalse(new StaffDAO().validateStaff("qqq", "q"));
    }

    @Test
    void validateStaff2() {
        assertFalse(new StaffDAO().validateStaff("qqqqq", ""));
    }

    @Test
    void validateStaff3() {
        assertFalse(new StaffDAO().validateStaff("", ""));
    }

    @Test
    void validateStaff4() {
        assertTrue(new StaffDAO().validateStaff("wwwww", "w"));
    }
}