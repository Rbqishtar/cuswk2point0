package entitydao;

import entity.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerDAOTest {

    @Test
    void getPsgr() {
        Passenger psgr = new Passenger();
        String[] eachline = new String[]{"manhattan cafe", "f", "17", "888", "false"};
        psgr.setName(eachline[0]);
        psgr.setSex(eachline[1]);
        psgr.setAge(Integer.parseInt(eachline[2]));
        psgr.setIdnum(eachline[3]);
        psgr.setDisabled(Boolean.parseBoolean(eachline[4]));
        assertEquals(new PassengerDAO().getPsgr("888"), psgr);
    }

    @Test
    void getPsgr2() {
        Passenger psgr = new Passenger();
        String[] eachline = new String[]{"narita taishin", "f", "17", "111", "false"};
        psgr.setName(eachline[0]);
        psgr.setSex(eachline[1]);
        psgr.setAge(Integer.parseInt(eachline[2]));
        psgr.setIdnum(eachline[3]);
        psgr.setDisabled(Boolean.parseBoolean(eachline[4]));
        assertEquals(new PassengerDAO().getPsgr("111"), psgr);
    }

}