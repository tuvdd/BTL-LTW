package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Comment;

public class CommentRepo extends Repo<Comment> {
	public int Add(Comment comment) {
        int response = 0;
        CreateConnection();
        try {
            sql = "INSERT INTO comments (id, user_id, book_id, rate, comment_text, create_at) VALUES (?, ?, ?, ?, ?, ?);";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, UUID.randomUUID());
            statement.setObject(2, comment.getUserID());
            statement.setObject(3, comment.getBookId());
            statement.setInt(4, comment.getRate());
            statement.setString(5, comment.getComment());
            statement.setTimestamp(6, comment.getCreate_at());
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
            sql = "UPDATE comments SET rate = ?, comment_text = ?, create_at = ? WHERE id = ?";
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
            sql = "DELETE FROM comments WHERE id=?;";
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
            UUID.fromString(resultSet.getString("user_id")),
            UUID.fromString(resultSet.getString("book_id")),
            resultSet.getInt("rate"),
            resultSet.getString("comment_text"),
            resultSet.getTimestamp("create_at")
        );
		return response;
	}

    public List<Comment> GetlistCommentByBookID(String book_id, int page, int size) {
        // TODO Auto-generated method stub
        
        List<Comment> comments = new ArrayList<>();
        CreateConnection();
        try {
            sql = "SELECT c.comment_text, c.rate, c.create_at, u.name FROM comments c JOIN users u ON c.user_id = u.id WHERE c.book_id = ? ORDER BY create_at DESC LIMIT ? OFFSET ?;";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, UUID.fromString(book_id));
            statement.setInt(2, size);
            statement.setInt(3, (page - 1)*size);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String commentText = resultSet.getString("comment_text");
                int rate = resultSet.getInt("rate");
                String name = resultSet.getString("name");
                Timestamp create_at = resultSet.getTimestamp("create_at");
                Comment comment = new Comment();
                comment.setPropertyFromResultSet(name, commentText, rate, create_at);
                comments.add(comment);
                System.out.println("MAsssss");
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
            sql = "SELECT * from comments Where book_id=?";
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
