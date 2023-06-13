package repositories;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import models.Order;
import models.OrderDetail;
import models.dtos.AdminOrderPreview;
import models.dtos.OrderFullDetail;

public class OrderRepo extends Repo<Order> {
    private OrderDetailRepo odr;

    public OrderRepo() {
        super();
        odr = new OrderDetailRepo();
    }

    public Order GetByID(UUID id) {
        Order res = null;
        try {
            CreateConnection();
            sql = "SELECT * FROM orders WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                res = setObjectFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return res;
    }

    public List<AdminOrderPreview> getAllAdminOrderView(int pageIndex, int pageSize) {
        List<AdminOrderPreview> res = new ArrayList<>();
        sql = "       SELECT o.id AS id, "
                + "          o.created_time AS created_time, "
                + "          o.status AS status, "
                + "          o.address AS address, "
                + "          o.phonenum AS phonenum, "
                + "          o.buyer_name AS buyer_name, "
                + "          SUM(od.quantity) AS totalProduct, "
                + "          SUM(od.price) AS totalPrice "
                + "     FROM orders o "
                + "LEFT JOIN order_details od ON o.id = od.order_id "
                + " GROUP BY o.id, "
                + "          o.created_time, "
                + "          o.status, "
                + "          o.address, "
                + "          o.phonenum, "
                + "          o.buyer_name "
                + " ORDER BY o.created_time DESC ";
        try {
            CreateConnection();
            if (pageIndex == -1 || pageSize == -1) {
                sql += " ;";
                statement = connection.prepareStatement(sql);
            } else {
                sql += "LIMIT ? OFFSET ? ;";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, pageSize);
                statement.setInt(2, (pageIndex - 1) * pageSize);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AdminOrderPreview adminOrderPreview = new AdminOrderPreview();
                adminOrderPreview.id = UUID.fromString(resultSet.getString("id"));
                adminOrderPreview.created_time = resultSet.getTimestamp("created_time");
                adminOrderPreview.status = resultSet.getInt("status");
                adminOrderPreview.address = resultSet.getString("address");
                adminOrderPreview.phonenum = resultSet.getString("phonenum");
                adminOrderPreview.buyer_name = resultSet.getString("buyer_name");
                adminOrderPreview.totalProduct = resultSet.getInt("totalProduct");
                adminOrderPreview.totalPrice = resultSet.getInt("totalPrice");
                res.add(adminOrderPreview);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return res;
    }

    public List<Order> getAll(int pageIndex, int pageSize) {
        List<Order> orders = new ArrayList<>();
        CreateConnection();
        try {
            if (pageIndex == -1 || pageSize == -1) {
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
            sql = "     SELECT o.id AS order_id, " +
                    "          o.created_time, " +
                    "          o.address,  " +
                    "          o.phonenum, " +
                    "          o.buyer_name, " +
                    "          od.id AS detail_id, " +
                    "          od.book_id, " +
                    "          od.quantity, " +
                    "          b.\"name\", " +
                    "          od.quantity, " +
                    "          od.price, " +
                    "          o.status " +
                    "     FROM orders o  " +
                    "LEFT JOIN order_details od  " +
                    "       ON o.id = od.order_id  " +
                    "LEFT JOIN books b  " +
                    "       ON od.book_id = b.id  " +
                    "    WHERE o.id = ?; ";

            statement = connection.prepareStatement(sql);
            statement.setObject(1, orderId);
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
                orderDetail.setBook_name(resultSet.getString("name"));
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
            statement.setObject(1, UUID.randomUUID());
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

    public int add_2 (OrderFullDetail orderFullDetail) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "INSERT INTO orders (id, user_id, created_time, status, address, phonenum, buyer_name) VALUES (?, ?, ?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, orderFullDetail.id);
            statement.setObject(2, orderFullDetail.getUser_id());
            statement.setTimestamp(3, orderFullDetail.created_time);
            statement.setInt(4, orderFullDetail.status);
            statement.setString(5, orderFullDetail.address);
            statement.setString(6, orderFullDetail.phonenum);
            statement.setString(7, orderFullDetail.buyer_name);
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
            statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            statement.setInt(2, orderFullDetail.status);
            statement.setString(3, orderFullDetail.address);
            statement.setString(4, orderFullDetail.phonenum);
            statement.setString(5, orderFullDetail.buyer_name);
            statement.setObject(6, orderFullDetail.id);
            rowsAffected = statement.executeUpdate();
            for (OrderDetail orderDetail : orderFullDetail.getOrderDetails()) {
                if (orderDetail.getId() == null) {
                    orderDetail.id = UUID.randomUUID();
                    rowsAffected += odr.add(orderDetail);
                } else {
                    rowsAffected += odr.update(orderDetail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public int updateStatus(UUID id, int status) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "UPDATE orders SET status = ? WHERE id = ?;";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            statement.setObject(2, id);
            rowsAffected += statement.executeUpdate();
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
            statement.setObject(1, orderId);
            rowsAffected += statement.executeUpdate();
            sql = "DELETE FROM orders WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, orderId);
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

    public int getCount() {
        int res = 0;
        sql = "SELECT COUNT(*) FROM orders ;";
        try {
            CreateConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }

        return res;
    }

    public int getCountOrderByStatus(int status) {
        int res = -1;
        sql = "SELECT COUNT(*) AS count FROM orders WHERE status = ?";
        try {
            CreateConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            resultSet = statement.executeQuery();
            if (resultSet.next())
                res = resultSet.getInt("count");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return res;
    }

    public BigDecimal tinhTongDoanhThuTheoThang(Timestamp current) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Timestamp from = new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.MONTH, 1);
        Timestamp to = new Timestamp(cal.getTimeInMillis());

        return tinhDoanhThuTheoThoiGian(from, to);
    }

    public BigDecimal tinhTongDoanhThuTheoNam(Timestamp current) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Timestamp from = new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.YEAR, 1);
        Timestamp to = new Timestamp(cal.getTimeInMillis());

        return tinhDoanhThuTheoThoiGian(from, to);
    }

    public BigDecimal tinhDoanhThuTheoThoiGian(Timestamp from, Timestamp to) {
        BigDecimal res = BigDecimal.valueOf(-1);
        sql = "SELECT SUM(od.quantity * od.price) AS tdt FROM orders o, order_details od WHERE o.id = od.order_id AND o.created_time BETWEEN ? AND ?;";
        try {
            CreateConnection();
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, from);
            statement.setTimestamp(2, to);
            resultSet = statement.executeQuery();
            if (resultSet.next())
                res = BigDecimal.valueOf(resultSet.getDouble("tdt"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return res;
    }
}