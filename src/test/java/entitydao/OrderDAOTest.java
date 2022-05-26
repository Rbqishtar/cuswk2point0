package entitydao;

import entity.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    @Test
    void getOrder() {
        //NOTE: SUPPOSE THERE IS 333,AA1111,2022/3/29,1D,Halal,Premium,Water,wqewewqe in Order.csv
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
        assertEquals(odr, new OrderDAO().getOrder("333", "AA1111"));
    }

    @Test
    void getOrder2() {
        //NOTE: SUPPOSE THERE IS 333,AA1111,2022/3/29,1D,Halal,Premium,Water,wqewewqe in Order.csv
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
        assertNotEquals(odr, new OrderDAO().getOrder("222", "AA1111"));
    }

}