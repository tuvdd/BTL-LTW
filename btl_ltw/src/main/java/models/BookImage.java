package models;
import java.util.UUID;

import utils.JsonUtils;

public class BookImage extends Table {
    public String value;
    public UUID book_id;

    public BookImage() {
        super();
        this.TableName = "book_images";
    }

    public void set(UUID id, String value, UUID book_id) {
        this.id = id;
        this.value = value;
        this.book_id = book_id;
    }

    public static String GetTableName() {
        return "book_images";
    }

    @Override
    public String Get_Insert_Fields_SQL() {
        return "id, value, book_id";
    }

    @Override
    public String Get_Insert_Values_SQL() {
        return "gen_random_uuid(), '" + value + "', '" + book_id + "'";
    }

    @Override
    public String Get_Update_Values_SQL() {
        return "value = '" + value + "', book_id = '" + book_id + "'";
    }public String To_Json_String() {
		return "{" +
				JsonUtils.PropToJson("id", id, true) +
				JsonUtils.PropToJson("value", value, true) +
				JsonUtils.PropToJson("book_id", book_id, true) +
				"}";
	}
}