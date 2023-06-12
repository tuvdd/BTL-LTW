package servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
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

		request.setAttribute("urldanhmuc", urldanhmuc);
		request.setAttribute("page", page);
		request.setAttribute("filter", filter);
		request.setAttribute("pricemin", priceMin);
		request.setAttribute("pricemax", priceMax);
		request.setAttribute("tenDanhMuc", categoryRepo.getByUrl(urldanhmuc).name);

		GetBooksDTO res = bookRepo.gets(urldanhmuc, page, filter, priceMin, priceMax);
		request.setAttribute("size", res.getSize());
		request.setAttribute("total", res.getTotal());
		request.setAttribute("data", res.getData());

		List<Category> categories = categoryRepo.getAll(-1, -1, true);
		request.setAttribute("categories", categories);

		String currentUrl = request.getRequestURI() + "?" + (request.getQueryString() != null ? request.getQueryString() : "");
		int index = currentUrl.indexOf("page=");
		if (index != -1) {
			currentUrl = currentUrl.substring(0, index);
			while (currentUrl.charAt(currentUrl.length() - 1) == '&') {
				currentUrl = currentUrl.substring(0, currentUrl.length() - 1);
			}
			System.out.println("New URL: " + currentUrl);
		}
		request.setAttribute("currentUrl", currentUrl);

		request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
	}
}
