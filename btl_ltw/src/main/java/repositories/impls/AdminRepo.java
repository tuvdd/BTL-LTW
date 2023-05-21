package repositories.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Admin;
import models.AdminLogin;
import models.dtos.AdminFullDetail;
import repositories.interfaces.IAdminRepo;
import repositories.utils.SQLInjection;
import repositories.utils.models.LogicalClause;
import repositories.utils.models.LogicalObject;
import repositories.utils.models.LogicalObjectType;

public class AdminRepo extends Repo<Admin> implements IAdminRepo {
    public AdminRepo() {
    }

    @Override
    public Admin Get(UUID id) throws SQLException {
        Admin response = null;
        try {
            LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
            LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
            LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
            LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "admins") + SQLInjection.WHERESQL(logicalClauseArray)
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
    public List<Admin> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<Admin> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "admins ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<Admin>();
            while (resultSet.next()) {
                Admin admin = setObjectFromResultSet(resultSet);

                response.add(admin);
            }

        } catch (Exception ex) {
        } finally {
           CloseConnection();
        }
        return response;
    }

    @Override
    public int Add(Admin record) throws SQLException {
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
    public int Update(Admin record) throws SQLException {
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
    public int Delete(Admin record) throws SQLException {
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
    protected Admin setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Admin response = new Admin();
        response.set(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("phonenum"),
                resultSet.getString("cccd"));
        return response;
    }

    @Override
    public int Register(Admin admin, AdminLogin adminLogin) throws SQLException {
        int res = Add(admin);
        if (res == 1) {
            List<Admin> la = Gets("WHERE name = '" + admin.name + "' AND email = '" + admin.email + "' AND phonenum = '"
                    + admin.phonenum + "' AND cccd = '" + admin.cccd + "' ", "");
            adminLogin.id = la.get(la.size() - 1).id;
            res += new AdminLoginRepo().Add(adminLogin);
        }

        return res;
    }

    @Override
    public List<AdminFullDetail> GetsFullDetail(String PaginSQL) throws SQLException {
        List<AdminFullDetail> response = null;
        try {
            CreateConnection();
            String sql = ""
                    + "SELECT a.id id, a.name name, a.email email, a.phonenum phonenum, a.cccd cccd, al.username username, al.password password "
                    + "FROM admins a, admin_logins al "
                    + "WHERE a.id = al.id "
                    + PaginSQL
                    + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<AdminFullDetail>();
            while (resultSet.next()) {
                AdminFullDetail adminFullDetail = new AdminFullDetail();

                adminFullDetail.setId(UUID.fromString(resultSet.getString("id")));
                adminFullDetail.setEmail(resultSet.getString("Email"));
                adminFullDetail.setPhonenum(resultSet.getString("Phonenum"));
                adminFullDetail.setPassword(resultSet.getString("Password"));
                adminFullDetail.setUsername(resultSet.getString("Username"));
                adminFullDetail.setCccd(resultSet.getString("Cccd"));
                adminFullDetail.setName(resultSet.getString("Name"));

                response.add(adminFullDetail);
            }

        } catch (Exception ex) {
        } finally {
           CloseConnection();
        }
        return response;
    }
}