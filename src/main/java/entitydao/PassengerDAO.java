package entitydao;

import java.io.*;
import java.util.ArrayList;

import entity.Booking;
import entity.Flight;
import entity.Order;
import entity.Passenger;

/**
 * Data access object for <code>Passenger.csv</code> file.
 * */
public class PassengerDAO {

     /**
     * Get the passenger by using his <code>idnum</code> as the key. <p>Because there can only exist one passenger per <code>idnum</code>,
     * There is no need to use <code>JsonArray</code> object as in <code>BookingDAO</code>. Instead, the extracted <code>Passenger</code> object was returned.
     *
     * @param idnum  The key of the query
     * @return The extracted <code>Passenger</code> object
     * */
    public Passenger getPsgr(String idnum) {

        Passenger psgr = new Passenger();
        String line = "";
        String splitBy = ",";
        try {
            //surName,foreName,sex,phoneNumber,age,idnum,disabled
            BufferedReader bf = new BufferedReader(new FileReader("Passenger.csv"));
            while ((line = bf.readLine()) != null) {
                String[] eachline = line.split(splitBy);
                if (eachline[3].equals(idnum)) {
                    psgr.setName(eachline[0]);
                    psgr.setSex(eachline[1]);
                    psgr.setAge(Integer.parseInt(eachline[2]));
                    psgr.setIdnum(eachline[3]);
                    psgr.setDisabled(Boolean.parseBoolean(eachline[4]));
                    break;
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return psgr;
    }

}
