package servlets.admin.delete;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.AdminRepo;

@WebServlet("/admin/admin/delete")
public class DeleteAdminServlet extends HttpServlet {
    private AdminRepo adminRepository;

    public void init() {
        adminRepository = new AdminRepo();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String adminId = request.getParameter("id");

        try {
            
            int res = adminRepository.delete(UUID.fromString(adminId));
            if (res == 2) {
                request.getSession().setAttribute("message", "Xóa thành công!");
                request.getSession().setAttribute("messageType", "success");
            } else {
                request.getSession().setAttribute("message", "Xóa không thành công!");
                request.getSession().setAttribute("messageType", "error");
            }
            
            response.sendRedirect(request.getContextPath() + "/admin/admin");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().append("Lỗi khi xóa admin!");
        }
    }
}