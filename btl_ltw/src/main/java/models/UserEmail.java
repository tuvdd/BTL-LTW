
package demo.models;

import java.util.UUID;

public class UserEmail extends Table {
    public String email;

    public UserEmail() {
        super();
        this.TableName = "user_emails";
    }

    public void set(UUID id, String email) {
        this.id = id;
        this.email = email;
    }

    public static String GetTableName() {
		return "user_emails";
	}
    

    @Override
    protected String Get_Insert_Fields_SQL() {
        return "id, email";
    }

    @Override
    protected String Get_Insert_Values_SQL() {
        return "'" + id + "', '" + email + "'";
    }

    @Override
    protected String Get_Update_Values_SQL() {
        return "id = '" + id + "', email = '" + email + "'";
    }
};
