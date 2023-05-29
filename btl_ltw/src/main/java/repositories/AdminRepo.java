package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Admin;

public class AdminRepo extends Repo<Admin> {
    public int add(Admin admin) throws SQLException {
        int res;
        try {
            CreateConnection();
            sql = "INSERT INTO admins (id, name, email, phonenum, cccd, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, admin.getId());
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
            sql = "UPDATE admins SET name=?, email=?, phonenum=?, cccd=? WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, admin.name);
            statement.setString(2, admin.email);
            statement.setString(3, admin.phonenum);
            statement.setString(4, admin.cccd);
            statement.setObject(5, admin.id);
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
            sql = "DELETE FROM admins WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
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
            CreateConnection();
            sql = "SELECT * FROM admins";
            if (page == -1 || pageSize == -1) {
                int offset = (page - 1) * pageSize;
                sql += " OFFSET ? LIMIT ? ;";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, offset);
                statement.setInt(2, pageSize);
            } else {
                sql += ";";
                statement = connection.prepareStatement(sql);
            }
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
            sql = "SELECT * FROM admins WHERE id=;";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
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

    public int getCount() {
        int res = 0;
        sql = "SELECT COUNT(*) FROM admins ;";
        try {
            CreateConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }

        return res;
    }
}