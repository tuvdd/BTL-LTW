package servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import models.dtos.GetBooksDTO;
import repositories.BookRepo;
import repositories.CategoryRepo;
import servlets.Utilities.StringUtilities;

@WebServlet(name = "ListBooksServlet", urlPatterns = "/danh-sach-san-pham")
public class ListBookServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BookRepo bookRepo;
	CategoryRepo categoryRepo;

	public ListBookServlet() {
		super();
		bookRepo = new BookRepo();
		categoryRepo = new CategoryRepo();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String urldanhmuc = request.getParameter("urldanhmuc");
		String page = request.getParameter("page");
		String filter = request.getParameter("filter");
		String priceMin = request.getParameter("pricemin");
		String priceMax = request.getParameter("pricemax");
		GetBooksDTO res = bookRepo.gets(urldanhmuc, page, filter, priceMin, priceMax);
		List<Category> categories = categoryRepo.getAll(-1, -1, true);

		request.setAttribute("urldanhmuc", urldanhmuc);
		request.setAttribute("page", page);
		request.setAttribute("filter", filter);
		request.setAttribute("pricemin", priceMin);
		request.setAttribute("pricemax", priceMax);
		request.setAttribute("tenDanhMuc", categoryRepo.getByUrl(urldanhmuc).name);
		request.setAttribute("total", res.getTotal());
		request.setAttribute("data", res.getData());
		request.setAttribute("categories", categories);
		request.setAttribute("currentUrl", StringUtilities.getRequestURLWithoutPageParam(request));

		request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
	}
}
