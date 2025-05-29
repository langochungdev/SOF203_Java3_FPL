package com.rs.entity;

public class Newsletter {
	private String email;
	private boolean enabled; // true = still valid

	public Newsletter() {
		super();
	}

	public Newsletter(String email, boolean enabled) {
		this.email = email;
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Object[] toInsertData() {
		Object[] data = { email, enabled };
		return data;
	}

	public Object[] toUpdateData() {
		Object[] data = { enabled, email };
		return data;
	}
}
