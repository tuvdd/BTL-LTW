package demo.services.impls;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import demo.models.Category;
import demo.services.interfaces.ICategoryRepo;
import demo.services.utils.SQLInjection;
import demo.services.utils.models.LogicalClause;
import demo.services.utils.models.LogicalObject;
import demo.services.utils.models.LogicalObjectType;

public class CategoryRepo extends Repo<Category> implements ICategoryRepo {
    public CategoryRepo() {
        super();
    }

    @Override
    public Category Get(UUID id) throws SQLException {
        Category response = null;
        try {LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
        LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
        LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
        LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "categories")+ SQLInjection.WHERESQL(logicalClauseArray)
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
    public List<Category> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<Category> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "categories ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<Category>();
            while (resultSet.next()) {
                var category = setObjectFromResultSet(resultSet);
                response.add(category);
            }

        } catch (Exception ex) {
        } finally{
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Add(Category record) throws SQLException {
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
    public int Update(Category record) throws SQLException {
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
    public int Delete(Category record) throws SQLException {
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
    protected Category setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        var response = new Category();
        response.set(UUID.fromString(resultSet.getString("id")), resultSet.getString("name"),
                resultSet.getBoolean(("status")));
        return response;
    }

}
