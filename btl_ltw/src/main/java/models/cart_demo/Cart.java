package models.cart_demo;

import models.Book;
import models.Order;
import models.OrderDetail;
import models.User;
import models.dtos.OrderFullDetail;
import repositories.UserRepo;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class Cart extends Book {
    private int quantity;
    private UUID book_id;
    private UUID user_id;
    public Cart() {
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getBook_id() {
        return book_id;
    }

    public void setBook_id(UUID book_id) {
        this.book_id = book_id;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}