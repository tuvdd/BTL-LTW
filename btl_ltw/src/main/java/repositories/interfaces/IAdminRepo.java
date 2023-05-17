package repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import models.Admin;
import models.AdminLogin;
import models.dtos.AdminFullDetail;

public interface IAdminRepo extends IRepo<Admin>{
    int Register(Admin admin, AdminLogin adminLogin) throws SQLException;
    List<AdminFullDetail> GetsFullDetail(String PaginSQL) throws SQLException;
}
