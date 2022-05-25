package uicontrol;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.Order;
import entity.Passenger;
import entitydao.BookingDAO;
import entitydao.FlightDAO;
import entitydao.OrderDAO;
import entitydao.PassengerDAO;

/**
 * The intermediate layer between Dashboard GUI and data DAO.
 * */
public class StaffDashboardCtrl {

    /**
     * Determines whether the flight number from input text field is valid or not.
     * <p>Returns <code>true</code> if there is a flight corresponding to the input i.e. the input is valid</p>
     *
     * @param input  The input from the staff in staff dashboard page
     * */
    public boolean inputIsValid(String input) {
        if (new FlightDAO().getFlight(input).getFlightno() == null) return false;
        else return true;
    }

    /**
     * Reads the flight number, and fetch the data of:
     * <ol>
     *     <li>The passengers of that flight</li>
     *     <li>If a passenger is checked in, the order information of him</li>
     * </ol>
     * Each passenger's information will be compiled into JSON by using <code>JsonObject</code>, and all the JSONs from passengers
     * will be compiled into a <code>JsonArray</code> from <code>Gson</code> class, and get returned.
     *
     * @param flightNo  The flight number to be queried
     * */
    public JsonArray getTableContents(String flightNo) {
        String idNum;
        JsonArray jsonArr = new BookingDAO().getBooking(flightNo);
        JsonArray toBeReturned = new JsonArray();
        for (JsonElement jsonElm: jsonArr) {
            JsonObject jsonObj = jsonElm.getAsJsonObject();
            idNum = jsonObj.get("idnum").getAsString();
            Passenger p = new PassengerDAO().getPsgr(idNum);
            Order o = new OrderDAO().getOrder(idNum, flightNo);
            JsonObject toBeAdded = new JsonObject();
            toBeAdded.addProperty("Name", p.getName());
            toBeAdded.addProperty("Sex", p.getSex());
            toBeAdded.addProperty("Age", p.getAge());
            toBeAdded.addProperty("Disabled", p.isDisabled());
            toBeAdded.addProperty("Flight", flightNo);
            toBeAdded.addProperty("Date", new FlightDAO().getFlight(flightNo).getFlightDate());
            if (o.getSeatno() != null) {
                toBeAdded.addProperty("Check in", true);
                toBeAdded.addProperty("Seat", o.getSeatno());
                toBeAdded.addProperty("Food Type", o.getFoodType());
                toBeAdded.addProperty("Food Menu", o.getFoodMenu());
                toBeAdded.addProperty("Drink", o.getDrink());
                toBeAdded.addProperty("Note", o.getNote());
            } else {
                toBeAdded.addProperty("Check in", false);
                toBeAdded.addProperty("Seat", "---");
                toBeAdded.addProperty("Food Type", "---");
                toBeAdded.addProperty("Food Menu", "---");
                toBeAdded.addProperty("Drink", "---");
                toBeAdded.addProperty("Note", "---");
            }
            toBeReturned.add(toBeAdded);
        }
        return toBeReturned;
    }

}
