package demo.services.utils.models;

public class LogicalClause {
    public LogicalObject obj1;
    public String sign;
    public LogicalObject obj2;

    public LogicalClause(LogicalObject obj1, String sign, LogicalObject obj2) {
        this.obj1 = obj1;
        this.sign = sign;
        this.obj2 = obj2;
    }
}
