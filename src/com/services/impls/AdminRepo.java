package demo.services.impls;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import demo.models.Admin;
import demo.services.interfaces.IAdminRepo;
import demo.services.utils.SQLInjection;
import demo.services.utils.models.LogicalClause;
import demo.services.utils.models.LogicalObject;
import demo.services.utils.models.LogicalObjectType;

public class AdminRepo extends Repo<Admin> implements IAdminRepo {
    public AdminRepo() {
        super();
    }

    @Override
    public Admin Get(UUID id) throws SQLException {
        Admin response = null;
        try {LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
        LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
        LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
        LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "admins")+ SQLInjection.WHERESQL(logicalClauseArray)
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
    public List<Admin> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<Admin> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "admins ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<Admin>();
            while (resultSet.next()) {
                var admin = setObjectFromResultSet(resultSet);

                response.add(admin);
            }

        } catch (Exception ex) {
        } finally{
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Add(Admin record) throws SQLException {
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
    public int Update(Admin record) throws SQLException {
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
    public int Delete(Admin record) throws SQLException {
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
    protected Admin setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        var response = new Admin();
        response.set(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("phonenum"),
                resultSet.getString("cccd"));
        return response;
    }
}
