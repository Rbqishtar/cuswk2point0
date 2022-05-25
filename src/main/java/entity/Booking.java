package entity;

public class Booking {

	private String idnum;
	private String flightno;
	private String bookingno;
	private boolean tuoyun;
	
	public String getIdnum() {
		return idnum;
	}
	
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	
	public String getFlightno() {
		return flightno;
	}
	
	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}

	public boolean isTuoyun() {
		return tuoyun;
	}

	public void setTuoyun(boolean tuoyun) {
		this.tuoyun = tuoyun;
	}

	public String getBookingno() { 
		return bookingno; 
	}

	public void setBookingno(String bkno) { 
		this.bookingno = bkno; 
	}

}
