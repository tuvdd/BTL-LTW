package servlets.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import models.Category;
import repositories.impls.BookImageRepo;
import repositories.impls.BookRepo;
import repositories.impls.CategoryRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet({ "/client/detail", "/client/detail/" })
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookRepo repoB = new BookRepo();
        Book book;
        try {
            book = repoB.Get(UUID.fromString("036af2da-e0c3-41f4-8609-0e1e115f2095"));
            System.out.println(0);

            req.setAttribute("book",book);
            System.out.println(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            req.getRequestDispatcher("/client/Detail.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
