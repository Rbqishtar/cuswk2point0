package uicontrol;

import com.google.gson.JsonObject;
import entity.Flight;
import entity.Passenger;
import entitydao.FlightDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShowInfoCtrlTest {

    @Test
    void fetchDetail() {
        Passenger p = new Passenger();
        String[] eachline = new String[]{"narita taishin", "f", "17", "111", "false"};
        p.setName(eachline[0]);
        p.setSex(eachline[1]);
        p.setAge(Integer.parseInt(eachline[2]));
        p.setIdnum(eachline[3]);
        p.setDisabled(Boolean.parseBoolean(eachline[4]));

        Flight f = new Flight();
        eachline = new String[]{"AAAAAA", "AA1111", "12:00", "14:00", "a", "2022/3/29"};
        //AAAAAA,AA1111,12:00,14:00,a,2022/3/29
        f.setRegisterno(eachline[0]);
        f.setFlightno(eachline[1]);
        f.setTakeoffTime(eachline[2]);
        f.setLandingTime(eachline[3]);
        f.setDest(eachline[4]);
        f.setFlightDate(eachline[5]);

        FlightDAO fdao = new FlightDAO();

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("ID number", p.getIdnum());
        jsonObj.addProperty("Name", p.getName());
        jsonObj.addProperty("Flight number", f.getFlightno());
        jsonObj.addProperty("Registration number", f.getRegisterno());
        jsonObj.addProperty("Departure time", f.getTakeoffTime());
        jsonObj.addProperty("Flight duration", fdao.getFlightTime(f));
        jsonObj.addProperty("Destination", f.getDest());
        jsonObj.addProperty("Flight date", f.getFlightDate());
        jsonObj.addProperty("Estimated boarding time", fdao.getBoardingTime(f));

        assertEquals(new ShowInfoCtrl().fetchDetail(new String[]{"111", "AA1111"}), jsonObj);
    }

}