package entitydao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.Booking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The data accessing object for the IO of <code>Booking.csv</code> file
 * */
public class BookingDAO {

    /**
     * The method that retrieves the booking of a given a <code>bookingno</code>. If the passenger forgot it, he can input id number ot surname instead.
     * <p>It reads the <code>Booking.csv</code> file and load them into a <code>JsonArray</code> object from <code>Gson</code> class.
     * The usage of <code>JsonArray</code> was considered because there may be several bookings corresponding to one key.</p>
     *
     * @param arg  The key for query, can be whether a <code>idnum, bookingno or a surname</code>
     * @return The <code>JsonArray</code> containing the required bookings
     * */
    public JsonArray getBooking(String arg) {
        Gson gson = new Gson();
        JsonArray jsonArr = new JsonArray();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("./src/main/resources/Booking.csv"));
            while ((line = bf.readLine()) != null) {
                String[] eachline = line.split(splitBy);
                for (int i=0; i<eachline.length; i++) {

                    if (eachline[i].equals(arg)) {
                        retrieveData(jsonArr, eachline);
                    }
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return jsonArr;
        }
    }

    /**
     * A method used to put a line of data in <code>Booking.csv</code> into a <code>JsonObject</code> and merge it into <code>JsonArray</code>,
     * using a <code>Booking</code> object as a helper.
     *
     * @param eachline  Represents the current line in file that is being read by the system
     * @param jsonArr  The <code>JsonArray</code> containing previously retrieved data
     * */
    private void retrieveData(JsonArray jsonArr, String[] eachline) {
        Booking bk = new Booking();
        bk.setIdnum(eachline[0]);
        bk.setFlightno(eachline[1]);
        bk.setBookingno(eachline[2]);
        bk.setTuoyun(Boolean.parseBoolean(eachline[3]));

        Gson gson = new Gson();
        String json = gson.toJson(bk);
        JsonObject jsonObj;
        jsonObj = gson.fromJson(json, JsonObject.class);
        jsonArr.add(jsonObj);
    }

    /**
     * Determines whether a passenger needs a luggage tag.
     * <p>First get all the bookings of the passenger by inputing <code>idnum</code> to <code>getBooking</code>, then select the booking that corresponds to
     * the current flight</p>
     *
     * @param idnum  Get all the bookings
     * @param flightno  Select the right booking that contains the information
     * @return Whether the <code>iftuoyun</code> was set to <code>true</code> or <code>false</code>
     * */
    public boolean getIfTuoyun(String idnum, String flightno) {
        JsonArray jsonArray = getBooking(idnum);
        for (JsonElement jsonElm: jsonArray) {
            JsonObject jsonObj = jsonElm.getAsJsonObject();
            if (jsonObj.get("flightno").getAsString().equals(flightno)) {
                if (jsonObj.get("tuoyun").getAsString().equals("true")) {
                    return true;
                }
            }
        }
        return false;
    }

}
