package servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repositories.impls.AdminRepo;
import repositories.interfaces.IAdminLoginRepo;
import repositories.interfaces.IAdminRepo;

@WebServlet({ "/admin/login", "/admin/login/" })
public class LoginServlet extends BaseServlet {
	private IAdminRepo adminRepo;
	private IAdminLoginRepo adminLoginRepo;

	public LoginServlet() {
		adminRepo = new AdminRepo();
	}

	private static final long serialVersionUID = 20;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req,resp);

		if (ServletUtil.IsSessionExsited(req, resp)) {
			resp.sendRedirect("/btl_ltw/admin");
			return;
		}
		RequestDispatcher rd = req.getRequestDispatcher("/admin/login.jsp");
		rd.forward(req, resp);
		return;
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
			UUID id = adminLoginRepo.GetIdByUsernamePassword(username, password);
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
