package models;

import java.util.UUID;

public class Admin {
	public UUID id;
	public String name;
	public String email;
	public String phonenum;
	public String cccd;
	public String username;
	public String password;

	public static String GetTableName() {
		return "admins";
	}

	public void set(UUID id, String name, String email, String phonenum, String cccd, String username,
			String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phonenum = phonenum;
		this.cccd = cccd;
		this.username = username;
		this.password = password;
	}

	public UUID getId() {
		if (id == null)
			id = UUID.randomUUID();
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
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

};
