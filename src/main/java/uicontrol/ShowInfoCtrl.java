package uicontrol;

import com.google.gson.JsonObject;
import entity.Flight;
import entity.Passenger;
import entity.Plane;
import entitydao.FlightDAO;
import entitydao.PassengerDAO;
import entitydao.PlaneDAO;

import javax.swing.*;

public class ShowInfoCtrl {

    public JsonObject fetchDetail(String[] idNumAndFlightNo) {
        if (idNumAndFlightNo.length != 2) {
            JOptionPane.showConfirmDialog(null, "Error in array length fetched from page 1");
            return null;
        }
        Passenger p = new PassengerDAO().getPsgr(idNumAndFlightNo[0]);
        Flight f = new FlightDAO().getFlight(idNumAndFlightNo[1]);
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
        return jsonObj;
    }

    public void seePlane(JsonObject jsonObj) {
        PlaneDAO pldao = new PlaneDAO();
        Plane pl = pldao.getPlane(jsonObj.get("Registration number").getAsString());
        JOptionPane.showConfirmDialog(null, "Info about this plane: " +
                "\nPlane model: " + pl.getModel() +
                "\nColumn number: " + pl.getColumnNum() +
                "\nMax passenger: " + pl.getMaxPsgr(), " ", JOptionPane.YES_OPTION);
    }

}
