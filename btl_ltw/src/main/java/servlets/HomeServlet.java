package servlets;

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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

@WebServlet(name = "ClientHome", urlPatterns = "/")
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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
			List<Book> listRB, listLasterBook, listFeaturedBook = new ArrayList<>();
			Map<Book, Float> listAverageBook = new HashMap<>();
			List<Category> listC;
			Book p;
			List<Float> listAverageCommentRB = new ArrayList<>();
			List<Integer> listNumberCommentRB = new ArrayList<>();
			List<Float> listAverageCommentB = new ArrayList<>();
			List<Integer> listNumberCommentB = new ArrayList<>();

			listRB = repoB.getListFeaturedBook();
			listLasterBook = repoB.get4LastestBooks();
			listC = repoC.getAll(-1, -1);
			for (Book x : listRB) {
				listNumberCommentRB.add(repoB.getNumberComments(x.getId().toString()));
				listAverageBook.put(x, repoB.getAverageComment(x.getId().toString()));
			}
			List<Map.Entry<Book, Float>> list = new ArrayList<>(listAverageBook.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<Book, Float>>() {

				@Override
				public int compare(Entry<Book, Float> o1, Entry<Book, Float> o2) {
					// TODO Auto-generated method stub
					return o2.getValue().compareTo(o1.getValue());
				}
				
			});
			for(int i = 0; i < 4; i++) {
				listFeaturedBook.add(list.get(i).getKey());
				listAverageCommentRB.add(list.get(i).getValue());
			}
			for (Book x : listLasterBook) {
				listAverageCommentB.add(repoB.getAverageComment(x.getId().toString()));
				listNumberCommentB.add(repoB.getNumberComments(x.getId().toString()));
			}
			p = listFeaturedBook.get(0);

			request.setAttribute("listNumberCommentRB", listNumberCommentRB);
			request.setAttribute("listAverageCommentRB", listAverageCommentRB);
			request.setAttribute("listNumberCommentB", listNumberCommentB);
			request.setAttribute("listAverageCommentB", listAverageCommentB);
			request.setAttribute("listLasterBook", listLasterBook);
			request.setAttribute("categories", listC);
			request.setAttribute("listFeaturedBook", listFeaturedBook);
			request.setAttribute("p", p);
			request.getRequestDispatcher("Home.jsp").forward(request, response);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}