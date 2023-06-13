package servlets.cart_demo;

import models.Book;
import models.cart_demo.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repositories.BookRepo;
import repositories.cart_demo.CartDao;
import repositories.cart_demo.DbCon;


@WebServlet(name = "AddToCartServlet", urlPatterns = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
//        	out.print("add to cart servlet");

            ArrayList<Cart> cartList = new ArrayList<>();
            String id_raw = request.getParameter("id");
            UUID id = UUID.fromString(id_raw);
            Cart cm = new Cart();
            BookRepo bookRepo = new BookRepo();
            Book book = bookRepo.getById(id);
            cm.setBook_id(id);
            System.out.println(id);
            cm.setQuantity(1);

            HttpSession session = request.getSession();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
            String userID = (String) session.getAttribute("userID");
            System.out.println("USERID 1 = ");
            if(userID == null){
                userID = "";
            }
            CartDao cartDao = new CartDao(DbCon.getConnection());
            int saveToCart = cartDao.saveToCart(id_raw, "ffe76ad1-10f8-4b45-9aa1-38ef3290a71b", cm.getQuantity());
            System.out.println(saveToCart);

            if (cart_list == null) {
                cartList.add(cm);
                request.setAttribute("cart-list", cartList);
                response.sendRedirect("/danh-sach-san-pham");
            } else {
                cartList = cart_list;

                boolean exist = false;
                for (Cart c : cart_list) {
                    if (c.getId() == id) {
                        exist = true;
                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='/cart'>GO to Cart Page</a></h3>");
                    }
                }

                if (!exist) {
                    cartList.add(cm);
                    response.sendRedirect("/danh-sach-san-pham");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}