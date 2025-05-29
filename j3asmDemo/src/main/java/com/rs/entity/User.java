package com.rs.entity;

import java.sql.Date;

public class User {
	private long id;
	private String email;
	private String password;
	private String fullname;
	private String username;
	private Date birthday;
	private String avatar;
	private boolean gender;
	private String mobile;
	private boolean role; // true = Admin, false = Reporter
	private boolean active;

	public User() {
		super();
	}

	public User(long id, String username, String password, String fullname, Date birthday, String avatar, boolean gender,
                String mobile, String email, boolean role, boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.birthday = birthday;
        this.avatar = avatar;
        this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.role = role;
        this.active = active;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return role;
	}
	
	public boolean isReporter() {
		return !role;
	}

	public boolean getRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	public String getRepId() {
		return "UR" + id;
	}

	public Object[] toInsertData() {
        return new Object[]{ email, password, fullname, username, avatar, birthday, gender, mobile, role };
	}

	public Object[] toUpdateData() {
        return new Object[]{ email, password, fullname, username, avatar, birthday, gender, mobile, role, id };
	}

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAvatar() {
        return avatar;
    }

	public String getAvatarPath() {
		return "/photo/" + avatar;
	}

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
