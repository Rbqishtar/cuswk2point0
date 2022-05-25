package entity;

import java.util.Date;

public class Flight {

	private String registerno;
	private String flightno;
	private String takeoffTime;
	private String landingTime;
	private String dest;
	private String flightDate;
	private String[][] seatlist; //[10][6]
	
	public String getRegisterno() {
		return registerno;
	}
	
	public void setRegisterno(String registerno) {
		this.registerno = registerno;
	}
	
	public String getFlightno() {
		return flightno;
	}
	
	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}

	public String[][] getSeatlist() {
		return seatlist;
	}

	public void setSeatlist(String[][] seatlist) {
		this.seatlist = seatlist;
	}
	
	public String getTakeoffTime() {
		return takeoffTime;
	}

	public void setTakeoffTime(String takeoffTime) {
		this.takeoffTime = takeoffTime;
	}

	public String getLandingTime() {
		return landingTime;
	}

	public void setLandingTime(String landingTime) {
		this.landingTime = landingTime;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String date) {
		this.flightDate = date;
	}
}
