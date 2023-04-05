package models;

import java.util.UUID;

public class Admin extends Table {
	public String name;
	public String email;
	public String phonenum;
	public String cccd;

	public static String GetTableName() {
		return "admins";
	}

	public void set(UUID id, String name, String email, String phonenum, String cccd) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phonenum = phonenum;
		this.cccd = cccd;
	}

	public Admin() {
		super();
		this.TableName = "admins";
	}

	@Override
	protected String Get_Insert_Fields_SQL() {
		return "id, name, email, phonenum, cccd";
	}

	@Override
	protected String Get_Insert_Values_SQL() {
		return "gen_random_uuid(), '" + name + "', '" + email + "', '" + phonenum + "', '" + cccd + "'";
	}

	@Override
	protected String Get_Update_Values_SQL() {
		return "name = '" + name + "', email = '" + email + "', phonenum = '" + phonenum + "', cccd = '" + cccd + "'";
	}
};
