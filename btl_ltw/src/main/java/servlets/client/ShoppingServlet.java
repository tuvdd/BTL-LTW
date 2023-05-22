package servlets.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import models.Category;
import repositories.impls.BookRepo;
import repositories.impls.CategoryRepo;
import repositories.impls.DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet({ "/client/shopping", "/client/shopping/" })
public class ShoppingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        DAO d = new DAO();
        CategoryRepo repoC = new CategoryRepo();
        BookRepo repoB = new BookRepo();
        List<Book> news, olds = null;
        List<Category> list = null;
        try {
            list = repoC.Gets("", "");
            news = repoB.Gets("","");
            olds = repoB.Gets("","");
            System.out.println("test");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[] pp={"Dưới 100k",
                "Từ 100k - 200k",
                "Từ 200k - 500k",
                "Từ 500k - 1 triệu",
                "Trên 1 triệu"};
        boolean[] pb=new boolean[pp.length+1];
        pb[0] = true;
        boolean[] chid = new boolean[list.size()+1];
        chid[0]=true;
        req.setAttribute("data", list);
        req.setAttribute("news", news);
        req.setAttribute("olds", olds);
        req.setAttribute("pp", pp);
        req.setAttribute("pb", pb);
        req.setAttribute("cid", 0);
        req.setAttribute("chid", chid);
        req.getRequestDispatcher("/client/Shopping.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
