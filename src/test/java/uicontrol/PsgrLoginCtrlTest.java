package uicontrol;

import static org.junit.jupiter.api.Assertions.*;

class PsgrLoginCtrlTest {

    @org.junit.jupiter.api.Test
    void getIdnumAndFlightNoForIdnum() {
        PsgrLoginCtrl plc = new PsgrLoginCtrl();
        assertTrue(plc.getIdnumAndFlightNo("111")[1].equals("AA1111"));
    }

    @org.junit.jupiter.api.Test
    void getIdnumAndFlightNoForBooking() {
        PsgrLoginCtrl plc = new PsgrLoginCtrl();
        assertTrue(plc.getIdnumAndFlightNo("AA1111")[0].equals("111"));
    }

}