package uicontrol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatCtrlTest {

    @Test
    void canInsertSeat() {
        assertTrue(new SeatCtrl().canInsertSeat("AA1111", 1, 4));
        assertTrue(new SeatCtrl().canInsertSeat("AA1111", 12, 4));
        assertTrue(new SeatCtrl().canInsertSeat("AA1111", 22, 4));
    }

    @Test
    void convertSeatNo() {
        assertEquals(new SeatCtrl().convertSeatNo(4, 4), "4D");
        assertEquals(new SeatCtrl().convertSeatNo(14, 4), "14D");
        assertEquals(new SeatCtrl().convertSeatNo(4, 1), "4A");
        assertEquals(new SeatCtrl().convertSeatNo(24, 8), "24H");
        assertEquals(new SeatCtrl().convertSeatNo(2, 9), "2");
        assertEquals(new SeatCtrl().convertSeatNo(1, 0), "1");
    }

    @Test
    void reConvertSeatNo() {
        assertEquals(new SeatCtrl().reConvertSeatNo("1D")[1], 4);
        assertEquals(new SeatCtrl().reConvertSeatNo("1H")[1], 8);
        assertEquals(new SeatCtrl().reConvertSeatNo("23A")[1], 1);
        assertEquals(new SeatCtrl().reConvertSeatNo("1G")[1], 7);
        assertEquals(new SeatCtrl().reConvertSeatNo("1I")[1], 0);
    }

    @Test
    void getMaxRowNumber() {
        assertEquals(new SeatCtrl().getMaxRowNumber("AA1111"), 30);
        assertEquals(new SeatCtrl().getMaxRowNumber("BB2222"), 30);
        assertEquals(new SeatCtrl().getMaxRowNumber("DD4444"), 30);
    }

    @Test
    void getColNumber() {
        assertEquals(new SeatCtrl().getColNumber("AA1111"), 6);
        assertEquals(new SeatCtrl().getColNumber("BB2222"), 8);
    }

}