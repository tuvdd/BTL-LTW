package models.cart_demo;


public class Item {
    private model.Product product;
    private int quantity;
    private double price;

    public Item() {
    }

    public Item(model.Product product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public model.Product getProduct() {
        return product;
    }

    public void setProduct(model.Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
