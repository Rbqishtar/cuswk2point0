package entitydao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Data Access Object for the <code>Staff.csv</code> file
 * */
public class StaffDAO {

    /**
     * Validates the inputs from a staff by comparing it to the records in <code>Staff.csv</code>
     *
     * @param password  Input password
     * @param username  Input username
     * @return Whether there exists a staff whose password and username matches the 2 inputs
     * */
    public boolean validateStaff(String username, String password) {

        String line = "";
        String splitBy = ",";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("./src/main/resources/Staff.csv"));
            while ((line = bf.readLine()) != null) {
                String[] eachline = line.split(splitBy);
                if (eachline[0].equals(username) && eachline[1].equals(password)) {
                    bf.close();
                    return true;
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
