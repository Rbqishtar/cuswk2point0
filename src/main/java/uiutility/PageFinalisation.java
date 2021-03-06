package uiutility;

import entity.Order;
import entitydao.BookingDAO;
import entitydao.OrderDAO;
import ui.Finish_8;
import uicontrol.SeatCtrl;

import java.io.IOException;

/**
 * A utility to finalize the check in process. Works include:
 * <ol>
 *     <li>Convert the seat number in<code>odr</code> into numeric representations</li>
 *     <li>Insert the final seat number into the seat file</li>
 *     <li>Call <code>GenerationCtrl</code> to generate boarding passes, counter numbers and luggage tag</li>
 *     <li>Display the final page</li>
 * </ol>
 * */
public class PageFinalisation {

    /**
     * A method to finalise everything
     *
     * @param odr  The order generated by passenger through the check in process
     * */
    public void finaliseEverything(Order odr) throws IOException {
        OrderDAO odao = new OrderDAO();
        SeatCtrl sCtrl = new SeatCtrl();
        GenerationCtrl gc = new GenerationCtrl();

        odao.insertOrder(odr);
        String seatNo = odr.getSeatno();
        int row = sCtrl.reConvertSeatNo(seatNo)[0];
        int col = sCtrl.reConvertSeatNo(seatNo)[1];
        sCtrl.insertSeat(odr.getIdnum(), odr.getFlightNo(), row, col);
        gc.printBoardingPassAndCounterNumber(odr);
        if (new BookingDAO().getIfTuoyun(odr.getIdnum(), odr.getFlightNo()))
            gc.printLuggageTag(odr);
        Finish_8 f8 = new Finish_8();
        f8.setVisible(true);
    }

}
