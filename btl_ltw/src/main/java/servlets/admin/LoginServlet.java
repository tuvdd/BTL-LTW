package servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.impls.AdminService;
import services.interfaces.IAdminService;

@WebServlet({ "/admin/login", "/admin/login/" })
public class LoginServlet extends HttpServlet {
	private IAdminService adminService;

	public LoginServlet() {
		adminService = new AdminService();
	}

	private static final long serialVersionUID = 20;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (ServletUtil.IsSessionExsited(req, resp)) {
			resp.sendRedirect("/btl_ltw/admin/home.jsp");
			return;
		}
		resp.sendRedirect("/btl_ltw/admin/login.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username == null || username == "" || password == null || password == "") {
			req.setAttribute("error", "Tên tài khoản hoặc mật khẩu không được để trống");
			resp.sendRedirect("/btl_ltw/admin/login");
			return;
		}

		try {
			UUID id = adminService.Login(username, password);
			if (id == null) {
				req.getSession().setAttribute("error", "Tên tài khoản hoặc mật khẩu không đúng");
				resp.sendRedirect("/btl_ltw/admin/login");
				return;
			}

			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			resp.sendRedirect("/btl_ltw/admin");

		} catch (SQLException e) {
			req.getSession().setAttribute("error", e.getMessage());
			resp.sendRedirect("/btl_ltw/admin/login");
		}
	}
}
