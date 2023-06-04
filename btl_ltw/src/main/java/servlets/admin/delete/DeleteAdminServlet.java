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
import servlets.admin.ServletUtil;

@WebServlet("/admin/admin/delete")
public class DeleteAdminServlet extends HttpServlet {
	private AdminRepo adminRepository;

	public void init() {
		adminRepository = new AdminRepo();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!ServletUtil.IsSessionExsited(request, response)) {
			response.sendRedirect("/btl_ltw/admin/login");
			return;
		}
		String adminId = request.getParameter("id");
		UUID id = (UUID) request.getSession().getAttribute("id");

		if (adminId.equals(id.toString())) {
			request.getSession().setAttribute("message", "Bạn đang dùng tài khoản này, không thể xóa!");
			request.getSession().setAttribute("messageType", "error");
			response.sendRedirect(request.getContextPath() + "/admin/admin");
			return;
		}

		try {

			int res = adminRepository.delete(UUID.fromString(adminId));
			if (res > 0) {
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