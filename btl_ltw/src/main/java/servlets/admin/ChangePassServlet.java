package servlets.admin;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.AdminRepo;

@WebServlet(name = "AdminChangePass", urlPatterns = "/admin/changepass")
public class ChangePassServlet extends BaseServlet {
    private AdminRepo adminRepo;

    public ChangePassServlet() {
        adminRepo = new AdminRepo();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletUtil.IsSessionExsited(req, resp)) {
            resp.sendRedirect("/btl_ltw/admin/login");
            return;
        }

        req.setAttribute("pageName", "changepass.jsp");
        req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletUtil.IsSessionExsited(req, resp)) {
            resp.sendRedirect("/btl_ltw/a		dmin/login");
            return;
        }

        String oldPass = req.getParameter("oldpass");
        String newPass = req.getParameter("newpass");
        String confirmNewPass = req.getParameter("confirmnewpass");
        try {
            int res = adminRepo.changePass(((UUID)req.getSession().getAttribute("id")), oldPass, newPass, confirmNewPass);
            if (res > 0) {
                req.getSession().setAttribute("message", "Đổi mật khẩu thành công!");
                req.getSession().setAttribute("messageType", "success");
            } else {
                req.getSession().setAttribute("message", "Đổi mật khẩu không thành công!");
                req.getSession().setAttribute("messageType", "error");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("message", e.getMessage());
            req.getSession().setAttribute("messageType", "error");
        } finally {
            resp.sendRedirect("/btl_ltw/admin/changepass");
        }
    }

}
