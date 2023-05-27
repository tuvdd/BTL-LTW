package servlets.admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="AdminHome", urlPatterns = "/admin")
public class HomeServlet extends BaseServlet {
	private static final long serialVersionUID = 22;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);

		if (!ServletUtil.IsSessionExsited(req, resp)) {
			resp.sendRedirect("/btl_ltw/admin/login");
			return;
		}

		req.setAttribute("pageName", "home.jsp");
		req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
	}
}
