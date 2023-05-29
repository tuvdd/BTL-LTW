package servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Admin;
import repositories.AdminRepo;

@WebServlet(name="AdminAdmin", urlPatterns = "/admin/admin")
public class AdminServlet extends BaseServlet {
    private AdminRepo adminRepo;

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

        int page = 1;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        List<Admin> listAdmins;
        try {
            int pageSize = 10;
            listAdmins = adminRepo.getAll(page, pageSize);
            req.setAttribute("listAdmins", listAdmins);

            int totalRecords = adminRepo.getCount();
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", page);
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
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phonenum = req.getParameter("phonenum");
        String cccd = req.getParameter("cccd");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Admin admin = new Admin();
        admin.set(null, name, email, phonenum, cccd, username, password);

        if (id != null && id != "") {
            admin.id = UUID.fromString(id);
            try {
                int res = adminRepo.update(admin);
                if (res == 1) {
                    req.getSession().setAttribute("message", "Sửa thành công!");
                    req.getSession().setAttribute("messageType", "success");
                } else {
                    req.getSession().setAttribute("message", "Sửa không thành công!");
                    req.getSession().setAttribute("messageType", "error");
                }
            } catch (SQLException e) {
                req.getSession().setAttribute("message", e.getMessage());
                req.getSession().setAttribute("messageType", "error");
            } finally {
                resp.sendRedirect("/btl_ltw/admin/admin");
            }
            return;
        }
        try {
            int res = adminRepo.add(admin);
            if (res >0) {
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
}
