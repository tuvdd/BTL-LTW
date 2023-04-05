package demo.services.impls;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import demo.models.ReceivedNewsEmail;
import demo.services.interfaces.IReceivedNewsEmailRepo;
import demo.services.utils.SQLInjection;
import demo.services.utils.models.LogicalClause;
import demo.services.utils.models.LogicalObject;
import demo.services.utils.models.LogicalObjectType;

public class ReceivedNewsEmailRepo extends Repo<ReceivedNewsEmail> implements IReceivedNewsEmailRepo {
    public ReceivedNewsEmailRepo() {
        super();
    }

    @Override
    public ReceivedNewsEmail Get(UUID id) throws SQLException {
        ReceivedNewsEmail response = null;
        try {LogicalObject obj1 = new LogicalObject("id", LogicalObjectType.FIELD);
        LogicalObject obj2 = new LogicalObject(id.toString(), LogicalObjectType.STRING);
        LogicalClause logicalClause = new LogicalClause(obj1, "=", obj2);
        LogicalClause[] logicalClauseArray = { logicalClause };
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "received_news_emails")+ SQLInjection.WHERESQL(logicalClauseArray)
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
    public List<ReceivedNewsEmail> Gets(String WhereSQL, String PaginSQL) throws SQLException {
        List<ReceivedNewsEmail> response = null;
        try {
            CreateConnection();
            String sql = SQLInjection.SELECTSQL(null, "received_news_emails ") + WhereSQL + " " + PaginSQL + " ;";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            response = new ArrayList<ReceivedNewsEmail>();
            while (resultSet.next()) {
                var received_news_email = setObjectFromResultSet(resultSet);

                response.add(received_news_email);
            }

        } catch (Exception ex) {
        } finally{
            connection.close();
            statement.close();
        }
        return response;
    }

    @Override
    public int Add(ReceivedNewsEmail record) throws SQLException {
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
    public int Update(ReceivedNewsEmail record) throws SQLException {
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
    public int Delete(ReceivedNewsEmail record) throws SQLException {
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
    protected ReceivedNewsEmail setObjectFromResultSet(ResultSet resultSet) throws SQLException {
        var response = new ReceivedNewsEmail();
        response.set(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("email"));
        return response;
    }

}
