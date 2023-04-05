package repositories.utils.models;

public enum OrderType {
    ASC("ASC"), DESC("DESC");

    public final String label;

    private OrderType(String label) {
        this.label = label;
    }
}
