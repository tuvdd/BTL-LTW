package servlets.client;

import models.Book;
import models.BookImage;
import models.Category;
import repositories.impls.BookImageRepo;
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

@WebServlet({ "/client/home", "/client/home/" })
public class HomeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CategoryRepo repoC = new CategoryRepo();
        BookRepo repoB = new BookRepo();
        BookImageRepo repoBI = new BookImageRepo();
        List<Book> listB, listRB = null;
        List<Category> listC;
        Book p;
        try {
            listB = repoB.Gets("","");
            listC = repoC.Gets("", "");
            System.out.println(listB);
            System.out.println(listC);
            System.out.println(listC.get(1).getName());
            System.out.println(listC.get(1).getId());
            System.out.println(0);

            p = listB.get(0);
            System.out.println(1);
            req.setAttribute("listB", listB);
            req.setAttribute("listC", listC);
            req.setAttribute("listRB", listRB);
            req.setAttribute("p",p);
            System.out.println(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            req.getRequestDispatcher("/client/Home.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}