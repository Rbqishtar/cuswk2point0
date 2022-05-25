package uiutility;

import com.google.gson.JsonArray;
import entity.Order;
import entitydao.BookingDAO;
import entitydao.OrderDAO;
import ui.Finish_8;
import uicontrol.GenerationCtrl;
import uicontrol.SeatCtrl;

public class PageFinalisation {

    public void finaliseEverything(Order odr) {
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
