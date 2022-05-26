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
     * The method that retrieves the booking of a given <code>idnum</code> or a <code>bookingno</code>
     * <p>It reads the <code>Booking.csv</code> file and load them into a <code>JsonArray</code> object from <code>Gson</code> class.
     * The usage of <code>JsonArray</code> was considered because there may be several bookings corresponding to one key.</p>
     *
     * @param arg  The key for query, can be whether a <code>idnum</code> or a <code>bookingno</code>
     * @return The <code>JsonArray</code> containing the required bookings
     * */
    public JsonArray getBooking(String arg) {
        Booking bk = new Booking();
        Gson gson = new Gson();
        JsonArray jsonArr = new JsonArray();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("Booking.csv"));
            while ((line = bf.readLine()) != null) {
                String[] eachline = line.split(splitBy);
                for (int i=0; i<eachline.length; i++) {

                    if (eachline[i].equals(arg)) {
                        interact(bk, gson, jsonArr, eachline);
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
    public JsonArray getBookingV2(String arg,boolean state) {
        //when state=true means retrieving for booking number,state=false means retrieving for ID or surname
        Booking bk = new Booking();
        Gson gson = new Gson();
        JsonArray jsonArr = new JsonArray();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("Booking.csv"));
            while ((line = bf.readLine()) != null) {
                String[] eachline = line.split(splitBy);
                if(state) {
                    if (eachline[2].equals(arg)) {
                        interact(bk, gson, jsonArr, eachline);
                    }
                }else{
                    if (eachline[0].equals(arg)||eachline[4].equals(arg)) {
                        interact(bk, gson, jsonArr, eachline);
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

    private void interact(Booking bk, Gson gson, JsonArray jsonArr, String[] eachline) {
        bk.setIdnum(eachline[0]);
        bk.setFlightno(eachline[1]);
        bk.setBookingno(eachline[2]);
        bk.setTuoyun(Boolean.parseBoolean(eachline[3]));
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
