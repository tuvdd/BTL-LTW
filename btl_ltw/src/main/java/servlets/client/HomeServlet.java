package servlets.client;

import models.Book;
import models.Category;
import repositories.BookRepo;
import repositories.CategoryRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CategoryRepo repoC = new CategoryRepo();
		BookRepo repoB = new BookRepo();
		List<Book> listB, listRB = null;
		List<Category> listC;
		Book p;
		listB = repoB.getAll(1, 20);
		listC = repoC.getAll(-1, -1);

		p = listB.get(0);
		req.setAttribute("listB", listB);
		req.setAttribute("listC", listC);
		req.setAttribute("listRB", listRB);
		req.setAttribute("p", p);
		req.getRequestDispatcher("/client/Home.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}