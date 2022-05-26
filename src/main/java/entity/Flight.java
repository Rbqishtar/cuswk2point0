package entity;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Flight {

	private String registerno;
	private String flightno;
	private String takeoffTime;
	private String landingTime;
	private String dest;
	private String flightDate;
	
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Flight flight = (Flight) o;
		return Objects.equals(registerno, flight.registerno) && Objects.equals(flightno, flight.flightno) && Objects.equals(takeoffTime, flight.takeoffTime) && Objects.equals(landingTime, flight.landingTime) && Objects.equals(dest, flight.dest) && Objects.equals(flightDate, flight.flightDate);
	}

}
