package servlets.admin.delete;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.UserRepo;
import servlets.admin.ServletUtil;

@WebServlet("/admin/user/delete")
public class DeleteUserServlet extends HttpServlet {
    private UserRepo userRepo;

    public DeleteUserServlet() {
        userRepo = new UserRepo();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!ServletUtil.IsSessionExsited(request, response)) {
            response.sendRedirect("/btl_ltw/admin/login");
            return;
        }
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category ID parameter is missing or empty");
            return;
        }
        try {

            int res = userRepo.delete(UUID.fromString(idParam));
            if (res > 0) {
                request.getSession().setAttribute("message", "Xóa thành công!");
                request.getSession().setAttribute("messageType", "success");
            } else {
                request.getSession().setAttribute("message", "Xóa không thành công!");
                request.getSession().setAttribute("messageType", "error");
            }

            response.sendRedirect(request.getContextPath() + "/admin/user");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().append("Lỗi khi xóa user!");
        }
    }
}