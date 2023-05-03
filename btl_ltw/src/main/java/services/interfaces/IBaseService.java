package services.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IBaseService<T> {
    String To_Json_String(T obj) throws SQLException;

    String To_Json_String(List<T> listObj) throws SQLException;
}
