package entitydao;

import entity.Flight;
import java.io.*;

/**
 * The data accessing object for the IO of <code>Flight.csv</code> file
 * */
public class FlightDAO {

    /**
     * Calculates the boarding time of a given flight according to its departure time.
     *
     * @param f  The flight that need to be queried
     * @return The formatted boarding time
     * */
    public String getBoardingTime(Flight f) {
        String[] time = f.getTakeoffTime().split(":");
        // Generate boarding time
        int hr = Integer.parseInt(time[0]);
        int min = Integer.parseInt(time[1]);
        if ((min-45) < 0) {
            min += 15;
            if (hr == 0) hr=23;
            else hr--;
        } else min -= 45;
        return hr + ":" + min;
    }

    /**
     * Get the flight from <code>Flight.csv</code> which <code>flightno</code> matches the key. One flight number only corresponds to one flight, so
     * using <code>JsonArray</code> for return as in <code>BookingDAO</code> is unnecessary. Therefore, the data was returned by using <code>Flight</code> object.
     *
     * @param flightno  The flight number of the flight to be extracted
     * @return The extracted <code>Flight</code> object
     * */
    public Flight getFlight(String flightno) {

        Flight flt = new Flight();
        String line;
        String splitBy = ",";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("Flight.csv"));
            while ((line = bf.readLine()) != null) {
                String[] eachline = line.split(splitBy);
                if (eachline[1].equals(flightno)) {
                    flt.setRegisterno(eachline[0]);
                    flt.setFlightno(eachline[1]);
                    flt.setTakeoffTime(eachline[2]);
                    flt.setLandingTime(eachline[3]);
                    flt.setDest(eachline[4]);
                    flt.setFlightDate(eachline[5]);
                    break;
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flt;
    }

    /**
     * Calculates the flight time of a given flight according to its departure time and landing time.
     *
     * @param f  The flight that need to be queried
     * @return The formatted flight duration
     * */
    public String getFlightTime(Flight f) {
        String[] up = f.getTakeoffTime().split(":");
        String[] down = f.getLandingTime().split(":");
        int hour = Integer.parseInt(down[0]) - Integer.parseInt(up[0]);
        int min = Integer.parseInt(down[1]) - Integer.parseInt(up[1]);
        if (min < 0) {
            hour -= 1;
            min += 60;
        }
        if (hour < 0) {
            hour += 24;
        }

        return hour + "hour " + min + "min";
    }

}
