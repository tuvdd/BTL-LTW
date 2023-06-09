package servlets;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import models.Category;
import repositories.BookRepo;
import repositories.CategoryRepo;

import java.io.IOException;
@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryRepo repoC = new CategoryRepo();
        List<Category> list = null;
        list = repoC.getAll(-1, -1);
        boolean[] chid = new boolean[list.size()+1];
        chid[0]=true;
        String[] pp={"Dưới 100k",
                "Từ 100k - 200k",
                "Từ 200k - 500k",
                "Từ 500k - 1 triệu",
                "Trên 1 triệu"};
        boolean[] pb=new boolean[pp.length+1];
        pb[0] = true;
        
        String searchQuery = request.getParameter("search");
        int page = 1, pageSize = 2;
        if (request.getParameter("searchpage") != null) {
        	page = Integer.parseInt(request.getParameter("searchpage"));
        }
		BookRepo bookRepo = new BookRepo();
		List<Book> books = new ArrayList<>();        
		books = bookRepo.searchBooks(searchQuery, page, pageSize);

		int totalRecords = bookRepo.getCountSearchBook(searchQuery);
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        
        request.setAttribute("totalRecords", totalRecords);
        request.setAttribute("searchQuery", searchQuery);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
		request.setAttribute("data", list);
        request.setAttribute("products", books);
        request.setAttribute("pp", pp);
        request.setAttribute("pb", pb);
        request.setAttribute("cid", 0);
        request.setAttribute("chid", chid);

        request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
    }
}
