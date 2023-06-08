package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import repositories.UserRepo;
import servlets.admin.BaseServlet;

@WebServlet({"/user/register", "/user/register/"})
public class RegisterUserServlet extends BaseServlet {
	private UserRepo userRepo;

	public RegisterUserServlet() {
		userRepo = new UserRepo();
	}

	private static final long serialVersionUID = 20;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doGet(req, resp);

		// if (ServletUtil.IsSessionExsited(req, resp)) {
		// 	resp.sendRedirect("/btl_ltw/admin");
		// 	return;
		// }
        System.out.println("Zo register");
		RequestDispatcher rd = req.getRequestDispatcher("/RegisterUser.jsp");
		rd.forward(req, resp);
		return;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (email == null || email == "" || phoneNumber == null || phoneNumber == "" || username == null || username == "" || password == null || password == "") {
			req.getSession().setAttribute("error", "Không được để trống!");
			resp.sendRedirect("/btl_ltw/user/register");
			System.out.println("nilll");
			return;
		}

		try {
			User user = userRepo.getUserByPhoneNumber(phoneNumber);
			if (user != null) {
                req.getSession().setAttribute("error", "Số điện thoại đã được đăng ký. Vui lòng nhập số khác!");
				resp.sendRedirect("/btl_ltw/user/register");
				return;
				
			}
            user = new User();
			user.set(UUID.randomUUID(), username, phoneNumber, email, isAccessFromServlet, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()), username, password);
			userRepo.add(user);
			System.out.println("thanh cong");
			HttpSession session = req.getSession();
			session.setAttribute("userID", user.getId().toString());
            System.out.println(user.getId().toString());
            String savedURL = (String) req.getSession().getAttribute("currentURL");
            if (savedURL == null) {
				savedURL = "/btl_ltw/";
			}
			resp.sendRedirect(savedURL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			req.getSession().setAttribute("error", e.getMessage());
			resp.sendRedirect("/btl_ltw/user/register");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.getSession().setAttribute("error", e.getMessage());
			resp.sendRedirect("/btl_ltw/user/register");
		}
	}
}