package servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Admin;
import models.AdminLogin;
import models.dtos.AdminFullDetail;
import repositories.impls.AdminRepo;
import repositories.interfaces.IAdminRepo;

@WebServlet({ "/admin/admin", "/admin/admin/" })
public class AdminServlet extends BaseServlet {
    private IAdminRepo adminRepo;

    public AdminServlet() {
        super();
        adminRepo = new AdminRepo();
    }

    private static final long serialVersionUID = 22;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        if (!ServletUtil.IsSessionExsited(req, resp)) {
            resp.sendRedirect("/btl_ltw/admin/login");
            return;
        }

        List<AdminFullDetail> listAdminFullDetails;
        try {

            listAdminFullDetails = adminRepo.GetsFullDetail("");
            req.setAttribute("listAdminFullDetails", listAdminFullDetails);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            req.setAttribute("pageName", "admin.jsp");
            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletUtil.IsSessionExsited(req, resp)) {
            resp.sendRedirect("/btl_ltw/admin/login");
            return;
        }

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phonenum = req.getParameter("phonenum");
        String cccd = req.getParameter("cccd");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Admin admin = new Admin();
        AdminLogin adminLogin = new AdminLogin();
        admin.set(null, name, email, phonenum, cccd);
        adminLogin.set(null, username, password);
        try {
            int res = adminRepo.Register(admin, adminLogin);
            if (res == 2) {
                req.getSession().setAttribute("message", "Thêm mới thành công!");
                req.getSession().setAttribute("messageType", "success");
            } else {
                req.getSession().setAttribute("message", "Thêm mới không thành công!");
                req.getSession().setAttribute("messageType", "error");
            }
        } catch (SQLException e) {
            req.getSession().setAttribute("message", e.getMessage());
            req.getSession().setAttribute("messageType", "error");
        } finally {
            resp.sendRedirect("/btl_ltw/admin/admin");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
