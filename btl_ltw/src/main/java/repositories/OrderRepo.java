package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Order;
import models.OrderDetail;
import models.dtos.OrderFullDetail;

public class OrderRepo extends Repo<Order> {
    private OrderDetailRepo odr;
    public OrderRepo() {
        super();
        odr = new OrderDetailRepo();
    }

    public List<Order> getAll(int pageIndex, int pageSize) {
        List<Order> orders = new ArrayList<>();
        CreateConnection();
        try {
            if (pageIndex == -1 && pageSize == -1) {
                sql = "SELECT * FROM orders;";
                statement = connection.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM orders LIMIT ? OFFSET ?;";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, pageSize);
                statement.setInt(2, (pageIndex - 1) * pageSize);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = setObjectFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return orders;
    }

    public OrderFullDetail getOrderFullDetailById(UUID orderId) {
        OrderFullDetail orderFullDetail = null;
        CreateConnection();
        try {
            sql = "SELECT o.id AS order_id, o.created_time, o.status, o.address, o.phonenum, o.buyer_name, od.id AS detail_id, od.book_id, od.quantity, od.price FROM orders o JOIN order_details od ON o.id = od.order_id WHERE o.id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, orderId.toString());
            resultSet = statement.executeQuery();
            List<OrderDetail> orderDetails = new ArrayList<>();
            while (resultSet.next()) {
                if (orderFullDetail == null) {
                    orderFullDetail = new OrderFullDetail();
                    orderFullDetail.setId(UUID.fromString(resultSet.getString("order_id")));
                    orderFullDetail.setCreated_time(resultSet.getTimestamp("created_time"));
                    orderFullDetail.setStatus(resultSet.getInt("status"));
                    orderFullDetail.setAddress(resultSet.getString("address"));
                    orderFullDetail.setPhonenum(resultSet.getString("phonenum"));
                    orderFullDetail.setBuyer_name(resultSet.getString("buyer_name"));
                }
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(UUID.fromString(resultSet.getString("detail_id")));
                orderDetail.setOrder_id(UUID.fromString(resultSet.getString("order_id")));
                orderDetail.setBook_id(UUID.fromString(resultSet.getString("book_id")));
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setPrice(resultSet.getDouble("price"));
                orderDetails.add(orderDetail);
            }
            if (orderFullDetail != null) {
                orderFullDetail.setOrderDetails(orderDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return orderFullDetail;
    }

    public int add(OrderFullDetail orderFullDetail) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "INSERT INTO orders (id, created_time, status, address, phonenum, buyer_name) VALUES (?, ?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, orderFullDetail.id.toString());
            statement.setTimestamp(2, orderFullDetail.created_time);
            statement.setInt(3, orderFullDetail.status);
            statement.setString(4, orderFullDetail.address);
            statement.setString(5, orderFullDetail.phonenum);
            statement.setString(6, orderFullDetail.buyer_name);
            rowsAffected = statement.executeUpdate();
            for (OrderDetail orderDetail : orderFullDetail.getOrderDetails()) {
                rowsAffected += odr.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public int update(OrderFullDetail orderFullDetail) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "UPDATE orders SET created_time = ?, status = ?, address = ?, phonenum = ?, buyer_name = ? WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, orderFullDetail.created_time);
            statement.setInt(2, orderFullDetail.status);
            statement.setString(3, orderFullDetail.address);
            statement.setString(4, orderFullDetail.phonenum);
            statement.setString(5, orderFullDetail.buyer_name);
            statement.setString(6, orderFullDetail.id.toString());
            rowsAffected = statement.executeUpdate();
            for (OrderDetail orderDetail : orderFullDetail.getOrderDetails()) {
                if (orderDetail.getId() == null) {
                    orderDetail.id =UUID.randomUUID();
                    rowsAffected+=odr.add(orderDetail);
                } else {
                    rowsAffected+=odr.update(orderDetail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public int delete(UUID orderId) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "DELETE FROM order_details WHERE order_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, orderId.toString());
            rowsAffected += statement.executeUpdate();
            sql = "DELETE FROM orders WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, orderId.toString());
            rowsAffected += statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public Order setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(UUID.fromString(resultSet.getString("id")));
        order.setCreated_time(resultSet.getTimestamp("created_time"));
        order.setStatus(resultSet.getInt("status"));
        order.setAddress(resultSet.getString("address"));
        order.setPhonenum(resultSet.getString("phonenum"));
        order.setBuyer_name(resultSet.getString("buyer_name"));
        return order;
    }
}