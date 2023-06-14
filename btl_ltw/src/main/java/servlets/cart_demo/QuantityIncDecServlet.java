package servlets.cart_demo;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.cart_demo.*;
import repositories.cart_demo.CartDao;
import repositories.cart_demo.DbCon;


@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            String id = request.getParameter("id");
            List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart_list");

            if (action != null && id != null) {
                if (action.equals("inc")) {
                    for (Cart c : cart_list) {

                        if (c.getId().toString().equals(id)) {
                            int quantity = c.getQuantity();
                            quantity++;
                            c.setQuantity(quantity);
                        }
                    }
                }

                if (action.equals("dec")) {
                    for (Cart c : cart_list) {
                        if (c.getId().toString().equals(id) && c.getQuantity() > 1) {
                            int quantity = c.getQuantity();
                            quantity--;
                            c.setQuantity(quantity);
                            break;
                        }
                    }
                }
            }

            CartDao pDao = new CartDao(DbCon.getConnection());
            double total = pDao.getTotalCartPrice(cart_list);
            request.setAttribute("cart_list", cart_list);
            request.setAttribute("total", total);
            response.sendRedirect("/cart");
//            request.getRequestDispatcher("/Cart.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}