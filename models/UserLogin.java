
package demo.models;

import java.util.UUID;

public class UserLogin extends Table {
    public String username;
    public String password;

    public UserLogin() {
        super();
        this.TableName = "user_logins";
    }

    public static String GetTableName() {
		return "user_logins";
	}
    

    public void set(UUID id, String username,
            String password) {
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
};