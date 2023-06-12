package servlets.cart_demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.cart_demo.Cart;
import repositories.cart_demo.CartDao;
import repositories.cart_demo.DbCon;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DecimalFormat dcf = new DecimalFormat("#.##");
        req.setAttribute("dcf", dcf);
        List<Cart> cart_list = (List<Cart>) req.getSession().getAttribute("cart-list");
        String auth = (String) req.getSession().getAttribute("userID");
        CartDao pDao = null;
        try {
            System.out.println(123);
            pDao = new CartDao(DbCon.getConnection());
            System.out.println(456);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cart_list = pDao.getCartProducts("ffe76ad1-10f8-4b45-9aa1-38ef3290a71b");
        System.out.println(cart_list);
        System.out.println(789);
        if (auth != null){
            cart_list = pDao.getCartProducts(auth);
        }
        if (cart_list != null) {
            double total = pDao.getTotalCartPrice(cart_list);
            req.setAttribute("total", total);
            req.setAttribute("cart_list", cart_list);
            System.out.println(total);
        }
        req.getRequestDispatcher("/cart2.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
