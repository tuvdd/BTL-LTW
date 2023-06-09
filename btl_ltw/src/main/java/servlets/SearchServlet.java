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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        int page = Integer.parseInt(request.getParameter("page"));
        int size = 10;
        CategoryRepo repoC = new CategoryRepo();
        List<Category> list = null;
        list = repoC.getAll(-1, -1);
        boolean[] chid = new boolean[list.size() + 1];
        chid[0] = true;
        request.setAttribute("data", list);
        String[] pp = { "Dưới 100k",
                "Từ 100k - 200k",
                "Từ 200k - 500k",
                "Từ 500k - 1 triệu",
                "Trên 1 triệu" };
        boolean[] pb = new boolean[pp.length + 1];
        pb[0] = true;

        BookRepo bookRepo = new BookRepo();
        List<Book> books = new ArrayList<>();
        
        request.setAttribute("pp", pp);
        request.setAttribute("pb", pb);
        request.setAttribute("cid", 0);
        request.setAttribute("chid", chid);



        request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
    }
}
