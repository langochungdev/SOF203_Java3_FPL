package Servlet;

public class b4_User {
	String fullname;
	boolean gender;
	String country;
	
	public b4_User() {
	}
	public b4_User(String fullname, boolean gender, String country) {
		super();
		this.fullname = fullname;
		this.gender = gender;
		this.country = country;
	}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getcountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
