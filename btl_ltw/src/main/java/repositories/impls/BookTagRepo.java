package repositories.impls;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.BookTag;
import repositories.interfaces.IBookTagRepo;
import repositories.utils.SQLInjection;
import repositories.utils.models.LogicalClause;
import repositories.utils.models.LogicalObject;
import repositories.utils.models.LogicalObjectType;

public class BookTagRepo extends Repo<BookTag> implements IBookTagRepo {
    public BookTagRepo() {
        super();
    }

    @Override
    public BookTag Get(UUID id) throws SQLException {
        BookTag response = null;
        try {LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
        LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
        LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
        LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "book_tags")+ SQLInjection.WHERESQL(logicalClauseArray)
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
    public List<BookTag> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<BookTag> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "book_tags ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<BookTag>();
            while (resultSet.next()) {
                var book_tag = setObjectFromResultSet(resultSet);
                response.add(book_tag);
            }

        } catch (Exception ex) {
        } finally{
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Add(BookTag record) throws SQLException {
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
    public int Update(BookTag record) throws SQLException {
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
    public int Delete(BookTag record) throws SQLException {
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
    protected BookTag setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        var response = new BookTag();
        response.set(
                UUID.fromString(resultSet.getString("book_id")),
                UUID.fromString(resultSet.getString("id")),
                UUID.fromString(resultSet.getString("tag_id")));
        return response;
    }

}
