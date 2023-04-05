package models;
import java.util.UUID;

public class Tag extends Table {
    public String tag_name;

    public Tag() {
        super();
        this.TableName = "tags";
    }

    public void set(UUID id, String tag_name) {
        this.id = id;
        this.tag_name = tag_name;
    }

    public static String GetTableName() {
		return "tags";
	}
    

    @Override
    protected String Get_Insert_Fields_SQL() {
        return "id, tag_name";
    }

    @Override
    protected String Get_Insert_Values_SQL() {
        return "gen_random_uuid(), '" + tag_name + "'";
    }

    @Override
    protected String Get_Update_Values_SQL() {
        return "tag_name = '" + tag_name + "'";
    }
}