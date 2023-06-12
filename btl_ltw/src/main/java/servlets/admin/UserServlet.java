package servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import repositories.UserRepo;
@WebServlet(name="UserServlet", urlPatterns = "/admin/user")
public class UserServlet extends BaseServlet {
    private UserRepo userRepo;

    public UserServlet() {
        super();
        userRepo = new UserRepo();
    }

    private static final long serialVersionUID = 23;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        if (!ServletUtil.IsSessionExsited(req, resp)) {
            resp.sendRedirect("/admin/login");
            return;
        }

        int page = 1;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        List<User> listUsers;
        try {
            int pageSize = 10;
            listUsers = userRepo.getAll(page, pageSize);
            req.setAttribute("listUsers", listUsers);

            int totalRecords = userRepo.getCount();
            int totalPages = (int) Math.ceil((double)totalRecords / pageSize);

            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", page);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            req.setAttribute("pageName", "user.jsp");
            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletUtil.IsSessionExsited(req, resp)) {
            resp.sendRedirect("/admin/login");
            return;
        }
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phonenum = req.getParameter("phonenum");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.set(null, name, phonenum, email, true, null, null,username, password);

        if (id != null && id != "") {
            user.id = UUID.fromString(id);
            try {
                int res = userRepo.update(user);
                if (res == 1) {
                    req.getSession().setAttribute("message", "Sửa thành công!");
                    req.getSession().setAttribute("messageType", "success");
                } else {
                    req.getSession().setAttribute("message", "Sửa không thành công!");
                    req.getSession().setAttribute("messageType", "error");
                }
            } catch (Exception e) {
                req.getSession().setAttribute("message", e.getMessage());
                req.getSession().setAttribute("messageType", "error");
            } finally {
                resp.sendRedirect("/admin/user");
            }
            return;
        }

        try {
            int res = userRepo.add(user);
            if (res >0) {
                req.getSession().setAttribute("message", "Thêm mới thành công!");
                req.getSession().setAttribute("messageType", "success");
            } else {
                req.getSession().setAttribute("message", "Thêm mới không thành công!");
                req.getSession().setAttribute("messageType", "error");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("message", e.getMessage());
            req.getSession().setAttribute("messageType", "error");
        } finally {
            resp.sendRedirect("/admin/user");
        }
    }
}