package servlets.cart_demo;

import models.Book;
import models.cart_demo.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
            System.out.println("testtttt");

            String id_raw = request.getParameter("id");
            System.out.println(id_raw);
            String quantity_raw = request.getParameter("quantity");
            int quantity = Integer.parseInt(quantity_raw);
            UUID id = UUID.fromString(id_raw);
            Cart cm = new Cart();
            String cart_id = UUID.randomUUID().toString();
            cm.setId(UUID.fromString(cart_id));
            BookRepo bookRepo = new BookRepo();
            Book book = bookRepo.getById(id);
            cm.setBook_id(id);
            System.out.println(id);
            cm.setQuantity(quantity);
            cm.setPromote_price(book.promote_price);
            cm.setImage(book.image);
            cm.setName(book.name);


            HttpSession session = request.getSession();
            List<Cart> cart_list = new ArrayList<>();
            cart_list = (List<Cart>) session.getAttribute("cart_list");
            String userID = (String) session.getAttribute("userID");
            if(userID == null){
                userID = "";
                System.out.println("nulllllllll");
            }
            System.out.println("USERID 1 = " + userID);
            CartDao cartDao = new CartDao(DbCon.getConnection());
            System.out.println("cart_list"+cart_list);
            if (cart_list == null) {
                cart_list = new ArrayList<>();
                int saveToCart = cartDao.saveToCart(cart_id,id_raw, userID, cm.getQuantity());
                System.out.println(saveToCart);
                cart_list.add(cm);

                System.out.println("test cart null");
            } else {
                boolean exist = false;
                for (Cart c : cart_list) {
                    System.out.println("for c id" + c.getBook_id().toString());
                    System.out.println(id_raw);
                    if (c.getBook_id().toString().equals(id_raw)) {
                        System.out.println("bang nhau");
                        exist = true;
                        System.out.println("Đã có trong giỏ");
                    }
                }
                if (!exist) {
                    int saveToCart = cartDao.saveToCart(cart_id, id_raw, userID, cm.getQuantity());
                    System.out.println(saveToCart);
                    cart_list.add(cm);

                }
            }
            System.out.println("end");
            System.out.println(cart_list);
            System.out.println(cm);
            System.out.println(1);
            session.setAttribute("cart_list", cart_list);
            System.out.println(2);
            System.out.println(cart_list);
            request.getRequestDispatcher("/detail?bookid="+id_raw).forward(request,response);
//            response.sendRedirect("/detail?bookid="+id_raw);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}