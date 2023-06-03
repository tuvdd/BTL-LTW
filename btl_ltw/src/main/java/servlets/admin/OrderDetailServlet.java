package servlets.admin;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dtos.OrderFullDetail;
import repositories.OrderRepo;

@WebServlet(name = "AdminOrderDetail", urlPatterns = "/admin/order/detail")
public class OrderDetailServlet extends BaseServlet {
	private OrderRepo orderRepo;

	public OrderDetailServlet() {
		orderRepo = new OrderRepo();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);

		if (!ServletUtil.IsSessionExsited(req, resp)) {
			resp.sendRedirect("/btl_ltw/admin/login");
			return;
		}

		String id = req.getParameter("id");
		if (id == null || id.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ID parameter");
			return;
		}
		try {
			OrderFullDetail orderFullDetail = orderRepo.getOrderFullDetailById(UUID.fromString(id));
			req.setAttribute("orderFullDetail", orderFullDetail);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			req.setAttribute("pageName", "order_detail.jsp");
			req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
		}
	}
}