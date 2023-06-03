package servlets.admin.changStatus;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import repositories.UserRepo;

@WebServlet("/admin/user/change-status")
public class UserChangeStatusServlet extends HttpServlet {
    private UserRepo userRepository;

    public void init() {
        userRepository = new UserRepo();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("id");

        try {
            User user = userRepository.getById(UUID.fromString(userId));
            user.setStatus(!user.getStatus());
            int res = userRepository.update(user);

            if (res == 1) {
                request.getSession().setAttribute("message", "Đổi trạng thái thành công!");
                request.getSession().setAttribute("messageType", "success");
            } else {
                request.getSession().setAttribute("message", "Đổi trạng thái không thành công!");
                request.getSession().setAttribute("messageType", "error");
            }
            response.sendRedirect("/btl_ltw/admin/user");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().append("Lỗi khi thay đổi trạng thái của user!");
        }
    }
}