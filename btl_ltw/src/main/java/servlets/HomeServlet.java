package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import models.Category;
import models.Comment;
import repositories.BookRepo;
import repositories.CategoryRepo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ClientHome", urlPatterns = "/")
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String path = request.getRequestURI().substring(request.getContextPath().length());

		boolean isResource = path.lastIndexOf('.') > 0;

		if (isResource) {
			InputStream inputStream = getServletContext().getResourceAsStream(path);
			if (inputStream != null) {
				response.setContentType(getServletContext().getMimeType(path));
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.flush();
				outputStream.close();
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			 CategoryRepo repoC = new CategoryRepo();
			 BookRepo repoB = new BookRepo();
			 List<Book> listB, listRB = null;
			 List<Category> listC;
			 Book p;
			 List<Float> listAverageCommentRB = new ArrayList<>();
			 List<Integer> listNumberCommentRB = new ArrayList<>();
			 List<Float> listAverageCommentB = new ArrayList<>();
			 List<Integer> listNumberCommentB = new ArrayList<>();
			 
			 listRB = repoB.getAll(1, 4);
			 listB = repoB.get4LastestBooks();
			 listC = repoC.getAll(-1, -1);
			 p = listB.get(0);
			 for(Book x : listRB) {
				 listAverageCommentRB.add(repoB.getAverageComment(x.getId().toString()));
				 listNumberCommentRB.add(repoB.getNumberComments(x.getId().toString()));
			 }
			 for(Book x : listB) {
				 listAverageCommentB.add(repoB.getAverageComment(x.getId().toString()));
				 listNumberCommentB.add(repoB.getNumberComments(x.getId().toString()));
			 }
			 
			 request.setAttribute("listNumberCommentRB", listNumberCommentRB);
			 request.setAttribute("listAverageCommentRB", listAverageCommentRB);
			 request.setAttribute("listNumberCommentB", listNumberCommentB);
			 request.setAttribute("listAverageCommentB", listAverageCommentB);
			 request.setAttribute("listB", listB);
			 request.setAttribute("listC", listC);
			 request.setAttribute("listRB", listRB);
			 request.setAttribute("p", p);
			 request.getRequestDispatcher("Home.jsp").forward(request, response);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
}