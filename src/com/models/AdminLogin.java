
package demo.models;

import java.util.UUID;

public class AdminLogin extends Table {
    public String username;
    public String password;

    public AdminLogin() {
        super();
        TableName = "admin_logins";
    }

    public static String GetTableName() {
		return "admin_logins";
	}

    public void set(UUID id,String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    protected String Get_Insert_Fields_SQL() {
        return "id, username, password";
    }

    @Override
    protected String Get_Insert_Values_SQL() {
        return "'" + id + "', '" + username + "', '" + password + "'";
    }

    @Override
    protected String Get_Update_Values_SQL() {
        return "username = '" + username + "', password = '" + password + "'";
    }
}