package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import repositories.BookRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name="ClientDetail",urlPatterns = "/san-pham" )
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookRepo repoB = new BookRepo();
        Book book;
        String bookid = req.getParameter("bookid");
        book = repoB.getById(UUID.fromString(bookid));
        System.out.println(0);

        req.setAttribute("book",book);
        System.out.println(2);
        req.getRequestDispatcher("/client/Detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
