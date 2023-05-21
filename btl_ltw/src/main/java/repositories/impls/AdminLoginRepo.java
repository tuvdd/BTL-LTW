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
           CloseConnection();
        }
        return response;
    }

    @Override
    public AdminLogin Get(String username, String password) throws SQLException {
        String whereSQL = "WHERE username = '" + username + "' AND password = '" + password + "'";
        List<AdminLogin> list = Gets(whereSQL, "");
        if (list != null && list.size() > 0 && list.get(0) != null) {
            return list.get(0);
        } else
            return null;
    }

    @Override
    public List<AdminLogin> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        if (PaginSQL == null)
            PaginSQL = "";
        List<AdminLogin> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "admin_logins ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<AdminLogin>();
            while (resultSet.next()) {
                AdminLogin admin_login = setObjectFromResultSet(resultSet);

                response.add(admin_login);
            }

        } catch (Exception ex) {
        } finally {
           CloseConnection();
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
           CloseConnection();
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
           CloseConnection();
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
           CloseConnection();
        }
        return response;
    }

    @Override
    protected AdminLogin setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        AdminLogin response = new AdminLogin();
        response.set(UUID.fromString(
                resultSet.getString("id")),
                resultSet.getString("username"),
                resultSet.getString("password"));
        return response;
    }

    @Override
    public UUID GetIdByUsernamePassword(String username, String password) throws SQLException {
        UUID id = null;
        try {
            CreateConnection();
            String sql = "SELECT id FROM admin_logins WHERE username ='"+username+"' AND password = '"+password+"';";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = UUID.fromString(resultSet.getString("id"));
            }

        } catch (Exception ex) {
        } finally {
           CloseConnection();
        }
        return id;
    }
}
