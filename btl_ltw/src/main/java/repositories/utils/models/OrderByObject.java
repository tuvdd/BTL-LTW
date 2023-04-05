package repositories.utils.models;

public class OrderByObject {
    public String FieldName;
    public OrderType type;

    public OrderByObject(String FieldName, OrderType type) {
        super();
        this.FieldName = FieldName;
        this.type = type;
    }
}
