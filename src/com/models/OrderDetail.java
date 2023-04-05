package demo.models;

import java.util.UUID;

public class OrderDetail extends Table {

    public UUID id;
    public UUID order_id;
    public UUID book_id;
    public int quantity;
    public double price;

    public OrderDetail() {
        super();
        TableName = "order_details";
    }

    public static String GetTableName() {
		return "order_details";
	}
    

    @Override
    protected String Get_Insert_Fields_SQL() {
        return "id,order_id,book_id,quantity,price";
    }

    @Override
    protected String Get_Insert_Values_SQL() {
        return "gen_random_uuid(), " +
                "'" + order_id + "', " +
                "'" + book_id + "', " +
                "" + quantity + ", " +
                "" + price + "";
    }

    @Override
    protected String Get_Update_Values_SQL() {
        return " order_id = '" + order_id + "', " +
                " book_id = '" + book_id + "', " +
                " quantity = '" + quantity + "', " +
                " price = '" + price + "'";
    }

    public void set(UUID id, UUID order_id, UUID book_id, int quantity, double price) {
        this.id = id;
        this.order_id = order_id;
        this.book_id = book_id;
        this.quantity = quantity;
        this.price = price;
    }

}
