package demo.services.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import demo.models.User;
import demo.services.interfaces.IUserRepo;
import demo.services.utils.SQLInjection;
import demo.services.utils.models.LogicalClause;
import demo.services.utils.models.LogicalObject;
import demo.services.utils.models.LogicalObjectType;

public class UserRepo extends Repo<User> implements IUserRepo {
    public UserRepo() {
        super();
    }

    @Override
    public User Get(UUID id) throws SQLException {
        User response = null;
        try {
            LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
            LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
            LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
            LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "users") + SQLInjection.WHERESQL(logicalClauseArray)
                    + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                response = setObjectFromResultSet(resultSet);
            }

        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public List<User> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<User> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "users") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<User>();
            while (resultSet.next()) {
                var user = setObjectFromResultSet(resultSet);

                response.add(user);
            }

        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Add(User record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetInsertSQL();
            CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Update(User record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetUpdateSQL();
            CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Delete(User record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetDeteleSQL();
            CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    protected User setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        var response = new User();
        response.set(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name"),
                resultSet.getString("phonenum"),
                resultSet.getString("address"),
                UUID.fromString(resultSet.getString("avatar_id")),
                resultSet.getBoolean("status"),
                resultSet.getTimestamp("created_time"),
                resultSet.getTimestamp("last_update_time"));
        return response;
    }
}
