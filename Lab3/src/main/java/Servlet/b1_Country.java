package Servlet;

public class b1_Country {
	String id;
	String name;
	
	public b1_Country(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public b1_Country() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

