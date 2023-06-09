package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Tag;

public class TagRepo extends Repo<Tag> {

    public List<Tag> getAll() {
        List<Tag> tags = new ArrayList<>();
        CreateConnection();
        try {
            sql = "SELECT * FROM tags";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tag tag = setObjectFromResultSet(resultSet);
                tags.add(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return tags;
    }

    public Tag getById(UUID id) {
        Tag tag = null;
        CreateConnection();
        try {
            sql = "SELECT * FROM tags WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tag = setObjectFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return tag;
    }

    public int add(Tag tag) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "INSERT INTO tags (id, tag_name) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, tag.id);
            statement.setString(2, tag.tag_name);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public int update(Tag tag) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "UPDATE tags SET tag_name = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, tag.tag_name);
            statement.setObject(2, tag.id);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public int delete(UUID tagId) {
        int rowsAffected = 0;
        CreateConnection();
        try {
            sql = "DELETE FROM tags WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, tagId);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseConnection();
        }
        return rowsAffected;
    }

    public Tag setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Tag tag = new Tag();
        tag.id = UUID.fromString(resultSet.getString("id"));
        tag.tag_name = resultSet.getString("tag_name");
        return tag;
    }
}