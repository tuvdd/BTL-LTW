package repositories.cart_demo;


import models.Book;
import models.cart_demo.Cart;
import repositories.BookRepo;

import java.sql.*;
import java.util.*;


public class CartDao {
    private Connection con;

    private String query;
    private PreparedStatement pst;
    private ResultSet rs;


    public CartDao(Connection con) {
        super();
        this.con = con;
    }

    public double getTotalCartPrice(List<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart c : cartList) {
                    query = "select promote_price from books where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setObject(1, c.getBook_id());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        sum+=rs.getDouble("promote_price")*c.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }


    public List<Cart> getCartProducts(String userID) {
        List<Cart> book = new ArrayList<>();
        try {
            query = "select cart_list.id, cart_list.user_id, cart_list.book_id, cart_list.quantity, books.name, books.category_id, books.promote_price, books.image from cart_list, books where cart_list.user_id=? AND cart_list.book_id = books.id";
            pst = this.con.prepareStatement(query);
            pst.setObject(1, UUID.fromString(userID));
            rs = pst.executeQuery();
            while (rs.next()) {
                Cart row = new Cart();
                row.setId(UUID.fromString(rs.getString("id")));
                row.setUser_id(UUID.fromString(rs.getString("user_id")));
                row.setBook_id(UUID.fromString(rs.getString("book_id")));
                row.setQuantity(rs.getInt("quantity"));
                row.setName(rs.getString("name"));
                row.setCategory_id(UUID.fromString(rs.getString("category_id")));
                row.setPromote_price(rs.getDouble("promote_price"));
                row.setImage(rs.getBytes("image"));
                book.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return book;
    }

    public int saveToCart(String id, String bookID, String userID, int quantity) throws Exception {
        int rowsAffected = 0;
        Object userId;
        if (userID == ""){
            userId = null;
        } else {
            userId = UUID.fromString(userID);
        }
        try {

            query = "INSERT INTO cart_list (id, user_id, book_id, quantity) VALUES (?, ?, ?, ?);";
            pst = this.con.prepareStatement(query);
            pst.setObject(1, UUID.fromString(id));
            pst.setObject(2, userId);
            pst.setObject(3, UUID.fromString(bookID));
            pst.setInt(4, quantity);
            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public int updateCart(List<Cart> cartList) {
        int rowsAffected = 0;
        try {
            for(Cart c : cartList){
                query = "UPDATE cart_list SET user_id = ?, book_id = ?, quantity = ? WHERE id = ?;";
                pst = this.con.prepareStatement(query);
                pst.setObject(1, c.getUser_id());
                pst.setObject(2, c.getBook_id());
                pst.setInt(3, c.getQuantity());
                pst.setObject(4, c.getId());

                rowsAffected = pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public int deleteCart(Cart cart) {
        int rowsAffected = 0;
        try {
            query = "DELETE FROM cart_list WHERE id = ?;";
            pst = this.con.prepareStatement(query);
            pst.setObject(1, cart.getId());
            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }



}