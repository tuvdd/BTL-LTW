package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Repo<T> {
    protected Connection connection;
    protected PreparedStatement statement;
    protected String sql;
    protected ResultSet resultSet;

    protected abstract T setObjectFromResultSet(ResultSet resultSet) throws SQLException;

    protected void CreateConnection() {
        try {
            Class.forName(DBConfig.className);
            connection = DriverManager.getConnection(DBConfig.url, DBConfig.username, DBConfig.password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    protected void CloseConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
