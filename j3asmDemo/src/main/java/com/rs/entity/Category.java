package com.rs.entity;

public class Category {
	private long id;
	private String name;
	private boolean active;

	public Category() {
		super();
	}

	public Category(long id, String name, boolean active) {
		this.id = id;
		this.name = name;
        this.active = active;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRepId() {
		return "CT" + id;
	}

	public Object[] toInsertData() {
		Object[] data = { name };
		return data;
	}

	public Object[] toUpdateData() {
		Object[] data = { name, id };
		return data;
	}

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
