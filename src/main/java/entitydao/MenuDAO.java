package entitydao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Data Access Object for <code>Menu.csv</code> file
 * */
public class MenuDAO {

    /**
     * Get the plane by its register number
     *
     * @param types  The type of the menus to be extracted
     * @return A list of the extracted menu
     * */
    public ArrayList<String> getMenu(String types) {

        ArrayList<String> menus = new ArrayList<>();
        String line;
        String splitBy = ",";
        try {
            //surName,foreName,sex,phoneNumber,age,idnum,disabled
            BufferedReader bf = new BufferedReader(new FileReader("./src/main/resources/Menu.csv"));
            while ((line = bf.readLine()) != null) {
                String[] eachline = line.split(splitBy);
                if (eachline[0].equals(types)) {
                    menus.add(eachline[1]);

                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return menus;
    }

}
