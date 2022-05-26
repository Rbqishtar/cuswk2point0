package entitydao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDAOTest {

    @Test
    void verifyPayment() {
        assertTrue(new PaymentDAO().verifyPayment("111-111-11", "1-1", "qqq", PaymentDAO.CREDIT_CARD));
    }

    @Test
    void verifyPayment1() {
        assertFalse(new PaymentDAO().verifyPayment("112-111-11", "1-1", "qqq", PaymentDAO.CREDIT_CARD));
    }

    @Test
    void verifyPayment2() {
        assertFalse(new PaymentDAO().verifyPayment("111-111-11", "1-2", "qqq", PaymentDAO.CREDIT_CARD));
    }

    @Test
    void verifyPayment3() {
        assertFalse(new PaymentDAO().verifyPayment("", "1-1", "qq", PaymentDAO.CREDIT_CARD));
    }

    @Test
    void verifyPayment4() {
        assertTrue(new PaymentDAO().verifyPayment("W1", "WP1", "111111", PaymentDAO.WECHAT));
    }

    @Test
    void verifyPayment41() {
        assertFalse(new PaymentDAO().verifyPayment("", "WP1", "111111", PaymentDAO.WECHAT));
    }

    @Test
    void verifyPayment42() {
        assertFalse(new PaymentDAO().verifyPayment("W1", "W", "111111", PaymentDAO.WECHAT));
    }

    @Test
    void verifyPayment43() {
        assertFalse(new PaymentDAO().verifyPayment("W1", "P1", "11", PaymentDAO.WECHAT));
    }

    @Test
    void verifyPayment5() {
        assertTrue(new PaymentDAO().verifyPayment("A1", "AP1", "111111", PaymentDAO.ALIPAY));
    }

    @Test
    void verifyPayment51() {
        assertFalse(new PaymentDAO().verifyPayment("A1d", "AP1", "111111", PaymentDAO.ALIPAY));
    }

    @Test
    void verifyPayment52() {
        assertFalse(new PaymentDAO().verifyPayment("A1", "APdfsfasd1", "111dsd111", PaymentDAO.ALIPAY));
    }

    @Test
    void verifyPayment53() {
        assertFalse(new PaymentDAO().verifyPayment("A1", "AP1", "1", PaymentDAO.ALIPAY));
    }
}