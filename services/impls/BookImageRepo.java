package demo.services.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import demo.models.BookImage;
import demo.services.interfaces.IBookImageRepo;
import demo.services.utils.SQLInjection;
import demo.services.utils.models.LogicalClause;
import demo.services.utils.models.LogicalObject;
import demo.services.utils.models.LogicalObjectType;

public class BookImageRepo extends Repo<BookImage> implements IBookImageRepo {
    public BookImageRepo() {
        super();
    }

    @Override
    public BookImage Get(UUID id) throws SQLException {
        BookImage response = null;
        try {
            LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
            LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
            LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
            LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "book_images") + SQLInjection.WHERESQL(logicalClauseArray)
                    + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                response = setObjectFromResultSet(resultSet);
            }

        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public List<BookImage> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<BookImage> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "bookImages ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<BookImage>();
            while (resultSet.next()) {
                var BookImage = new BookImage();
                BookImage.set(UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("value"),
                        UUID.fromString(resultSet.getString("book_id")));

                response.add(BookImage);
            }

        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Add(BookImage record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetInsertSQL();
            CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Update(BookImage record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetUpdateSQL();
            CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Delete(BookImage record) throws SQLException {
        int response = 0;
        try {
            sql = record.GetDeteleSQL();
            CreateConnection();
            statement = connection.prepareStatement(sql);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    protected BookImage setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        var response = new BookImage();
        response.set(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("value"),
                UUID.fromString(resultSet.getString("book_id")));
        return response;
    }

    @Override
    public List<BookImage> GetListBookImageByBookId(UUID id) throws SQLException {
        String id_String = id.toString();
        return GetListBookImageByBookId(id_String);
    }

    @Override
    public List<BookImage> GetListBookImageByBookId(String book_id) throws SQLException {
        LogicalClause clause = new LogicalClause(new LogicalObject("book_id", LogicalObjectType.FIELD), "=",
                new LogicalObject(book_id, LogicalObjectType.STRING));

        String selectSql = SQLInjection.SELECTSQL(null, BookImage.GetTableName()) + " ";
        String whereSql = SQLInjection.WHERESQL(clause) + " ";

        String sql = selectSql + whereSql + " ;";

        List<BookImage> response = null;
        try {
            CreateConnection();
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<BookImage>();
            while (resultSet.next()) {
                var BookImage = new BookImage();
                BookImage.set(UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("value"),
                        UUID.fromString(resultSet.getString("book_id")));

                response.add(BookImage);
            }

        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }
}
