package servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import repositories.impls.CategoryRepo;
import repositories.interfaces.ICategoryRepo;

@WebServlet({ "/admin/category", "/admin/category/" })
public class CategoryServlet extends BaseServlet {
    private ICategoryRepo categoryRepo;

    public CategoryServlet() {
        super();
        categoryRepo = new CategoryRepo();
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

        	listCategories = categoryRepo.Gets("","");
            req.setAttribute("listCategories", listCategories);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	req.setAttribute("pageName", "category.jsp");
            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
        }
    }
}
