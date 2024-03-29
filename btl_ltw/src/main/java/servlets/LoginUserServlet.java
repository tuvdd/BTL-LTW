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
import servlets.Utilities.StringUtilities;
import servlets.admin.BaseServlet;

@WebServlet({"/login", "/login/"})
public class LoginUserServlet extends BaseServlet {
	private UserRepo userRepo;

	public LoginUserServlet() {
		userRepo = new UserRepo();
	}

	private static final long serialVersionUID = 20;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("zolofin");
		RequestDispatcher rd = req.getRequestDispatcher("/UserLogin.jsp");
		rd.forward(req, resp);
		return;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String phoneNumber = req.getParameter("phoneNumber").trim();
		String password = req.getParameter("password").trim();
		if (phoneNumber == null || phoneNumber == "" || password == null || password == "") {
			req.getSession().setAttribute("error", "Không được để trống!");
			resp.sendRedirect("/login");
			return;
		} else if(!StringUtilities.isValidPhoneNumber(phoneNumber)) {
			req.getSession().setAttribute("error", "Không đúng định dạng số điện thoại!");
			resp.sendRedirect("/login");
			return;
		}
		try {
			User user = userRepo.getUserByPhoneNumber(phoneNumber);
			if (user == null) {
				req.getSession().setAttribute("error", "Số điện thoại chưa được đăng ký!");
				resp.sendRedirect("/login");
				return;
			} else if (!user.password.equals(password)) {
				req.getSession().setAttribute("error", "Sai mật khẩu!");
				resp.sendRedirect("/login");
				return;
			} else if (user.status == false) {
				req.getSession().setAttribute("error", "Tài khoản đã bị khoá!");
				resp.sendRedirect("/login");
				return;
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("userID", user.getId().toString());
			String savedURL = (String) session.getAttribute("currentURL");
			if (savedURL == null) {
				savedURL = "/btl_ltw";
			}
			resp.sendRedirect(savedURL);
		} catch (SQLException e) {
			
			req.getSession().setAttribute("error", e.getMessage());
			resp.sendRedirect("/login");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.getSession().setAttribute("error", e.getMessage());
			resp.sendRedirect("/login");
		}
	}
}
