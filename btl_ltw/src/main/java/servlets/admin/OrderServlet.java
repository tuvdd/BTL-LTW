package servlets.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dtos.AdminOrderPreview;
import repositories.OrderRepo;
@WebServlet(name="AdminOrder", urlPatterns = "/admin/order")
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

        int page = 1;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        List<AdminOrderPreview> listAdminOrderPreviews;
    
        try {
            int pageSize = 10;
            listAdminOrderPreviews = orderRepo.getAllAdminOrderView(page, pageSize);
        
            int totalRecords = orderRepo.getCount();
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", page);

            req.setAttribute("listAdminOrderPreviews", listAdminOrderPreviews);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            req.setAttribute("pageName", "order.jsp");
            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
        }
	}
}
