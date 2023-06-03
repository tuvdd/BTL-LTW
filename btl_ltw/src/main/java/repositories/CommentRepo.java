package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Comment;

public class CommentRepo extends Repo<Comment> {
	public int Add(Comment comment) {
        int response = 0;
        CreateConnection();
        try {
            sql = "INSERT INTO comment (id, book_id, rate, comment, create_at) VALUES (?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, UUID.randomUUID());
            statement.setObject(2, comment.getBookId());
            statement.setInt(3, comment.getRate());
            statement.setString(4, comment.getComment());
            statement.setTimestamp(5, comment.getCreate_at());
            response = statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            CloseConnection();
        }
		return response;
	}

	public int Update(Comment comment) {
		// TODO Auto-generated method stub
        int response = 0;
        CreateConnection();

        try {
            sql = "UPDATE comment SET rate = ?, comment = ?, create_at = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setFloat(1, comment.getRate());
            statement.setString(2, comment.getComment());
            statement.setTimestamp(3, comment.getCreate_at());
            statement.setObject(4, comment.getId().toString());

            response = statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            CloseConnection();
        }
		return response;
	}

	public int Delete(UUID id) {
		// TODO Auto-generated method stub
        int response = 0;
        CreateConnection();
        try {
            sql = "DELETE FROM comment WHERE id=?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id.toString());
            response = statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            CloseConnection();
        }
		return response;
	}

	protected Comment setObjectFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
        Comment response = new Comment();
        response.set(
            UUID.fromString(resultSet.getString("id")),
            UUID.fromString(resultSet.getString("book_id")),
            resultSet.getInt("rate"),
            resultSet.getString("comment"),
            resultSet.getTimestamp("create_at")
        );
		return response;
	}

    public List<Comment> GetlistCommentByBookID(String book_id, int page, int size) {
        // TODO Auto-generated method stub
        
        List<Comment> comments = new ArrayList<>();
        CreateConnection();
        try {
            sql = "SELECT * FROM comment WHERE book_id=? ORDER BY create_at DESC LIMIT ? OFFSET ?;";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, UUID.fromString(book_id));
            statement.setInt(2, size);
            statement.setInt(3, (page - 1)*size);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Comment comment = setObjectFromResultSet(resultSet);
                comments.add(comment);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            CloseConnection();
        }
        System.out.println(comments.size());
        return comments;
    }
    
    public int getNumberOfPages(String book_id, int commentsPerPage) {
        int res = 0;
        CreateConnection();
        try {
            sql = "SELECT * from comment Where book_id=?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, UUID.fromString(book_id));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                res += 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            CloseConnection();
        }
        int numberOfPages = 1;
        if (res % commentsPerPage == 0) {
            numberOfPages = res/commentsPerPage;
        } else {
            numberOfPages = res/commentsPerPage + 1;
        }
        return numberOfPages;
    }
}
