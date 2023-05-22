package servlets.admin;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import repositories.CategoryRepo;

@WebServlet({ "/admin/category", "/admin/category/" })
public class CategoryServlet extends BaseServlet {
	private CategoryRepo categoryRepo;

	public CategoryServlet() {
		super();
		categoryRepo = new CategoryRepo();
	}

	private static final long serialVersionUID = 23;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);

		if (!ServletUtil.IsSessionExsited(req, resp)) {
			resp.sendRedirect("/btl_ltw/admin/login");
			return;
		}

		List<Category> listCategories;
		try {

			listCategories = categoryRepo.getAll(1, 10);
			req.setAttribute("listCategories", listCategories);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			req.setAttribute("pageName", "category.jsp");
			req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (!ServletUtil.IsSessionExsited(req, resp)) {
			resp.sendRedirect("/btl_ltw/admin/login");
			return;
		}

		String id = req.getParameter("id");
		String name = req.getParameter("name");

		Category category = new Category();
		category.setName(name);
		category.setStatus(true);
		if (id != null && id != "") {
			category.id = UUID.fromString(id);
			try {
				int res = categoryRepo.update(category);
				if (res == 1) {
					req.getSession().setAttribute("message", "Thêm mới thành công!");
					req.getSession().setAttribute("messageType", "success");
				} else {
					req.getSession().setAttribute("message", "Thêm mới không thành công!");
					req.getSession().setAttribute("messageType", "error");
				}
			} catch (Exception e) {
				req.getSession().setAttribute("message", e.getMessage());
				req.getSession().setAttribute("messageType", "error");
			} finally {
				resp.sendRedirect("/btl_ltw/admin/category");
			}
			return;
		}
		try {
			int res = categoryRepo.add(category);
			if (res == 1) {
				req.getSession().setAttribute("message", "Thêm mới thành công!");
				req.getSession().setAttribute("messageType", "success");
			} else {
				req.getSession().setAttribute("message", "Thêm mới không thành công!");
				req.getSession().setAttribute("messageType", "error");
			}
		} catch (Exception e) {
			req.getSession().setAttribute("message", e.getMessage());
			req.getSession().setAttribute("messageType", "error");
		} finally {
			resp.sendRedirect("/btl_ltw/admin/category");
		}
	}
}
