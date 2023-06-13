package servlets.admin.delete;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.OrderRepo;
import servlets.admin.ServletUtil;

@WebServlet("/admin/order/delete")
public class DeleteOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletUtil.IsSessionExsited(request, response)) {
            response.sendRedirect("/admin/login");
            return;
        }
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Order ID parameter is missing or empty");
            return;
        }

        try {
            UUID id = UUID.fromString(idParam);
            OrderRepo bookRepo = new OrderRepo();
            int rowsAffected = bookRepo.delete(id);
            if (rowsAffected == 0) {
                request.getSession().setAttribute("message", "Xóa không thành công!");
                request.getSession().setAttribute("messageType", "error");
            } else {
                request.getSession().setAttribute("message", "Xóa thành công!");
                request.getSession().setAttribute("messageType", "success");
            }

            response.sendRedirect(request.getContextPath() + "/admin/order");
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Order ID parameter is not a valid UUID");
        }
    }
}