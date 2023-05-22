package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Admin;
import models.dtos.AdminFullDetail;

public class AdminRepo extends Repo<Admin> {
    public int add(Admin admin) throws SQLException {
        int res;
        try {
            CreateConnection();
            sql = "INSERT INTO admin (id, name, email, phonenum, cccd, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, admin.id.toString());
            statement.setString(2, admin.name);
            statement.setString(3, admin.email);
            statement.setString(4, admin.phonenum);
            statement.setString(5, admin.cccd);
            statement.setString(6, admin.username);
            statement.setString(7, admin.password);
            res = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }

        return res;
    }

    public int update(Admin admin) throws SQLException {
        int res;
        try {
            CreateConnection();
            sql = "UPDATE admin SET name=?, email=?, phonenum=?, cccd=?, username=?, password=? WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, admin.name);
            statement.setString(2, admin.email);
            statement.setString(3, admin.phonenum);
            statement.setString(4, admin.cccd);
            statement.setString(5, admin.username);
            statement.setString(6, admin.password);
            statement.setString(7, admin.id.toString());
            res = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }

        return res;
    }

    public int delete(UUID id) throws SQLException {
        int res;
        try {
            CreateConnection();
            sql = "DELETE FROM admin WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id.toString());
            res = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }

        return res;
    }

    public List<Admin> getAll(int page, int pageSize) throws SQLException {
        List<Admin> admins = null;
        try {
            int offset = (page - 1) * pageSize;
            CreateConnection();
            sql = "SELECT * FROM admin LIMIT ?, ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, pageSize);
            resultSet = statement.executeQuery();

            admins = new ArrayList<>();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.id = UUID.fromString(resultSet.getString("id"));
                admin.name = resultSet.getString("name");
                admin.email = resultSet.getString("email");
                admin.phonenum = resultSet.getString("phonenum");
                admin.cccd = resultSet.getString("cccd");
                admin.username = resultSet.getString("username");
                admin.password = resultSet.getString("password");
                admins.add(admin);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }

        return admins;
    }

    public Admin getById(UUID id) throws SQLException {
        Admin admin;
        try {
            CreateConnection();
            sql = "SELECT * FROM admin WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id.toString());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                admin = new Admin();
                admin.id = UUID.fromString(resultSet.getString("id"));
                admin.name = resultSet.getString("name");
                admin.email = resultSet.getString("email");
                admin.phonenum = resultSet.getString("phonenum");
                admin.cccd = resultSet.getString("cccd");
                admin.username = resultSet.getString("username");
                admin.password = resultSet.getString("password");

            } else {
                admin = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }

        return admin;
    }

    public Admin getByUsernameAndPassword(String username, String password) throws SQLException {
        Admin admin;
        try {
            CreateConnection();
            sql = "SELECT * FROM admins WHERE username=? AND password=?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                admin = new Admin();
                admin.id = UUID.fromString(resultSet.getString("id"));
                admin.name = resultSet.getString("name");
                admin.email = resultSet.getString("email");
                admin.phonenum = resultSet.getString("phonenum");
                admin.cccd = resultSet.getString("cccd");
                admin.username = resultSet.getString("username");
                admin.password = resultSet.getString("password");
            } else {
                admin = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }
        return admin;
    }

    @Override
    protected Admin setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.id = UUID.fromString(resultSet.getString("id"));
        admin.name = resultSet.getString("name");
        admin.email = resultSet.getString("email");
        admin.phonenum = resultSet.getString("phonenum");
        admin.cccd = resultSet.getString("cccd");
        return admin;
    }

    public List<AdminFullDetail> GetsFullDetail(int page, int pageSize) throws SQLException {
        int offset = (page - 1) * pageSize;
        sql = "SELECT a.id id, a.name name, a.email email, a.phonenum phonenum, a.cccd cccd, al.username username, al.password password "
                + "FROM admins a, admin_logins al "
                + "LIMIT ?, ? ;";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, offset);
        statement.setInt(2, pageSize);
        ResultSet resultSet = statement.executeQuery();

        List<AdminFullDetail> adminFullDetails = new ArrayList<>();
        while (resultSet.next()) {
            AdminFullDetail afd = new AdminFullDetail();
            afd.id = UUID.fromString(resultSet.getString("id"));
            afd.name = resultSet.getString("name");
            afd.email = resultSet.getString("email");
            afd.phonenum = resultSet.getString("phonenum");
            afd.cccd = resultSet.getString("cccd");
            afd.username = resultSet.getString("username");
            afd.password = resultSet.getString("password");
            adminFullDetails.add(afd);
        }
        return adminFullDetails;
    }
}