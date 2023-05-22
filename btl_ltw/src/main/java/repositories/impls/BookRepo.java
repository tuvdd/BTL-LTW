package repositories.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Book;
import models.dtos.AdminBookView;
import repositories.interfaces.IBookRepo;
import repositories.utils.SQLInjection;
import repositories.utils.models.LogicalClause;
import repositories.utils.models.LogicalObject;
import repositories.utils.models.LogicalObjectType;
import repositories.utils.models.OrderByObject;
import repositories.utils.models.OrderType;

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
            CloseConnection();
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
                Book Book = setObjectFromResultSet(resultSet);
                response.add(Book);
            }

        } catch (Exception ex) {
        } finally {
            CloseConnection();
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
            statement.setBytes(1, record.image);
            response = statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            CloseConnection();
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
            if (record.getImage() != null && record.getImage().length > 0)
                statement.setBytes(1, record.image);
            response = statement.executeUpdate();
        } catch (Exception ex) {
        } finally {
            CloseConnection();
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
            CloseConnection();
        }
        return response;
    }

    @Override
    protected Book setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        Book response = new Book();
        response.set(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name"),
                resultSet.getBytes("image"),
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
        String whereSql = SQLInjection.WHERESQL(clausArray);

        OrderByObject obj3 = new OrderByObject("last_update_time", OrderType.DESC);
        OrderByObject[] orderArr = { obj3 };
        String paginSql = SQLInjection.PAGINSQL(page, size, orderArr);

        sql += SQLInjection.SELECTSQL(null, "books") + " " + whereSql + " " + paginSql + " ;";

        List<Book> response = null;
        try {
            CreateConnection();

            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<Book>();
            while (resultSet.next()) {
                Book Book = setObjectFromResultSet(resultSet);
                response.add(Book);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            CloseConnection();
        }
        return response;
    }

    @Override
    public List<Book> GetListBookByPrice(double fromMoney, double toMoney, int page, int size) throws SQLException {
        String sql = "";
        OrderByObject obj1 = new OrderByObject("price", OrderType.ASC);
        OrderByObject[] objArr = { obj1 };
        String paginSql = SQLInjection.PAGINSQL(page, size, objArr);
        sql += SQLInjection.SELECTSQL(null, "books") + " ";
        sql += "WHERE " + SQLInjection.BETWEEN("books", fromMoney + "", toMoney + "") + paginSql + " ;";

        List<Book> response = null;
        try {
            CreateConnection();

            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<Book>();
            while (resultSet.next()) {
                Book Book = setObjectFromResultSet(resultSet);
                response.add(Book);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            CloseConnection();
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
                Book Book = setObjectFromResultSet(resultSet);
                response.add(Book);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            CloseConnection();
        }
        return response;
    }

    @Override
    public List<AdminBookView> GetsAdminBookView(String PaginSQL) throws SQLException {
        List<AdminBookView> response = null;
        try {
            String sql = ""
                    + "SELECT  b.id id, "
                    + "        b.name name, "
                    + "        b.image image, "
                    + "        b.author author, "
                    + "        b.release_year release_year, "
                    + "        b.category_id category_id, "
                    + "        c.name category_name, "
                    + "        b.price price, "
                    + "        b.promote_price promote_price, "
                    + "        b.quantity quantity, "
                    + "        b.description description, "
                    + "        b.sub_description sub_description, "
                    + "        b.status status, "
                    + "        b.create_time create_time, "
                    + "        b.create_by create_by, "
                    + "        ac.name create_by_name, "
                    + "        b.last_update_time last_update_time, "
                    + "        b.last_update_by last_update_by, "
                    + "        au.name last_update_by_name "
                    + "FROM    books b, "
                    + "        categories c, "
                    + "        admins ac, "
                    + "        admins au "
                    + "WHERE   b.category_id = c.id AND ac.id = b.create_by AND au.id = b.last_update_by "
                    + PaginSQL
                    + " ;";
            CreateConnection();
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            response = new ArrayList<>();

            while (rs.next()) {
                AdminBookView bfd = new AdminBookView();
                bfd.id = UUID.fromString(rs.getString("id"));
                bfd.name = rs.getString("name");
                bfd.image = rs.getBytes("image");
                bfd.author = rs.getString("author");
                bfd.release_year = rs.getInt("release_year");
                bfd.category_id = UUID.fromString(rs.getString("category_id"));
                bfd.category_name = rs.getString("category_name");
                bfd.price = rs.getDouble("price");
                bfd.promote_price = rs.getDouble("promote_price");
                bfd.quantity = rs.getInt("quantity");
                bfd.description = rs.getString("description");
                bfd.sub_description = rs.getString("sub_description");
                bfd.status = rs.getInt("status");
                bfd.create_time = rs.getTimestamp("create_time");
                bfd.create_by = UUID.fromString(rs.getString("create_by"));
                bfd.create_by_name = rs.getString("create_by_name");
                bfd.last_update_time =rs.getTimestamp("last_update_time");
                bfd.last_update_by = UUID.fromString(rs.getString("last_update_by"));
                bfd.last_update_by_name = rs.getString("last_update_by_name");

                response.add(bfd);
            }

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            CloseConnection();
        }

        return response;
    }

}
