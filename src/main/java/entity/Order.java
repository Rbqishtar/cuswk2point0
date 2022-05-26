package entity;

import java.util.Objects;

public class Order {
	
	private String idnum;
	private String flightno;
	private String flightDate;
	private String foodType;
	private String foodMenu;
	private String seatno;
	private String drink;
	private int seatExtra;
	private int mealExtra;
	private String note;

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getSeatno() {
		return seatno;
	}

	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}

	public String getFlightNo() {
		return flightno;
	}

	public void setFlightNo(String flightid) {
		this.flightno = flightid;
	}

	public String getFoodMenu() {
		return foodMenu;
	}

	public void setFoodMenu(String foodMenu) {
		this.foodMenu = foodMenu;
	}
	
	public String getIdnum() {
		return idnum;
	}
	
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	
	public String getFlightDate() {
		return flightDate;
	}
	
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public int getMealExtra() { return mealExtra; }

	public void setMealExtra(int mealextra) { this.mealExtra = mealextra; }

	public int getSeatExtra() { return seatExtra; }

	public void setSeatExtra(int seatExtra) { this.seatExtra = seatExtra; }

	public String getDrink() { return drink; }

	public void setDrink(String drink) { this.drink = drink; }

	public String getNote() { return note; }

	public void setNote(String note) { this.note = note; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return seatExtra == order.seatExtra && mealExtra == order.mealExtra && Objects.equals(idnum, order.idnum) && Objects.equals(flightno, order.flightno) && Objects.equals(flightDate, order.flightDate) && Objects.equals(foodType, order.foodType) && Objects.equals(foodMenu, order.foodMenu) && Objects.equals(seatno, order.seatno) && Objects.equals(drink, order.drink) && Objects.equals(note, order.note);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idnum, flightno, flightDate, foodType, foodMenu, seatno, drink, seatExtra, mealExtra, note);
	}
}
