package services.interfaces;

import java.sql.SQLException;
import java.util.UUID;

public interface IAdminService {
    UUID Login(String username, String password) throws SQLException;
}
