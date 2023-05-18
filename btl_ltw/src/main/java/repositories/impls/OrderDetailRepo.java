package repositories.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.OrderDetail;
import repositories.interfaces.IOrderDetailRepo;
import repositories.utils.SQLInjection;
import repositories.utils.models.LogicalClause;
import repositories.utils.models.LogicalObject;
import repositories.utils.models.LogicalObjectType;

public class OrderDetailRepo extends Repo<OrderDetail> implements IOrderDetailRepo {

    @Override
    public OrderDetail Get(UUID id) throws SQLException {
        OrderDetail response = null;
        try {LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
        LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
        LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
        LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();

            String sql = SQLInjection.SELECTSQL(null, "order_details")+ SQLInjection.WHERESQL(logicalClauseArray)
                    + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                response = setObjectFromResultSet(resultSet);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
           CloseConnection();
        }
        return response;
    }

    @Override
    public List<OrderDetail> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<OrderDetail> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "order_details ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<OrderDetail>();
            while (resultSet.next()) {
                OrderDetail order_detail = setObjectFromResultSet(resultSet);

                response.add(order_detail);
            }

        } catch (Exception ex) {
        } finally {
           CloseConnection();
        }
        return response;
    }

    @Override
    public int Add(OrderDetail record) throws SQLException {
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
    public int Update(OrderDetail record) throws SQLException {
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
    public int Delete(OrderDetail record) throws SQLException {
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
    protected OrderDetail setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        OrderDetail response = new OrderDetail();
        response.set(
                UUID.fromString(resultSet.getString("id")),
                UUID.fromString(resultSet.getString("order_id")),
                UUID.fromString(resultSet.getString("book_id")),
                resultSet.getInt("quantity"),
                resultSet.getDouble("price"));
        return response;
    }

}
