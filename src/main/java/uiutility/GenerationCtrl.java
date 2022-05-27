package uiutility;

import entity.Flight;
import entity.Order;
import entity.Passenger;
import entitydao.FlightDAO;
import entitydao.PassengerDAO;

import java.io.*;

/**
 * A class to generate boarding pass, counter number and luggage tag according to the <code>Order</code> made by passenger
 * through the check in process.
 * */
public class GenerationCtrl {

    /**
     * Generates the boarding pass and counter number, output as a .txt file
     *
     * @param odr  The <code>Order</code> generated by the passenger
     * */
    public void printBoardingPassAndCounterNumber(Order odr) {
        Passenger psgr = new PassengerDAO().getPsgr(odr.getIdnum());
        Flight flight = new FlightDAO().getFlight(odr.getFlightNo());
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(psgr.getName() + flight.getFlightno() + flight.getFlightDate() + ".txt")));
            out.println("--------------------------------------------------------------");
            out.println("\t\t\t" + "Your name: " + psgr.getName());
            out.println("\t\t\t" + "Flight number & destination: " + flight.getFlightno() + ", " + flight.getDest());
            out.println("\t\t\t" + "Flight date & time: " + flight.getFlightDate() + "," + flight.getTakeoffTime());
            out.println("\t\t\t" + "Counter number: " + (int)(50 * Math.random() + 1));
            out.println("\t\t\t" + "GOODBYE");
            out.println("\t\t\t");
            out.println("\t\t\t");
            out.println("--------------------------------------------------------------");
            out.flush();
        }catch (IOException e) {
            System.err.println(e);
        }finally{
            if(out != null){
                out.close();
            }
        }
    }

    /**
     * Generates the luggage tag, output as a .txt file
     *
     * @param odr  The <code>Order</code> generated by the passenger
     * */
    public void printLuggageTag(Order odr) {
        Passenger psgr = new PassengerDAO().getPsgr(odr.getIdnum());
        Flight flight = new FlightDAO().getFlight(odr.getFlightNo());
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("LuGGageTAg" + psgr.getName() + flight.getFlightno() + flight.getFlightDate() + ".txt")));
            out.println("--------------------------------------------------------------");
            out.println("\t\t\t" + "Name: " + psgr.getName());
            out.println("\t\t\t" + "Flight number & destination: " + flight.getFlightno() + ", " + flight.getDest());
            out.println("\t\t\t" + "Flight date & time: " + flight.getFlightDate() + "," + flight.getTakeoffTime());
            out.println("\t\t\t" + "Luggage No: " + (50 * Math.random() + 1));
            out.println("--------------------------------------------------------------");
            out.flush();
        }catch (IOException e) {
            System.err.println(e);
        }finally{
            if(out != null){
                out.close();
            }
        }
    }

}