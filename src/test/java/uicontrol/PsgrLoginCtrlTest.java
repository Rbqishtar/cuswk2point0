package uicontrol;

import entity.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PsgrLoginCtrlTest {

    @Test
    void canGetDetail() {
        assertTrue(new PsgrLoginCtrl().canGetDetail("111"));
        assertTrue(new PsgrLoginCtrl().canGetDetail("111111"));
        assertTrue(new PsgrLoginCtrl().canGetDetail("taishin"));
    }

    @Test
    void getIdnumAndFlightNo() {
        String[] s = new String[]{"111", "AA1111"};
        String[] s2 = new String[]{"999", "BB2222"};
        assertEquals(new PsgrLoginCtrl().getIdnumAndFlightNo("999")[1], s2[1]);
        assertEquals(new PsgrLoginCtrl().getIdnumAndFlightNo("111")[0], s[0]);
    }

    @Test
    void getExistingOrder() {
        Order odr = new Order();
        String[] eachline = new String[]{"333", "AA1111", "2022/3/29", "1D", "Halal", "Premium", "Water", "wqewewqe"};
        odr.setIdnum(eachline[0]);
        odr.setFlightNo(eachline[1]);
        odr.setFlightDate(eachline[2]);
        odr.setSeatno(eachline[3]);
        odr.setFoodType(eachline[4]);
        odr.setFoodMenu(eachline[5]);
        odr.setDrink(eachline[6]);
        odr.setNote(eachline[7]);
        assertEquals(new PsgrLoginCtrl().getExistingOrder("333", "AA1111"), odr);
    }
}