package servlets;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import repositories.BookRepo;
import java.io.IOException;
@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        System.out.println(searchQuery);
		BookRepo bookRepo = new BookRepo();
		List<Book> books = new ArrayList<>();
		books = bookRepo.searchBooks(searchQuery, 1, 10);
		System.out.println(books.size());
        // Thực hiện tìm kiếm với truy vấn query
        
        // Lưu kết quả tìm kiếm vào một biến để truyền cho JSP
        request.setAttribute("products", books);
        
        // Forward đến trang JSP hiển thị kết quả
        request.getRequestDispatcher("/Shopping.jsp").forward(request, response);
    }
}
