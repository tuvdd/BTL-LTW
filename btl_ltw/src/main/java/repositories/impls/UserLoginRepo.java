package repositories.impls;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.UserLogin;
import repositories.interfaces.IUserLoginRepo;
import repositories.utils.SQLInjection;
import repositories.utils.models.LogicalClause;
import repositories.utils.models.LogicalObject;
import repositories.utils.models.LogicalObjectType;

public class UserLoginRepo extends Repo<UserLogin> implements IUserLoginRepo {
    public UserLoginRepo() {
        super();
    }
    @Override
    public UserLogin Get(UUID id) throws SQLException {
        UserLogin response = null;
        try {
            LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
            LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
            LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
            LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "user_logins")+ SQLInjection.WHERESQL(logicalClauseArray)
                    + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                response = setObjectFromResultSet(resultSet);
            }

        } catch (Exception ex) {
        } finally{
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public List<UserLogin> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<UserLogin> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "user_logins ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<UserLogin>();
            while (resultSet.next()) {
                var user_login = setObjectFromResultSet(resultSet);

                response.add(user_login);
            }

        } catch (Exception ex) {
        } finally{
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Add(UserLogin record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetInsertSQL(); CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally{
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Update(UserLogin record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetUpdateSQL(); CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally{
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Delete(UserLogin record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetDeteleSQL(); CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally{
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    protected UserLogin setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        var response = new UserLogin();
        response.set(UUID.fromString(
                resultSet.getString("id")),
                resultSet.getString("username"),
                resultSet.getString("password"));
        return response;
    }
}
