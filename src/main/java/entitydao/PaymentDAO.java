package entitydao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The data access object for files that contains the payment information, including <code>Cards.csv, Wechat.csv and Alipay.csv</code>
 * */
public class PaymentDAO {

    // Flags that were used to identify different payment methods
    public static int CREDIT_CARD = 0;
    public static int WECHAT = 1;
    public static int ALIPAY = 2;

    /**
     * Verify the payment by comparing 3 inputs from the passenger to the corresponding file, according to <code>mode</code>.
     *
     * @param mode  Determines the payment method, and reads different files according to the different mode.
     * @param s1  1/3 inputs
     * @param s2  2/3 inputs
     * @param s3  3/3 inputs
     * @return The inputs match the records in file or not
     * */
    public boolean verifyPayment(String s1, String s2, String s3, int mode) {
        
        String line = "";
        String splitBy = ",";
        BufferedReader bf;
        try {

            switch (mode) {
                case 0 -> bf = new BufferedReader(new FileReader("Cards.csv"));
                case 1 -> bf = new BufferedReader(new FileReader("Wechat.csv"));
                case 2 -> bf = new BufferedReader(new FileReader("Alipay.csv"));
                default -> { return false; }
            }

            while ((line = bf.readLine()) != null) {
                String[] eachline = line.split(splitBy);
                if (eachline[0].equals(s1) && eachline[1].equals(s2) && eachline[2].equals(s3)) {
                    bf.close();
                    return true;
                }
            }
            bf.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
