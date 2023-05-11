package models;

import java.util.UUID;

import utils.JsonUtils;

public class ReceivedNewsEmail extends Table {
    public String email;

    public ReceivedNewsEmail() {
        super();
        this.TableName = "received_news_emails";
    }

    public void set(UUID id, String email) {
        this.id = id;
        this.email = email;
    }

    public static String GetTableName() {
		return "received_news_emails";
	}
    

    @Override
    protected String Get_Insert_Fields_SQL() {
        return "id, email";
    }

    @Override
    protected String Get_Insert_Values_SQL() {
        return "gen_random_uuid(), '" + email + "'";
    }

    @Override
    protected String Get_Update_Values_SQL() {
        return "email = '" + email + "'";
    }public String To_Json_String() {
		return "{" +
				JsonUtils.PropToJson("id", id, true) +
				JsonUtils.PropToJson("email", email, true) +
				"}";
	}
}
