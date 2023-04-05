
package demo.models;

import java.util.UUID;

public class Category extends Table {
	public String name;
	public boolean status;

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
	protected String Get_Insert_Fields_SQL() {
		return "id, name, status";
	}

	@Override
	protected String Get_Insert_Values_SQL() {
		return "gen_random_uuid(), '" + name + "', " + status + "";
	}

	@Override
	protected String Get_Update_Values_SQL() {
		return "name = '" + name + "', status = " + status + "";
	}
};