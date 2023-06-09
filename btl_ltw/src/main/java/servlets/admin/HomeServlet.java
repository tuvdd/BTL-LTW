package servlets.admin;

import java.io.IOException;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.OrderRepo;

@WebServlet(name = "AdminHome", urlPatterns = "/admin")
public class HomeServlet extends BaseServlet {
	OrderRepo orderRepo;

	public HomeServlet() {
		orderRepo = new OrderRepo();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);

		if (!ServletUtil.IsSessionExsited(req, resp)) {
			resp.sendRedirect("/btl_ltw/admin/login");
			return;
		}

		Timestamp t = new Timestamp(System.currentTimeMillis());

		req.setAttribute("Số lượng đơn chờ xác nhận", orderRepo.getCountOrderByStatus(0));
		req.setAttribute("Số lượng đơn chờ thanh toán", orderRepo.getCountOrderByStatus(1));
		req.setAttribute("Số lượng đơn chờ vận chuyển", orderRepo.getCountOrderByStatus(2));
		req.setAttribute("Tổng doanh thu tháng", orderRepo.tinhTongDoanhThuTheoThang(t));
		req.setAttribute("Tổng doanh thu năm", orderRepo.tinhTongDoanhThuTheoNam(t));

		req.setAttribute("pageName", "home.jsp");
		req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
	}
}
