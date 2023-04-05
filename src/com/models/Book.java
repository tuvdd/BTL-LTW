package demo.models;

import java.sql.Timestamp;
import java.util.UUID;

public class Book extends Table {
    public String name;
    public String author;
    public int release_year;
    public UUID category_id;
    public double price;
    public double promote_price;
    public int quantity;
    public String description;
    public String sub_description;
    public int status;
    public Timestamp create_time;
    public UUID create_by;
    public Timestamp last_update_time;
    public UUID last_update_by;

    public Book() {
        super();
        TableName = "books";
    }

    public static String GetTableName() {
		return "books";
	}
    

    public void set(UUID id, String name, String author, int release_year, UUID category_id, double price,
            double promote_price,
            int quantity, String description, String sub_description, int status, Timestamp create_time, UUID create_by,
            Timestamp last_update_time, UUID last_update_by) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.release_year = release_year;
        this.category_id = category_id;
        this.price = price;
        this.promote_price = promote_price;
        this.quantity = quantity;
        this.description = description;
        this.sub_description = sub_description;
        this.status = status;
        this.create_time = create_time;
        this.create_by = create_by;
        this.last_update_time = last_update_time;
        this.last_update_by = last_update_by;
    }

    @Override
    protected String Get_Insert_Fields_SQL() {
        return "id, name, author, release_year, category_id, price, promote_price, quantity, description, sub_description, status, create_time, create_by, last_update_time, last_update_by";
    }

    @Override
    protected String Get_Insert_Values_SQL() {
        return "gen_random_uuid(), '" + name + "', '" + author + "', " + release_year + ", '" + category_id + "', "
                + price + ", " + promote_price + ", " + quantity + ", '" + description + "', '" + sub_description
                + "', " + status + ", '" + create_time + "', '" + create_by + "', '" + last_update_time + "', '"
                + last_update_by + "'";
    }

    @Override
    protected String Get_Update_Values_SQL() {
        return "id = '" + id + "', name = '" + name + "', author = '" + author + "', release_year = " + release_year
                + ", category_id = '" + category_id + "', price = " + price + ", promote_price = " + promote_price
                + ", quantity = " + quantity + ", description = '" + description + "', sub_description = '"
                + sub_description + "', status = " + status + ", create_time = '" + create_time + "', create_by = '"
                + create_by + "', last_update_time = '" + last_update_time + "', last_update_by = '" + last_update_by
                + "'";
    }
}
