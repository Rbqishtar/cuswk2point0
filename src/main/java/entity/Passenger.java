package entity;

import java.util.Objects;

public class Passenger {

    private String name;
	private String sex;
	private int age;
	private String idnum;
	private boolean disabled;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Passenger passenger = (Passenger) o;
		return age == passenger.age && disabled == passenger.disabled && Objects.equals(name, passenger.name) && Objects.equals(sex, passenger.sex) && Objects.equals(idnum, passenger.idnum);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, sex, age, idnum, disabled);
	}
}
