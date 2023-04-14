package repositories.interfaces;

import java.sql.SQLException;

import models.AdminLogin;

public interface IAdminLoginRepo extends IRepo<AdminLogin>{
    public AdminLogin Get(String username, String password) throws SQLException;
}
