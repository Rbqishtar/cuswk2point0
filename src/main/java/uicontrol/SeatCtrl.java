package uicontrol;

import entity.Order;
import entity.Plane;
import entitydao.FlightDAO;
import entitydao.PlaneDAO;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * The controller for actions relating to seat, responsible for communicating
 * between pages and seat files (<code>XXXXXX_seats.csv</code>) as well as <code>FlightDAO, PlaneDAO</code>
 * */
public class SeatCtrl {

    /**
     * Get all the seats of a given flight and return it as an <code>ArrayList</code>, enabled by the special design in seat files
     *
     * @param flightno  The flight number
     * @return The seats of the flight, represented as a list
     * */
    public ArrayList<String[]> getSeat(String flightno) {

        ArrayList<String[]> seats = new ArrayList<>();
        String line;
        String splitBy = ",";
        int i = 0;
        try {
            //surName,foreName,sex,phoneNumber,age,idnum,disabled
            BufferedReader bf = new BufferedReader(new FileReader(flightno + "_seats.csv"));
            while ((line = bf.readLine()) != null) {
                String[] eachline = line.split(splitBy);
                seats.add(eachline);
                i++;
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return seats;
    }

    /**
     * Determine whether the seat is able to be inserted or not
     *
     * @param flightno  The flight number of the seats
     * @param col  Column number
     * @param row  Row number
     * @return <code>true</code> if able
     * */
    public boolean canInsertSeat(String flightno, int row, int col) {
        ArrayList<String[]> a = this.getSeat(flightno);
        return a.get(row)[col - 1].equals("0");
    }

    /**
     * Insert the seat selected by the passenger into the flight
     *
     * @param flightno  The flight number
     * @param idnum  The id number of the passenger
     * @param col  Column number
     * @param row  Row number
     * */
    public void insertSeat(String idnum, String flightno, int row, int col) {
        try {
            PrintWriter out;
            ArrayList<String[]> a = this.getSeat(flightno);
            out = new PrintWriter(new BufferedWriter(new FileWriter(flightno + "_seats.csv")));
            a.get(row)[col - 1] = idnum;
            for (String[] s : a) {
                String sb;
                if (getColNumber(flightno) == 6)
                    sb = s[0] + "," + s[1] + "," + s[2] + "," + s[3] + "," + s[4] + "," + s[5];
                else if (getColNumber(flightno) == 8)
                    sb = s[0] + "," + s[1] + "," + s[2] + "," + s[3] + "," + s[4] + "," + s[5] + "," + s[6] + "," + s[7];
                else sb = "";
                out.println(sb);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Convert the numeral seat number to standard seat number<p>Eg: row 5, column 8 -> 5H</p>
     *
     * @param col  Column number
     * @param row  Row number
     * @return  The converted seat number
     * */
    public String convertSeatNo(int row, int col) {
        String s = "";
        switch (col) {
            case 1 -> s = "A";
            case 2 -> s = "B";
            case 3 -> s = "C";
            case 4 -> s = "D";
            case 5 -> s = "E";
            case 6 -> s = "F";
            case 7 -> s = "G";
            case 8 -> s = "H";
            default -> s = "";
        }
        return row + s;
    }

    /**
     * Convert the standard seat number to numeral seat number<p>Eg: 5B -> row 5, column 2</p>
     *
     * @param seatNo  The seat number to be converted
     * @return The int[] containing the row number and column number
     * */
    public int[] reConvertSeatNo(String seatNo) {
        String rowString = seatNo.substring(0, seatNo.length() - 1);
        String colString = seatNo.substring(seatNo.length() - 1);
        int row = Integer.parseInt(rowString);
        int col = 0;
        switch (colString) {
            case "A" -> col = 1;
            case "B" -> col = 2;
            case "C" -> col = 3;
            case "D" -> col = 4;
            case "E" -> col = 5;
            case "F" -> col = 6;
            case "G" -> col = 7;
            case "H" -> col = 8;
            default -> col = 0;
        }
        return new int[]{row, col};

    }

    /**
     * Generate a random seat and set the <code>seatExtra</code> and <code>seatNo</code> in order
     *
     * @param odr  The <code>order</code> object to be modified
     * */
    public void generateRandomSeat(Order odr) {
        while (true) {
            int row = (int)(10 * Math.random() + 1);
            int col = (int)(6 * Math.random() + 1);
            if (canInsertSeat(odr.getFlightNo(), row, col)) {
                odr.setSeatno(convertSeatNo(row, col));
                odr.setSeatExtra(0);
                break;
            }
        }
    }

    /**
     * Get the corresponding row number from the select item in seat selection page
     *
     * @param selectedIndex  The selected index from the page
     * @param flightNo  The flight number that the passenger is to take
     * @return The row number
     * */
    public int getRowFromSelectedIndex(int selectedIndex, String flightNo) {
        int row = 0;
        int maxRow = getMaxRowNumber(flightNo);
        switch (selectedIndex) {
            case 0 -> {
                do {
                    row = (int) (10 * Math.random() + 1);
                } while (row == maxRow * 0.6 || row == maxRow * 0.4);
            }
            case 1 -> row = (int)(maxRow * 0.6);
            case 2 -> row = (int)(10 * Math.random() + 4);
            case 3 -> row = (int)(maxRow - (10 * Math.random() + 1));
            case 4 -> row = (int)(maxRow * 0.4);
            case 5 -> row = (int)(3 * Math.random() + 1);
            default -> JOptionPane.showConfirmDialog(null, "pref error", "?", JOptionPane.YES_OPTION);
        }
        return row;
    }

   /**
     * Gets the max row number according to the plane type of the flight which flight number is the same as input
     *
     * @param flightno  Flight number
     * @return The max row number of the plane
     * */
    public int getMaxRowNumber(String flightno) {
        String regNo = new FlightDAO().getFlight(flightno).getRegisterno();
        Plane p = new PlaneDAO().getPlane(regNo);
        String maxpsgr = p.getMaxPsgr();
        String colNum = p.getColumnNum();
        int i = Integer.parseInt(maxpsgr) / Integer.parseInt(colNum);
        if (i >= 0) return i;
        else {
            JOptionPane.showConfirmDialog(null, "getMaxRowNumber error");
            return -1;
        }
    }

    /**
     * Gets the column number according to the plane type of the flight which flight number is the same as input
     *
     * @param flightno  Flight number
     * @return The column number of the plane
     * */
    public int getColNumber(String flightno) {
        String regNo = new FlightDAO().getFlight(flightno).getRegisterno();
        Plane p = new PlaneDAO().getPlane(regNo);
        String colNum = p.getColumnNum();
        int i = Integer.parseInt(colNum);
        if (i == 6 || i == 8) return i;
        else {
            JOptionPane.showConfirmDialog(null, "getColNumber error");
            return -1;
        }
    }

    /**
     * Gets the extra price for the given row number. First class -> 1000 extra bucks, extra legroom -> 100 extra bucks.
     *
     * @param row  The row number
     * @return The corresponding extra fee
     * */
    public int calculateSeatExtra(int row) {
        switch (row) {
            case 1, 2, 3 -> {
                return 1000;
            }
            case 12 -> {
                return 100;
            }
            default -> {
                return 0;
            }
        }
    }

}
