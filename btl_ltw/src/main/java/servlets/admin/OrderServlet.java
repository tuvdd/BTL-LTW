package servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Order;
import repositories.impls.OrderRepo;
import repositories.interfaces.IOrderRepo;

@WebServlet({ "/admin/order", "/admin/order/" })
public class OrderServlet extends BaseServlet {
    private IOrderRepo orderRepo;

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

			listCategories = orderRepo.Gets("","");
			req.setAttribute("listCategories", listCategories);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			req.setAttribute("pageName", "order.jsp");
			req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!ServletUtil.IsSessionExsited(req, resp)) {
			resp.sendRedirect("/btl_ltw/admin/login");
			return;
		}

		try {
			int res = orderRepo.Add(null);
			if (res == 1) {
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
			resp.sendRedirect("/btl_ltw/admin/order");
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
