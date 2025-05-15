package Servlet;

import java.util.Date;

public class b1_Staff_BeanUtils {
	String fullname; 
	Date birthday; 
	boolean gender; 
	String[] hobbies; 
	String country; 
	double salary;
	public b1_Staff_BeanUtils(String fullname, Date birthday, boolean gender, String[] hobbies, String country,
			double salary) {
		super();
		this.fullname = fullname;
		this.birthday = birthday;
		this.gender = gender;
		this.hobbies = hobbies;
		this.country = country;
		this.salary = salary;
	} 
	public b1_Staff_BeanUtils(){}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	} 
	
	
	
	
}
