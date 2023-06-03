package servlets.admin.delete;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.CategoryRepo;

@WebServlet("/admin/category/delete")
public class DeleteCategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category ID parameter is missing or empty");
            return;
        }

        try {
            UUID id = UUID.fromString(idParam);
            CategoryRepo bookRepo = new CategoryRepo();
            int rowsAffected = bookRepo.delete(id);
            if (rowsAffected == 0) {
                request.getSession().setAttribute("message", "Xóa không thành công!");
                request.getSession().setAttribute("messageType", "error");
            } else {
                request.getSession().setAttribute("message", "Xóa thành công!");
                request.getSession().setAttribute("messageType", "success");
            }

            response.sendRedirect(request.getContextPath() + "/admin/category");
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category ID parameter is not a valid UUID");
        }
    }
}