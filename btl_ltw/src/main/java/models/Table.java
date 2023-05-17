package models;
import java.util.UUID;

public abstract class Table {
    public String TableName;
    public UUID id;

    public String GetInsertSQL() {
        return "INSERT INTO " + TableName + " (" + Get_Insert_Fields_SQL() + ")" + " "
                + "VALUES (" + Get_Insert_Values_SQL() + ");";
    }

    public String GetUpdateSQL() {
        return "UPDATE " + TableName + " "
                + "SET " + Get_Update_Values_SQL() + " "
                + "WHERE id = '" + id + "';";
    };

    public String GetDeteleSQL() {
        return "DELETE FROM " + TableName + " WHERE id = '" + id + "'";
    }

    
    protected abstract String Get_Insert_Fields_SQL();

    protected abstract String Get_Insert_Values_SQL();

    protected abstract String Get_Update_Values_SQL();

    public abstract String To_Json_String();

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
