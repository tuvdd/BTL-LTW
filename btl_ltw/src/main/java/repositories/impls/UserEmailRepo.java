package repositories.impls;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.UserEmail;
import repositories.interfaces.IUserEmailRepo;
import repositories.utils.SQLInjection;
import repositories.utils.models.LogicalClause;
import repositories.utils.models.LogicalObject;
import repositories.utils.models.LogicalObjectType;

public class UserEmailRepo extends Repo<UserEmail> implements IUserEmailRepo {
    public UserEmailRepo() {
        super();
    }

    @Override
    public UserEmail Get(UUID id) throws SQLException {
        UserEmail response = null;
        try {
            LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
            LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
            LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
            LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "user_emails")+ SQLInjection.WHERESQL(logicalClauseArray)
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
    public List<UserEmail> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<UserEmail> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "user_emails ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<UserEmail>();
            while (resultSet.next()) {
                var user_email = setObjectFromResultSet(resultSet);
                response.add(user_email);
            }

        } catch (Exception ex) {
        } finally{
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Add(UserEmail record) throws SQLException {
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
    public int Update(UserEmail record) throws SQLException {
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
    public int Delete(UserEmail record) throws SQLException {
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
    protected UserEmail setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        var response = new UserEmail();
        response.set(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("email"));
        return response;
    }

}
