package repositories.interfaces;

import java.sql.SQLException;
import java.util.UUID;

import models.AdminLogin;

public interface IAdminLoginRepo extends IRepo<AdminLogin>{
    public AdminLogin Get(String username, String password) throws SQLException;

    public UUID GetIdByUsernamePassword(String username, String password) throws SQLException;
}
