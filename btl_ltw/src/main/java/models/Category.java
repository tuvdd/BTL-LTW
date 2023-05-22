package models;

import java.util.UUID;

public class Category {
	public UUID id;
	public String name;
	public boolean status;
	public String url;

	public void set(UUID id, String name, boolean status, String url) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.url = url;
	}

	public UUID getId() {
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
};