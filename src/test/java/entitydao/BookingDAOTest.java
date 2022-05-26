package entitydao;

import entity.Booking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingDAOTest {

    @Test
    void getIfTuoyun1() {
        assertFalse(new BookingDAO().getIfTuoyun("222", "AA1111"));
    }

    @Test
    void getIfTuoyun2() {
        assertFalse(new BookingDAO().getIfTuoyun("333", "AA1111"));
    }

    @Test
    void getIfTuoyun3() {
        assertFalse(new BookingDAO().getIfTuoyun("444", "AA1111"));
    }

    @Test
    void getIfTuoyun4() {
        assertTrue(new BookingDAO().getIfTuoyun("111", "AA1111"));
    }

    @Test
    void getIfTuoyun5() {
        assertFalse(new BookingDAO().getIfTuoyun("999", "BB2222"));
    }


}