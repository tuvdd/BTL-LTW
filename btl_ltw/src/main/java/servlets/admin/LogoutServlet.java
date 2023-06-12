package servlets.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="AdminLogout", urlPatterns = "/admin/logout")
public class LogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 21;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		for (int i = 0; i < ServletUtil.SesstionAttributes.length; i++)
			req.getSession().setAttribute(ServletUtil.SesstionAttributes[i], null);
		resp.sendRedirect("/admin/login");
	}

}
