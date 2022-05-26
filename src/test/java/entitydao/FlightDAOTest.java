package entitydao;

import entity.Flight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOTest {

    @Test
    void getBoardingTime1() {
        Flight flt = new Flight();
        String[] eachline = new String[]{"1", "2", "12:00", "12:01", "3", "4"};
        flt.setRegisterno(eachline[0]);
        flt.setFlightno(eachline[1]);
        flt.setTakeoffTime(eachline[2]);
        flt.setLandingTime(eachline[3]);
        flt.setDest(eachline[4]);
        flt.setFlightDate(eachline[5]);
        assertEquals(new FlightDAO().getBoardingTime(flt), "11:15");
    }

    @Test
    void getBoardingTime2() {
        Flight flt = new Flight();
        String[] eachline = new String[]{"1", "2", "13:44", "12:01", "3", "4"};
        flt.setRegisterno(eachline[0]);
        flt.setFlightno(eachline[1]);
        flt.setTakeoffTime(eachline[2]);
        flt.setLandingTime(eachline[3]);
        flt.setDest(eachline[4]);
        flt.setFlightDate(eachline[5]);
        assertEquals(new FlightDAO().getBoardingTime(flt), "12:59");
    }

    @Test
    void getBoardingTime3() {
        Flight flt = new Flight();
        String[] eachline = new String[]{"1", "2", "00:00", "12:01", "3", "4"};
        flt.setRegisterno(eachline[0]);
        flt.setFlightno(eachline[1]);
        flt.setTakeoffTime(eachline[2]);
        flt.setLandingTime(eachline[3]);
        flt.setDest(eachline[4]);
        flt.setFlightDate(eachline[5]);
        assertEquals(new FlightDAO().getBoardingTime(flt), "23:15");
    }

    @Test
    void getBoardingTime4() {
        Flight flt = new Flight();
        String[] eachline = new String[]{"1", "2", "00:01", "12:01", "3", "4"};
        flt.setRegisterno(eachline[0]);
        flt.setFlightno(eachline[1]);
        flt.setTakeoffTime(eachline[2]);
        flt.setLandingTime(eachline[3]);
        flt.setDest(eachline[4]);
        flt.setFlightDate(eachline[5]);
        assertEquals(new FlightDAO().getBoardingTime(flt), "23:16");
    }

    @Test
    void getFlight() {
        Flight flt = new Flight();
        String[] eachline = new String[]{"1", "2", "12:00", "12:01", "3", "4"};
        flt.setRegisterno(eachline[0]);
        flt.setFlightno(eachline[1]);
        flt.setTakeoffTime(eachline[2]);
        flt.setLandingTime(eachline[3]);
        flt.setDest(eachline[4]);
        flt.setFlightDate(eachline[5]);


    }

    @Test
    void getFlightTime1() {
        Flight flt = new Flight();
        String[] eachline = new String[]{"1", "2", "12:00", "12:01", "3", "4"};
        flt.setRegisterno(eachline[0]);
        flt.setFlightno(eachline[1]);
        flt.setTakeoffTime(eachline[2]);
        flt.setLandingTime(eachline[3]);
        flt.setDest(eachline[4]);
        flt.setFlightDate(eachline[5]);
        assertEquals(new FlightDAO().getFlightTime(flt), "0hour 1min");
    }

    @Test
    void getFlightTime2() {
        Flight flt = new Flight();
        String[] eachline = new String[]{"1", "2", "12:00", "12:57", "3", "4"};
        flt.setRegisterno(eachline[0]);
        flt.setFlightno(eachline[1]);
        flt.setTakeoffTime(eachline[2]);
        flt.setLandingTime(eachline[3]);
        flt.setDest(eachline[4]);
        flt.setFlightDate(eachline[5]);
        assertEquals(new FlightDAO().getFlightTime(flt), "0hour 57min");
    }

    @Test
    void getFlightTime() {
        Flight flt = new Flight();
        String[] eachline = new String[]{"1", "2", "12:00", "13:54", "3", "4"};
        flt.setRegisterno(eachline[0]);
        flt.setFlightno(eachline[1]);
        flt.setTakeoffTime(eachline[2]);
        flt.setLandingTime(eachline[3]);
        flt.setDest(eachline[4]);
        flt.setFlightDate(eachline[5]);
        assertEquals(new FlightDAO().getFlightTime(flt), "1hour 54min");
    }

    @Test
    void getFlightTime3() {
        Flight flt = new Flight();
        String[] eachline = new String[]{"1", "2", "23:00", "01:00", "3", "4"};
        flt.setRegisterno(eachline[0]);
        flt.setFlightno(eachline[1]);
        flt.setTakeoffTime(eachline[2]);
        flt.setLandingTime(eachline[3]);
        flt.setDest(eachline[4]);
        flt.setFlightDate(eachline[5]);
        assertEquals(new FlightDAO().getFlightTime(flt), "2hour 0min");
    }

    @Test
    void getFlightTime4() {
        Flight flt = new Flight();
        String[] eachline = new String[]{"1", "2", "14:45", "15:42", "3", "4"};
        flt.setRegisterno(eachline[0]);
        flt.setFlightno(eachline[1]);
        flt.setTakeoffTime(eachline[2]);
        flt.setLandingTime(eachline[3]);
        flt.setDest(eachline[4]);
        flt.setFlightDate(eachline[5]);
        assertEquals(new FlightDAO().getFlightTime(flt), "0hour 57min");
    }
}