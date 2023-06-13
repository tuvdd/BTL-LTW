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
		List<Category> categories = categoryRepo.getAll(-1, -1, true);
        
        String searchQuery = request.getParameter("search");
        int pageSearch, sizeSearch = 2;
        if (page == null) {
        	pageSearch = 1;
        } else {
        	pageSearch = Integer.parseInt(page);
        }
		List<Book> books = new ArrayList<>();        
		books = bookRepo.searchBooks(searchQuery, pageSearch, sizeSearch);

		int totalRecords = bookRepo.getCountSearchBook(searchQuery);
        String currentUrl = "search?search=" + searchQuery;
        
        request.setAttribute("total", totalRecords);
		request.setAttribute("page", page);
		request.setAttribute("data", books);
		request.setAttribute("categories", categories);
		request.setAttribute("currentUrl", currentUrl);

        request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
    }
}
