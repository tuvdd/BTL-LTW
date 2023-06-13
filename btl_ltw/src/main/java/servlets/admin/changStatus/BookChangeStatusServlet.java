package servlets.admin.changStatus;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import repositories.BookRepo;

@WebServlet("/admin/book/change-status")
public class BookChangeStatusServlet extends HttpServlet {
    private BookRepo bookRepository;

    public void init() {
        bookRepository = new BookRepo();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookId = request.getParameter("id");
        if (bookId == null || bookId.isEmpty()) {
        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ID parameter");
			return;
		}
        try {
            Book book = bookRepository.getById(UUID.fromString(bookId));
            book.setStatus(!book.getStatus());
            int res = bookRepository.update(book);

            if (res == 1) {
                request.getSession().setAttribute("message", "Đổi trạng thái thành công!");
                request.getSession().setAttribute("messageType", "success");
            } else {
                request.getSession().setAttribute("message", "Đổi trạng thái không thành công!");
                request.getSession().setAttribute("messageType", "error");
            }
            response.sendRedirect("/admin/book");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().append("Lỗi khi thay đổi trạng thái của book!");
        }
    }
}