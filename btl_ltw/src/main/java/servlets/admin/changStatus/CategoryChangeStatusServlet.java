package servlets.admin.changStatus;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import repositories.CategoryRepo;

@WebServlet("/admin/category/change-status")
public class CategoryChangeStatusServlet extends HttpServlet {
    private CategoryRepo categoryRepository;

    public void init() {
        categoryRepository = new CategoryRepo();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryId = request.getParameter("id");

        try {
            Category category = categoryRepository.getById(UUID.fromString(categoryId));
            category.setStatus(!category.getStatus());
            int res = categoryRepository.update(category);

            if (res == 1) {
                request.getSession().setAttribute("message", "Đổi trạng thái thành công!");
                request.getSession().setAttribute("messageType", "success");
            } else {
                request.getSession().setAttribute("message", "Đổi trạng thái không thành công!");
                request.getSession().setAttribute("messageType", "error");
            }
            response.sendRedirect("/btl_ltw/admin/category");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().append("Lỗi khi thay đổi trạng thái của category!");
        }
    }
}