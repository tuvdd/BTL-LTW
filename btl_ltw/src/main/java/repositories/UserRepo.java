package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.User;
import utils.Utils;

public class UserRepo extends Repo<User> {
    public int add(User user) throws Exception {
        if (user.name == null || user.email == null || user.phonenum == null || user.username == null
                || user.password == null) {
            throw new Exception("Thiếu dữ liệu");
        }
        if (Utils.isExistNotNumberChar(user.phonenum)) {
            throw new Exception("Số điện thoại không hợp lệ");
        }

        int res;
        try {
            CreateConnection();
            sql = "INSERT INTO users (id, name, phonenum, email, status, created_time, last_update_time, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, user.id);
            statement.setString(2, user.name);
            statement.setString(3, user.phonenum);
            statement.setString(4, user.email);
            statement.setBoolean(5, user.status);
            statement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            statement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            statement.setString(8, user.username);
            statement.setString(9, user.password);
            res = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }

        return res;
    }

    public int update(User user) throws Exception {
        if (user.name == null || user.email == null || user.phonenum == null) {
            throw new Exception("Thiếu dữ liệu");
        }
        if (Utils.isExistNotNumberChar(user.phonenum)) {
            throw new Exception("Số điện thoại không hợp lệ");
        }

        int res;
        try {
            CreateConnection();
            sql = "UPDATE users SET name=?, phonenum=?, email=?, status=?, last_update_time=?  WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.name);
            statement.setString(2, user.phonenum);
            statement.setString(3, user.email);
            statement.setBoolean(4, user.status);
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            statement.setObject(6, user.id);
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
            sql = "DELETE FROM users WHERE id=?";
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

    public List<User> getAll(int page, int pageSize) throws SQLException {
        List<User> users = null;
        try {
            CreateConnection();
            sql = "SELECT * FROM users";
            if (page > 0 && pageSize > 0) {
                int offset = (page - 1) * pageSize;
                sql += " OFFSET ? LIMIT ? ;";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, offset);
                statement.setInt(2, pageSize);
            } else {
                sql += " ;";
                statement = connection.prepareStatement(sql);
            }
            resultSet = statement.executeQuery();

            users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(setObjectFromResultSet(resultSet));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }

        return users;
    }

    public User getById(UUID id) throws SQLException {
        User user;
        try {
            CreateConnection();
            sql = "SELECT * FROM users WHERE id=?;";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = setObjectFromResultSet(resultSet);

            } else {
                user = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }

        return user;
    }

    public User getByUsernameAndPassword(String username, String password) throws SQLException {
        User user;
        try {
            CreateConnection();
            sql = "SELECT * FROM users WHERE username=? AND password=?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = setObjectFromResultSet(resultSet);

            } else {
                user = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }
        return user;
    }

    public User getUserByPhoneNumber(String phoneNumber) throws Exception {
        User user;
        try {
            CreateConnection();
            sql = "SELECT * FROM users WHERE phonenum=?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, phoneNumber);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = setObjectFromResultSet(resultSet);

            } else {
                user = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CloseConnection();
        }
        return user;
    }

    @Override
    protected User setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.id = UUID.fromString(resultSet.getString("id"));
        user.name = resultSet.getString("name");
        user.email = resultSet.getString("email");
        user.phonenum = resultSet.getString("phonenum");
        user.username = resultSet.getString("username");
        user.password = resultSet.getString("password");
        user.created_time = resultSet.getTimestamp("created_time");
        user.last_update_time = resultSet.getTimestamp("last_update_time");
        user.status = resultSet.getBoolean("status");
        return user;
    }

    public int getCount() {
        int res = 0;
        sql = "SELECT COUNT(*) FROM users ;";
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