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
import java.util.UUID;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DecimalFormat dcf = new DecimalFormat("#.##");
        req.setAttribute("dcf", dcf);
        List<Cart> cart_list = (List<Cart>) req.getSession().getAttribute("cart_list");
        String auth = (String) req.getSession().getAttribute("userID");
        CartDao pDao = null;
        try {
            pDao = new CartDao(DbCon.getConnection());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (auth != null){
            if(cart_list != null){
                for(Cart cart: cart_list){
                    cart.setUser_id(UUID.fromString(auth));
                }
                int update = pDao.updateCart(cart_list);
                System.out.println(update);
                cart_list = pDao.getCartProducts(auth);
                int i = 0;
                while(i + 1 < cart_list.size()){
                    if(cart_list.get(i).getBook_id().equals(cart_list.get(i+1).getBook_id())){
                        pDao.deleteCart(cart_list.get(i+1));
                        cart_list.remove(cart_list.get(i+1));
                    }
                    i++;
                }

                cart_list = pDao.getCartProducts(auth);

            } else {
                cart_list = pDao.getCartProducts(auth);
            }
            double total = pDao.getTotalCartPrice(cart_list);
            req.setAttribute("total", total);
            req.setAttribute("cart_list", cart_list);
        }
        if (auth == null && cart_list != null) {
            double total = pDao.getTotalCartPrice(cart_list);
            req.setAttribute("total", total);
            req.setAttribute("cart_list", cart_list);
        }
        req.getRequestDispatcher("/Cart.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
