package models;
import java.sql.Timestamp;
import java.util.UUID;

import utils.JsonUtils;

public class Order extends Table {
    public Timestamp created_time;
    public int status;
    public String address;
    public String phonenum;
    public String buyer_name;

    public Order() {
        super();
        TableName = "orders";
    }

    public static String GetTableName() {
		return "orders";
	}
    

    @Override
    public String Get_Insert_Fields_SQL() {
        return "id,created_time,status,address,phonenum,buyer_name";
    }

    @Override
    public String Get_Insert_Values_SQL() {
        return "gen_random_uuid(), " +
                "'" + created_time + "', " +
                "" + status + ", " +
                "'" + address + "', " +
                "'" + phonenum + "', " +
                "'" + buyer_name + "'";
    }

    @Override
    public String Get_Update_Values_SQL() {
        return "created_time = '" + created_time + "', " +
                "status = '" + status + "', " +
                "address = '" + address + "', " +
                "phonenum = '" + phonenum + "', " +
                "buyer_name = '" + buyer_name + "'";
    }

    public void set(UUID id, Timestamp created_time, int status, String address, String phonenum, String buyer_name) {
        this.id = id;
        this.created_time = created_time;
        this.status = status;
        this.address = address;
        this.phonenum = phonenum;
        this.buyer_name = buyer_name;
    }
    public String To_Json_String() {
		return "{" +
				JsonUtils.PropToJson("id", id, true) +
				JsonUtils.PropToJson("created_time", created_time, true) +
				JsonUtils.PropToJson("status", status, false) +
                JsonUtils.PropToJson("address", address, true) +
				JsonUtils.PropToJson("phonenum", phonenum, true) +
                JsonUtils.PropToJson("buyer_name", buyer_name, true) +
				"}";
	}
}
