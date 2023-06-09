package servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import repositories.UserRepo;
import servlets.admin.BaseServlet;

@WebServlet({"/user/login", "/user/login/"})
public class LoginUserServlet extends BaseServlet {
	private UserRepo userRepo;

	public LoginUserServlet() {
		userRepo = new UserRepo();
	}

	private static final long serialVersionUID = 20;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doGet(req, resp);

		// if (ServletUtil.IsSessionExsited(req, resp)) {
		// 	resp.sendRedirect("/btl_ltw/admin");
		// 	return;
		// }
        System.out.println("Zo login");
		RequestDispatcher rd = req.getRequestDispatcher("/LoginUser.jsp");
		rd.forward(req, resp);
		return;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String phoneNumber = req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		if (phoneNumber == null || phoneNumber == "" || password == null || password == "") {
			req.getSession().setAttribute("error", "Không được để trống!");
			resp.sendRedirect("/btl_ltw/user/login");
			System.out.println("nilll");
			return;
		}

		try {
			User user = userRepo.getUserByPhoneNumber(phoneNumber);
			if (user == null) {
				req.getSession().setAttribute("error", "Số điện thoại chưa được đăng ký!");
				resp.sendRedirect("/btl_ltw/user/login");
				return;
			} else if (!user.password.equals(password)) {
				req.getSession().setAttribute("error", "Sai mật khẩu!");
				resp.sendRedirect("/btl_ltw/user/login");
				System.out.println("sai tk");
				return;
			}
			System.out.println("thanh cong");
			HttpSession session = req.getSession();
			session.setAttribute("userID", user.getId().toString());
			String savedURL = (String) req.getSession().getAttribute("currentURL");
			if (savedURL == null) {
				savedURL = "/btl_ltw/";
			}
			resp.sendRedirect(savedURL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			req.getSession().setAttribute("error", e.getMessage());
			resp.sendRedirect("/btl_ltw/user/login");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.getSession().setAttribute("error", e.getMessage());
			resp.sendRedirect("/btl_ltw/user/login");
		}
	}
}
