package models;

import java.util.UUID;

import utils.JsonUtils;

public class Category extends Table {
	public String name;
	public boolean status;

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

	public Category() {
		super();
		TableName = "categories";
	}

	public void set(UUID id, String name, boolean status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public static String GetTableName() {
		return "categories";
	}

	@Override
	public String Get_Insert_Fields_SQL() {
		return "id, name, status";
	}

	@Override
	public String Get_Insert_Values_SQL() {
		return "gen_random_uuid(), '" + name + "', " + status + "";
	}

	@Override
	public String Get_Update_Values_SQL() {
		return "name = '" + name + "', status = " + status + "";
	}
	public String To_Json_String() {
		return "{" +
				JsonUtils.PropToJson("id", id, true) +
				JsonUtils.PropToJson("name", name, true) +
				JsonUtils.PropToJson("status", status, false) +
				"}";
	}

};