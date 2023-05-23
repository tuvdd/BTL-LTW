package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.OrderDetail;

public class OrderDetailRepo extends Repo<OrderDetail> {

    public List<OrderDetail> getByOrderId(UUID orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        CreateConnection();
        try {
            sql = "SELECT * FROM order_details WHERE order_id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, orderId.toString());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = setObjectFromResultSet(resultSet);
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return orderDetails;
    }

    public int add(OrderDetail orderDetail) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "INSERT INTO order_details (id, order_id, book_id, quantity, price) VALUES (?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, orderDetail.id.toString());
            statement.setString(2, orderDetail.order_id.toString());
            statement.setString(3, orderDetail.book_id.toString());
            statement.setInt(4, orderDetail.quantity);
            statement.setDouble(5, orderDetail.price);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public int update(OrderDetail orderDetail) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "UPDATE order_details SET order_id = ?, book_id = ?, quantity = ?, price = ? WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, orderDetail.order_id.toString());
            statement.setString(2, orderDetail.book_id.toString());
            statement.setInt(3, orderDetail.quantity);
            statement.setDouble(4, orderDetail.price);
            statement.setString(5, orderDetail.id.toString());
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public int delete(UUID orderDetailId) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "DELETE FROM order_details WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, orderDetailId.toString());
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public OrderDetail setObjectFromResultSet(ResultSet resultSet) throws SQLException {
       OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(UUID.fromString(resultSet.getString("id")));
        orderDetail.setOrder_id(UUID.fromString(resultSet.getString("order_id")));
        orderDetail.setBook_id(UUID.fromString(resultSet.getString("book_id")));
        orderDetail.setQuantity(resultSet.getInt("quantity"));
        orderDetail.setPrice(resultSet.getDouble("price"));
        return orderDetail;
    }
}