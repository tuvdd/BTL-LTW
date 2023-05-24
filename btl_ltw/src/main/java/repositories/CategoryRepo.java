package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Category;

public class CategoryRepo extends Repo<Category> {

    public List<Category> getAll(int pageIndex, int pageSize) {
        List<Category> categories = new ArrayList<>();
        CreateConnection();
        try {
            if (pageIndex == -1 || pageSize == -1) {
                sql = "SELECT * FROM categories ORDER BY status DESC;";
                statement = connection.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM categories ORDER BY status DESC LIMIT ? OFFSET ? ;";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, pageSize);
                statement.setInt(2, (pageIndex - 1) * pageSize);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = setObjectFromResultSet(resultSet);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return categories;
    }

    public List<Category> getAll(int pageIndex, int pageSize, boolean status) {
        List<Category> categories = new ArrayList<>();
        CreateConnection();
        try {
            if (pageIndex == -1 || pageSize == -1) {
                sql = "SELECT * FROM categories WHERE status = ?";
                statement = connection.prepareStatement(sql);
                statement.setBoolean(1, status);
            } else {
                sql = "SELECT * FROM categories WHERE status = ? LIMIT ? OFFSET ?";
                statement = connection.prepareStatement(sql);
                statement.setBoolean(1, status);
                statement.setInt(2, pageSize);
                statement.setInt(3, (pageIndex - 1) * pageSize);
            }
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = setObjectFromResultSet(resultSet);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return categories;
    }

    public Category getById(UUID id) {
        Category category = null;
        CreateConnection();
        try {
            sql = "SELECT * FROM categories WHERE id=?;";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = setObjectFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return category;
    }

    public int add(Category category) throws Exception {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "INSERT INTO categories (id, name, status, url) VALUES (?, ?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, UUID.randomUUID());
            statement.setString(2, category.name);
            statement.setBoolean(3, category.status);
            statement.setString(4, category.url);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
        	if (e.getMessage().contains("duplicate key value violates unique constraint ")) throw new Exception("Đã tồn tại url");
            e.printStackTrace();
        } 
        finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public int update(Category category) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "UPDATE categories SET name=?, status=?, url=? WHERE id=?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, category.name);
            statement.setBoolean(2, category.status);
            statement.setString(3, category.url);
            statement.setObject(4, category.id);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public int delete(UUID id) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "DELETE FROM categories WHERE id=?;";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    @Override
    protected Category setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(UUID.fromString(resultSet.getString("id")));
        category.setName(resultSet.getString("name"));
        category.setStatus(resultSet.getBoolean("status"));
        category.setUrl(resultSet.getString("url"));
        return category;
    }

    public int getCount() {
        int res = 0;
        sql = "SELECT COUNT(*) FROM categories ;";
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
