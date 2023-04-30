package servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import services.impls.CategoryService;
import services.interfaces.ICategoryService;

@WebServlet({ "/admin/category", "/admin/category/" })
public class CategoryServlet extends BaseServlet {
    private ICategoryService categoryService;

    public CategoryServlet() {
        super();
        categoryService = new CategoryService();
    }

    private static final long serialVersionUID = 23;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	super.doGet(req, resp);
    	
        List<Category> listCategories;
        try {

        	listCategories = categoryService.Gets();
            req.setAttribute("listCategories", listCategories);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            req.getRequestDispatcher("/admin/category.jsp").forward(req, resp);
        }
    }
}
