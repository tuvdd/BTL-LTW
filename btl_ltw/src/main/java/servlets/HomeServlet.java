package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
			// CategoryRepo repoC = new CategoryRepo();
			// BookRepo repoB = new BookRepo();
			// List<Book> listB, listRB = null;
			// List<Category> listC;
			// Book p;
			// listB = repoB.getAll(1, 20);
			// listC = repoC.getAll(-1, -1);

			// p = listB.get(0);
			// request.setAttribute("listB", listB);
			// request.setAttribute("listC", listC);
			// request.setAttribute("listRB", listRB);
			// request.setAttribute("p", p);
			request.getRequestDispatcher("Home.jsp").forward(request, response);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}