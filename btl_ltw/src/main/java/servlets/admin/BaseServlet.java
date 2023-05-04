package servlets.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 999999;
	protected boolean isAccessFromServlet;

    public BaseServlet() {
        super();
        isAccessFromServlet = true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isAccessFromServlet", isAccessFromServlet);
    }
    
}
