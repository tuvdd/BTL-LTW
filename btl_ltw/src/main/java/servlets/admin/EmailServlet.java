package servlets.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ReceivedNewsEmail;
import repositories.impls.ReceivedNewsEmailRepo;
import repositories.interfaces.IReceivedNewsEmailRepo;

@WebServlet({ "/admin/email", "/admin/email/" })
public class EmailServlet extends BaseServlet {
    private IReceivedNewsEmailRepo emailRepo;

    public EmailServlet() {
        super();
        emailRepo = new ReceivedNewsEmailRepo();
    }

    private static final long serialVersionUID = 22;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        if (!ServletUtil.IsSessionExsited(req, resp)) {
            resp.sendRedirect("/btl_ltw/admin/login");
            return;
        }

        List<ReceivedNewsEmail> listCategories;
        try {

            listCategories = emailRepo.Gets("", "");
            req.setAttribute("listCategories", listCategories);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            req.setAttribute("pageName", "email.jsp");
            req.getRequestDispatcher("/admin/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            int res = emailRepo.Add(null);
            if (res == 2) {
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
            resp.sendRedirect("/btl_ltw/admin/email");
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
}

