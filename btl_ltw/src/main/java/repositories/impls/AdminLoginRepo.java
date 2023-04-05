package repositories.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.AdminLogin;
import repositories.interfaces.IAdminLoginRepo;
import repositories.utils.SQLInjection;
import repositories.utils.models.LogicalClause;
import repositories.utils.models.LogicalObject;
import repositories.utils.models.LogicalObjectType;

public class AdminLoginRepo extends Repo<AdminLogin> implements IAdminLoginRepo {

    @Override
    public AdminLogin Get(UUID id) throws SQLException {
        AdminLogin response = null;
        try {
            LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
            LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
            LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
            LogicalClause[] logicalClauseArray = { logicalClause };

            String sql = SQLInjection.SELECTSQL(null, "admin_logins") + SQLInjection.WHERESQL(logicalClauseArray)
                    + " ;";

            CreateConnection();
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                response = setObjectFromResultSet(resultSet);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public List<AdminLogin> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<AdminLogin> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "admin_logins ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<AdminLogin>();
            while (resultSet.next()) {
                var admin_login = setObjectFromResultSet(resultSet);

                response.add(admin_login);
            }

        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Add(AdminLogin record) throws SQLException {
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
    public int Update(AdminLogin record) throws SQLException {
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
    public int Delete(AdminLogin record) throws SQLException {
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
    protected AdminLogin setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        var response = new AdminLogin();
        response.set(UUID.fromString(
                resultSet.getString("id")),
                resultSet.getString("username"),
                resultSet.getString("password"));
        return response;
    }
}
