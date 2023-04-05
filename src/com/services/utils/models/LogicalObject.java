package demo.services.utils.models;

public class LogicalObject {
    public String name;
    public LogicalObjectType type;

    public LogicalObject(String name, LogicalObjectType type) {
        super();
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        if (type == LogicalObjectType.STRING) {
            return "'" + name + "'";
        } else
            return name;
    }
}
