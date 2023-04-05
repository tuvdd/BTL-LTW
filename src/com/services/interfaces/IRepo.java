package demo.services.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface IRepo<T> {

    public T Get(UUID id) throws SQLException;

    public List<T> Gets(String WhereSQL, String PaginSQL) throws SQLException;

    public int Add(T record) throws SQLException;

    public int Update(T record) throws SQLException;

    public int Delete(T record) throws SQLException;
}
