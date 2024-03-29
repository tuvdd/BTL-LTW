package servlets;

import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import models.Category;
import models.dtos.GetBooksDTO;
import repositories.BookRepo;
import repositories.CategoryRepo;
import servlets.Utilities.StringUtilities;

import java.io.IOException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	BookRepo bookRepo;
    	CategoryRepo categoryRepo;
    	bookRepo = new BookRepo();
		categoryRepo = new CategoryRepo();
		
		String page = request.getParameter("page");
    	String urldanhmuc = request.getParameter("urldanhmuc");
		String filter = request.getParameter("filter");
		String priceMin = request.getParameter("pricemin");
		String priceMax = request.getParameter("pricemax");
		List<Category> categories = categoryRepo.getAll(-1, -1, true);
        
        String searchQuery = request.getParameter("search");
        int pageSearch, sizeSearch = 9;
        if (page == null) {
        	pageSearch = 1;
        } else {
        	pageSearch = Integer.parseInt(page);
        }
		List<Book> books = new ArrayList<>();        
		books = bookRepo.getListSearchBooks(searchQuery, pageSearch, sizeSearch, urldanhmuc, filter, priceMin, priceMax);

		int totalRecords = bookRepo.getCountSearchBook(searchQuery, urldanhmuc);
        String currentUrl = "search?search=" + searchQuery;
        request.setAttribute("total", totalRecords);
        request.setAttribute("urldanhmuc", urldanhmuc);
		request.setAttribute("page", page);
		request.setAttribute("filter", filter);
		request.setAttribute("pricemin", priceMin);
		request.setAttribute("pricemax", priceMax);
		request.setAttribute("tenDanhMuc", categoryRepo.getByUrl(urldanhmuc).name);
		request.setAttribute("data", books);
		request.setAttribute("categories", categories);
		request.setAttribute("currentUrl", currentUrl);

        request.getRequestDispatcher("/ShoppingSearch.jsp").forward(request, response);
    }
}
