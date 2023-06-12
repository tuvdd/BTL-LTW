package servlets.cart_demo;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.cart_demo.*;


@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            String id = request.getParameter("id");
            List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart-list");
            System.out.println("+");
            System.out.println(cart_list);
            System.out.println("+");

            if (action != null && id != null) {
                if (action.equals("inc")) {
                    for (Cart c : cart_list) {
                        System.out.println(cart_list);
                        System.out.println(id);
                        System.out.println(c.getId());


                        if (c.getId().toString().equals(id)) {
                            System.out.println("0000");
                            int quantity = c.getQuantity();
                            quantity++;
                            c.setQuantity(quantity);
                            System.out.println(c.getQuantity());
//                            response.sendRedirect("/cart2.jsp");
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
//                    response.sendRedirect("cart2.jsp");
                }
            }
//            else {
//                response.sendRedirect("cart2.jsp");
//            }
//            request.getRequestDispatcher("/cart2.jsp").forward(request,response);
            for (Cart c : cart_list) {
                System.out.println(c.getQuantity());
            }


            request.setAttribute("cart-list", cart_list);
            response.sendRedirect("cart2.jsp");
        }
    }

}