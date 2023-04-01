package demo.models;

import java.sql.Timestamp;
import java.util.UUID;

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
    protected String Get_Insert_Fields_SQL() {
        return "id,created_time,status,address,phonenum,buyer_name";
    }

    @Override
    protected String Get_Insert_Values_SQL() {
        return "gen_random_uuid(), " +
                "'" + created_time + "', " +
                "" + status + ", " +
                "'" + address + "', " +
                "'" + phonenum + "', " +
                "'" + buyer_name + "'";
    }

    @Override
    protected String Get_Update_Values_SQL() {
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

}
