package demo.services.utils.models;

public class InsertFieldObject {
    public String fieldName;
    public String value;
    public boolean valueIsString;

    public InsertFieldObject() {
        super();
    }

    public InsertFieldObject(String fieldName, String value, boolean valueIsString) {
        this.fieldName = fieldName;
        this.value = value;
        this.valueIsString = valueIsString;
    }

    public String getValue() {
        if (valueIsString)
            return "'" + value + "'";
        else
            return value;
    }
}
