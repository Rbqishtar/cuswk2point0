package uicontrol;

import entitydao.PaymentDAO;

import javax.swing.*;

/**
 * The controller for payment page, responsible for communicating between it and <code>PaymentDAO</code>
 * */
public class PaymentCtrl {
    /**
     * Call the <code>verifyPayment</code> method in <code>PaymentDAO</code>
     *
     * @param method  The payment method to be passed
     * @param s1  1/3 inputs to be passed
     * @param s2  2/3 inputs to be passed
     * @param s3  3/3 inputs to be passed
     * @return The inputs match the records in file or not
     * */
    public boolean verifyPayment(String method, String s1, String s2, String s3) {
        PaymentDAO pdao = new PaymentDAO();
        boolean isPayed = false;
        switch (method) {
            case "Credit Card" -> isPayed = pdao.verifyPayment(s1, s2, s3, PaymentDAO.CREDIT_CARD);
            case "Wechat" -> isPayed = pdao.verifyPayment(s1, s2, s3, PaymentDAO.WECHAT);
            case "Alipay" -> isPayed = pdao.verifyPayment(s1, s2, s3, PaymentDAO.ALIPAY);
            default -> JOptionPane.showConfirmDialog(null, "Payment method error");
        }
        return isPayed;
    }

}
