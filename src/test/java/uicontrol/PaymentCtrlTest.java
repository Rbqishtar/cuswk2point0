package uicontrol;

import entitydao.PaymentDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentCtrlTest {

    @Test
    void verifyPayment() {
        assertTrue(new PaymentCtrl().verifyPayment("Credit Card", "111-111-11", "1-1", "qqq"));
        assertTrue(new PaymentCtrl().verifyPayment("Credit Card", "222-222-22", "2-2", "www"));
        assertTrue(new PaymentCtrl().verifyPayment("Credit Card", "333-333-33", "3-3", "eee"));
        assertFalse(new PaymentCtrl().verifyPayment("Credit Card", "111-1112321-11", "2131-1", "q321qq"));
        assertTrue(new PaymentCtrl().verifyPayment("Wechat", "W1", "WP1", "111111"));
        assertTrue(new PaymentCtrl().verifyPayment("Wechat", "W2", "WP2", "222222"));
        assertFalse(new PaymentCtrl().verifyPayment("Wechat", "111-111-11", "1-1", "qqq"));
        assertTrue(new PaymentCtrl().verifyPayment("Alipay", "A1", "AP1", "111111"));
        assertTrue(new PaymentCtrl().verifyPayment("Alipay", "A2", "AP2", "222222"));
        assertFalse(new PaymentCtrl().verifyPayment("Alipay", "111-111-11", "1-1", "qqq"));

    }
}