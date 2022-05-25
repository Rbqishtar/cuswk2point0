package entitydao;

import entity.Plane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Data Access Object for the <code>Plane.csv</code> file
 * */
public class PlaneDAO {

    /**
     * Get the plane by its register number
     *
     * @param regno  The registration number of the plane to be extracted
     * @return The extracted plane
     * */
    public Plane getPlane(String regno) {

        Plane p = new Plane();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("Plane.csv"));
            while ((line = bf.readLine()) != null) {
                Object[] eachline = line.split(splitBy);
                if (eachline[0].equals(regno)) {
                    p.setRegisterno((String) eachline[0]);
                    p.setModel((String) eachline[1]);
                    p.setColumnNum((String) eachline[2]);
                    p.setMaxPsgr((String) eachline[3]);
                    break;
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

}
