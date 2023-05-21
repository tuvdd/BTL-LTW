package repositories.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Order;
import repositories.interfaces.IOrderRepo;
import repositories.utils.SQLInjection;
import repositories.utils.models.LogicalClause;
import repositories.utils.models.LogicalObject;
import repositories.utils.models.LogicalObjectType;

public class OrderRepo extends Repo<Order> implements IOrderRepo {

    @Override
    public Order Get(UUID id) throws SQLException {
        Order response = null;
        try {LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
        LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
        LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
        LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "orders")+ SQLInjection.WHERESQL(logicalClauseArray)
                    + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                response = setObjectFromResultSet(resultSet);
            }

        } catch (Exception ex) {
        } finally {
           CloseConnection();
        }
        return response;
    }

    @Override
    public List<Order> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<Order> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "orders ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<Order>();
            while (resultSet.next()) {
                Order order = setObjectFromResultSet(resultSet);

                response.add(order);
            }

        } catch (Exception ex) {
        } finally {
           CloseConnection();
        }
        return response;
    }

    @Override
    public int Add(Order record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetInsertSQL();
            CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally {
           CloseConnection();
        }
        return response;
    }

    @Override
    public int Update(Order record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetUpdateSQL();
            CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally {
           CloseConnection();
        }
        return response;
    }

    @Override
    public int Delete(Order record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetDeteleSQL();
            CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally {
           CloseConnection();
        }
        return response;
    }

    @Override
    protected Order setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Order response = new Order();
        response.set(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getTimestamp("created_time"),
                resultSet.getInt("status"),
                resultSet.getString("address"),
                resultSet.getString("phonenum"),
                resultSet.getString("buyer_name"));
        return response;
    }

}
