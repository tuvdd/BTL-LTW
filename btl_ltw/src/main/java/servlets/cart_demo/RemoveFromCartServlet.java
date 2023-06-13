package servlets.cart_demo;


import models.cart_demo.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.cart_demo.CartDao;
import repositories.cart_demo.DbCon;


@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String bookId = request.getParameter("id");
            CartDao cartDao = new CartDao(DbCon.getConnection());
            if (bookId != null) {
                List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart-list");
                if (cart_list != null) {
                    for (Cart c : cart_list) {
                        if (c.getId().toString().equals(bookId)) {
                            cart_list.remove(c);
                            System.out.println("Da xoa" + cartDao.deleteCart(c));
                            break;
                        }
                    }
                }
                response.sendRedirect("cart2.jsp");

            } else {
                response.sendRedirect("cart2.jsp");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}