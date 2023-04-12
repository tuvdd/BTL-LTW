package services.impls;

import java.sql.SQLException;
import java.util.UUID;

import models.AdminLogin;
import repositories.impls.AdminLoginRepo;
import repositories.interfaces.IAdminLoginRepo;
import services.interfaces.IAdminService;

public class AdminService implements IAdminService {
    private IAdminLoginRepo adminLoginRepo;

    public AdminService() {
        adminLoginRepo = new AdminLoginRepo();
    }

    @Override
    public UUID Login(String username, String password) throws SQLException {
        AdminLogin adminLogin = adminLoginRepo.Get(username, password);
        if (adminLogin != null) {
            return adminLogin.id;
        } else
            return null;
    }

}
