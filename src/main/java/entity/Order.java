package entity;

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
}
