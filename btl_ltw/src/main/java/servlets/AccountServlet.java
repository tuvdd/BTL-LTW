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
import models.Admin;
import models.User;
import repositories.AdminRepo;
import repositories.UserRepo;
import servlets.admin.BaseServlet;

@WebServlet({"/account", "/account/"})
public class AccountServlet extends BaseServlet {
	private UserRepo userRepo;

	public AccountServlet() {
		userRepo = new UserRepo();
	}

	private static final long serialVersionUID = 20;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userID = (String) req.getSession().getAttribute("userID");
		if ( userID == null) {
            String currentURL = req.getRequestURL().toString();
            // currentURL += "?account=" + ;
            req.getSession().setAttribute("currentURL", currentURL);
            resp.sendRedirect("/btl_ltw/login");
            return;
        }

        try {
            User user= userRepo.getById(UUID.fromString(userID));
            req.setAttribute("user", user);
            System.out.println(user);
            RequestDispatcher rd = req.getRequestDispatcher("/Account.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            req.getSession().setAttribute("error", e.getMessage());
            e.printStackTrace();
            // TODO: handle exception
        }
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("userID");
		String currentURL = req.getRequestURL().toString();
        req.getSession().setAttribute("currentURL", currentURL);
		resp.sendRedirect("/btl_ltw/login");
	}
}
