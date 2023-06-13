package servlets.admin.changStatus;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.OrderRepo;

@WebServlet("/admin/order/change-status")
public class OrderChangeStatusServlet extends HttpServlet {
    OrderRepo orderRepo;

    public OrderChangeStatusServlet() {
        orderRepo = new OrderRepo();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderId = request.getParameter("id");
        if (orderId == null || orderId.isEmpty()) {
        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ID parameter");
			return;
		}
        try {
            int status = Integer.parseInt(request.getParameter("status"));
            int res = orderRepo.updateStatus(UUID.fromString(orderId), status);

            if (res == 1) {
                request.getSession().setAttribute("message", "Đổi trạng thái thành công!");
                request.getSession().setAttribute("messageType", "success");
            } else {
                request.getSession().setAttribute("message", "Đổi trạng thái không thành công!");
                request.getSession().setAttribute("messageType", "error");
            }
            response.sendRedirect("/admin/order");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().append("Lỗi khi thay đổi trạng thái của order!");
        }
    }
}
