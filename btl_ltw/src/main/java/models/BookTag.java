package models;

import java.util.UUID;

import utils.JsonUtils;

public class BookTag extends Table {
    public UUID book_id;
    public UUID tag_id;

    public BookTag() {
        super();
        TableName = "booktags";
    }

    public void set(UUID book_id, UUID id, UUID tag_id) {
        this.book_id = book_id;
        this.id = id;
        this.tag_id = tag_id;
    }

    public static String GetTableName() {
		return "booktags";
	}
    

    @Override
    protected String Get_Insert_Fields_SQL() {
        return "id, book_id, tag_id";
    }

    @Override
    protected String Get_Insert_Values_SQL() {
        return "gen_random_uuid(), '" + book_id + "', '" + tag_id + "'";
    }

    @Override
    protected String Get_Update_Values_SQL() {
        return "book_id='" + book_id + "', tag_id='" + tag_id + "'";
    }public String To_Json_String() {
		return "{" +
				JsonUtils.PropToJson("id", id, true) +
				JsonUtils.PropToJson("book_id", book_id, true) +
				JsonUtils.PropToJson("tag_id", tag_id, true) +
				"}";
	}
}
