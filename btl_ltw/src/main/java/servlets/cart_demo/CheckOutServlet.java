package servlets.cart_demo;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.cart_demo.*;
import models.dtos.OrderFullDetail;
import repositories.OrderRepo;
import repositories.cart_demo.DbCon;


@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try{
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = new Date();
//            List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart-list");
//            String auth = (String) request.getSession().getAttribute("userID");
//            OrderDao orderDao = new OrderDao(DbCon.getConnection());
//            if(cart_list != null && auth!=null) {
//                OrderFullDetail orderFullDetail = orderDao.cartToOrder(cart_list, auth, "123 nguyen trai");
//                OrderRepo orderRepo = new OrderRepo();
//                int temp = orderRepo.add(orderFullDetail);
//                if(temp == 0){
//                    System.out.println("error");
//                }
//                cart_list.clear();
//                response.sendRedirect("orders.jsp");
//            }else {
//                if(auth==null) {
//                    response.sendRedirect("/btl_ltw/user/login");
//                    return;
//                }
//                response.sendRedirect("cart.jsp");
//            }
//        } catch (ClassNotFoundException|SQLException e) {
//
//            e.printStackTrace();
//        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}