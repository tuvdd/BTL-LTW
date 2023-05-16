package servlets.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Book;
import models.Category;
import models.dtos.AdminBookView;
import repositories.impls.BookRepo;
import repositories.impls.CategoryRepo;
import repositories.interfaces.IBookRepo;
import repositories.interfaces.ICategoryRepo;

@WebServlet({ "/admin/book", "/admin/book/" })
@MultipartConfig(location = "uploads", fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class BookServlet extends BaseServlet {
    private IBookRepo bookRepo;
    private ICategoryRepo categoryRepo;

    public BookServlet() {
        super();
        bookRepo = new BookRepo();
        categoryRepo = new CategoryRepo();
    }

    private static final long serialVersionUID = 22;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        if (!ServletUtil.IsSessionExsited(req, resp)) {
            resp.sendRedirect("/btl_ltw/admin/login");
            return;
        }

        List<AdminBookView> listAdminBookViews;
        List<Category> listCategories;
        try {
            listAdminBookViews = bookRepo.GetsAdminBookView("");
            listCategories = categoryRepo.Gets("", "");
            req.setAttribute("listAdminBookViews", listAdminBookViews);
            req.setAttribute("listCategories", listCategories);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            req.setAttribute("pageName", "book.jsp");
            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("image");
        byte[] image;
        try (InputStream inputStream = filePart.getInputStream()) {
            image = inputStream.readAllBytes();
        }

        UUID admin_id = (UUID) req.getSession().getAttribute("id");

        String name = req.getParameter("name");
        String author = req.getParameter("author");
        int release_year = Integer.parseInt(req.getParameter("release_year"));
        UUID category_id = UUID.fromString(req.getParameter("category_id"));
        double price = Double.parseDouble(req.getParameter("price"));
        double promote_price = Double.parseDouble(req.getParameter("promote_price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        String sub_description = req.getParameter("sub_description");
        int status = Integer.parseInt(req.getParameter("status"));
        Date create_time = new Date();
        UUID create_by = admin_id;
        Date last_update_time = new Date();
        UUID last_update_by = admin_id;

        Book book = new Book();
        book.set(null, name, image, author, release_year, category_id, price, promote_price, quantity, description,
                sub_description, status, create_time, create_by, last_update_time, last_update_by);

        try {
            int res = bookRepo.Add(book);
            if (res == 1) {
                req.getSession().setAttribute("message", "Thêm mới thành công!");
                req.getSession().setAttribute("messageType", "success");
            } else {
                req.getSession().setAttribute("message", "Thêm mới không thành công!");
                req.getSession().setAttribute("messageType", "error");
            }
        } catch (SQLException e) {
            req.getSession().setAttribute("message", e.getMessage());
            req.getSession().setAttribute("messageType", "error");
        } finally {
            resp.sendRedirect("/btl_ltw/admin/book");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
}
