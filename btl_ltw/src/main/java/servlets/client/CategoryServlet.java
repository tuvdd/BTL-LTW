package servlets.client;

import models.Book;
import models.Category;
import repositories.impls.BookRepo;
import repositories.impls.CategoryRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "client/category", value = "/client/category")
public class CategoryServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        CategoryRepo repoC = new CategoryRepo();
        BookRepo repoB = new BookRepo();
        List<Book> listB = null;
        List<Category> listC = null;
        try {
            listB = repoB.Gets("","");
            listC = repoC.Gets("", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            req.setAttribute("listB", listB);
            req.setAttribute("listC", listC);
            req.getRequestDispatcher("/client/Home.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}