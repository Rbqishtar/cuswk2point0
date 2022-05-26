package uicontrol;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.Order;
import entitydao.BookingDAO;
import entitydao.OrderDAO;

import javax.swing.*;

/**
 * The controller for passenger login page, responsible for communicating between it and <code>BookingDAO, OrderDAO</code>
 * */
public class PsgrLoginCtrl {

    /**
     * Checks whether the passenger has at least one booking or not. This can also function as an input checker.
     *
     * @param arg  The input from the passenger to be passed to <code>BookingDAO</code>
     * @return <code>false</code> if there are no bookings for the input, <code>true</code> otherwise
     * */
    public boolean canGetDetail(String arg,boolean state) {
        JsonArray jsonArr = new BookingDAO().getBooking(arg);
        if (jsonArr.size() == 0) return false;
        else return true;
    }

    /**
     * Get the <code>idnum</code> and <code>flightno</code> of the booking which <code>idnum</code> or <code>bookingno</code>
     * is the same as <code>arg</code>
     *
     * @param arg  The key to be queried
     * @return The String[] of the data, first element is <code>idnum</code> and second element is <code>flightno</code>
     * */
    public String[] getIdnumAndFlightNo(String arg) {
        String[] s = new String[2];
        JsonArray jsonArr = new BookingDAO().getBooking(arg);
        for (JsonElement jsonElm: jsonArr) {
            if (jsonElm.isJsonObject()) {
                JsonObject jsonObj = jsonElm.getAsJsonObject();
                s[0] = jsonObj.get("idnum").getAsString();
                s[1] = jsonObj.get("flightno").getAsString();
                return s;
            }
        }
        return null;
    }

    /**
     * Notify that the passenger has made a check in before. It also displays the information about that check in.
     *
     * @param checkodr  The existing order of the passenger in <code>Order.csv</code>
     * */
    public void alertCheckedIn(Order checkodr) {
        JOptionPane.showConfirmDialog(null, "You have checked in, mate" +
                        "\nFlight: " + checkodr.getFlightNo() +
                        "\nSeat: " + checkodr.getSeatno() +
                        "\nFood : " + checkodr.getFoodType() + ", " + checkodr.getFoodMenu(),
                "?", JOptionPane.YES_OPTION);
    }


    /**
     * Fetches the existing order of the passenger who has made a check in before
     *
     * @param idnum  1/2 keys for fetching the order
     * @param flightno  Another key
     * */
    public Order getExistingOrder(String idnum, String flightno) {
        return new OrderDAO().getOrder(idnum, flightno);
    }
}
