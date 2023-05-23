package servlets.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Order;
import repositories.OrderRepo;
@WebServlet({ "/admin/order", "/admin/order/" })
public class OrderServlet extends BaseServlet {
    private OrderRepo orderRepo;

    public OrderServlet() {
        super();
        orderRepo = new OrderRepo();
    }

	private static final long serialVersionUID = 23;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);

		if (!ServletUtil.IsSessionExsited(req, resp)) {
			resp.sendRedirect("/btl_ltw/admin/login");
			return;
		}

		List<Order> listCategories;
		try {

			listCategories = orderRepo.getAll(1,10);
			req.setAttribute("listCategories", listCategories);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			req.setAttribute("pageName", "order.jsp");
			req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
		}
	}
}
