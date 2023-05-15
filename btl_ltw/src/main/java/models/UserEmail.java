package models;

import java.util.UUID;

import utils.JsonUtils;

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
    public String Get_Insert_Fields_SQL() {
        return "id, email";
    }

    @Override
    public String Get_Insert_Values_SQL() {
        return "'" + id + "', '" + email + "'";
    }

    @Override
    public String Get_Update_Values_SQL() {
        return "id = '" + id + "', email = '" + email + "'";
    }public String To_Json_String() {
		return "{" +
				JsonUtils.PropToJson("id", id, true) +
				JsonUtils.PropToJson("email", email, true) +
				"}";
	}
};
