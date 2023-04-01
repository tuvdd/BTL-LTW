package demo.services.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import demo.models.Book;
import demo.services.interfaces.IBookRepo;
import demo.services.utils.SQLInjection;
import demo.services.utils.models.LogicalClause;
import demo.services.utils.models.LogicalObject;
import demo.services.utils.models.LogicalObjectType;
import demo.services.utils.models.OrderByObject;
import demo.services.utils.models.OrderType;

public class BookRepo extends Repo<Book> implements IBookRepo {

    @Override
    public Book Get(UUID id) throws SQLException {
        Book response = null;
        try {
            LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
            LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
            LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
            LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "books") + SQLInjection.WHERESQL(logicalClauseArray)
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
    public List<Book> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<Book> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "books ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<Book>();
            while (resultSet.next()) {
                var Book = setObjectFromResultSet(resultSet);
                response.add(Book);
            }

        } catch (Exception ex) {
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Add(Book record) throws SQLException {
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
    public int Update(Book record) throws SQLException {
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
    public int Delete(Book record) throws SQLException {
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
    protected Book setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        var response = new Book();
        response.set(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name"),
                resultSet.getString("author"),
                resultSet.getInt("release_year"),
                UUID.fromString(resultSet.getString("category_id")),
                resultSet.getDouble("price"),
                resultSet.getDouble("promote_price"),
                resultSet.getInt("quantity"),
                resultSet.getString("description"),
                resultSet.getString("sub_description"),
                resultSet.getInt("status"),
                resultSet.getTimestamp("create_time"),
                UUID.fromString(resultSet.getString("create_by")),
                resultSet.getTimestamp("last_update_time"),
                UUID.fromString(resultSet.getString("last_update_by")));
        return response;
    }

    @Override
    public List<Book> GetListBookByCategoryID(String uuid, int page, int size) throws SQLException {
        String sql = "";

        LogicalObject obj1 = new LogicalObject("category_id", LogicalObjectType.FIELD);
        LogicalObject obj2 = new LogicalObject(uuid.toString(), LogicalObjectType.STRING);
        LogicalClause clause = new LogicalClause(obj1, "=", obj2);
        LogicalClause[] clausArray = { clause };
        var whereSql = SQLInjection.WHERESQL(clausArray);

        OrderByObject obj3 = new OrderByObject("last_update_time", OrderType.DESC);
        OrderByObject[] orderArr = { obj3 };
        var paginSql = SQLInjection.PAGINSQL(page, size, orderArr);

        sql += SQLInjection.SELECTSQL(null, "books") + " " + whereSql + " " + paginSql + " ;";

        List<Book> response = null;
        try {
            CreateConnection();

            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<Book>();
            while (resultSet.next()) {
                var Book = setObjectFromResultSet(resultSet);
                response.add(Book);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public List<Book> GetListBookByPrice(double fromMoney, double toMoney, int page, int size) throws SQLException {
        var sql = "";
        OrderByObject obj1 = new OrderByObject("price", OrderType.ASC);
        OrderByObject[] objArr = { obj1 };
        var paginSql = SQLInjection.PAGINSQL(page, size, objArr);
        sql += SQLInjection.SELECTSQL(null, "books") + " ";
        sql += "WHERE " + SQLInjection.BETWEEN("books", fromMoney + "", toMoney + "") + paginSql + " ;";

        List<Book> response = null;
        try {
            CreateConnection();

            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<Book>();
            while (resultSet.next()) {
                var Book = setObjectFromResultSet(resultSet);
                response.add(Book);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public List<Book> GetListBookByBookTagString(String tagString) throws SQLException {
        String sql = "SELECT * FROM books " +
                "WHERE id IN (" +
                "SELECT book_id FROM book_tags " +
                "WHERE tag_id IN (" +
                "SELECT id FROM tags WHERE tag_name LIKE '"
                + tagString + "')); ";
                
        List<Book> response = null;
        try {
            CreateConnection();

            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<Book>();
            while (resultSet.next()) {
                var Book = setObjectFromResultSet(resultSet);
                response.add(Book);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
            statement.close();
        }
        return response;
    }

}
