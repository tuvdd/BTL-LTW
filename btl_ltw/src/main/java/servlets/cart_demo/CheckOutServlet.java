package servlets.cart_demo;


import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.OrderDetail;
import models.cart_demo.Cart;
import models.dtos.OrderFullDetail;
import repositories.OrderRepo;
import repositories.cart_demo.CartDao;
import repositories.cart_demo.DbCon;


@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp);
            String auth = (String) request.getSession().getAttribute("userID");
            System.out.println("Checkout" + auth);
            if(auth == null) {
                response.sendRedirect("/login");
                return;
            }
            CartDao cartDao = new CartDao(DbCon.getConnection());
            List<Cart> cart_list = cartDao.getCartProducts(auth);

            System.out.println(cart_list);

            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String name = request.getParameter("name");

            if (address != null && phone != null && name != null){
                OrderFullDetail orderFullDetail = cartToOrder(auth, cart_list,timestamp,1,address, phone,name);
                OrderRepo orderRepo = new OrderRepo();
                int temp = orderRepo.add_2(orderFullDetail);
                if(temp == 0){
                    System.out.println("error");
                }else {
                    for(Cart cart : cart_list){
                        cartDao.deleteCart(cart);
                    }
                    cart_list.clear();
                }
            }
            response.sendRedirect("/manageOrder");
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    public OrderFullDetail cartToOrder(String auth, List<Cart> cartList, Timestamp timestamp, int status, String address, String phone, String buyer){
        OrderFullDetail orderFullDetail = new OrderFullDetail();
        orderFullDetail.setId(UUID.randomUUID());
        orderFullDetail.setUser_id(UUID.fromString(auth));
        orderFullDetail.setStatus(status);
        orderFullDetail.setAddress(address);
        orderFullDetail.setPhonenum(phone);
        orderFullDetail.setCreated_time(timestamp);
        orderFullDetail.setBuyer_name(buyer);
        List<OrderDetail> list = new ArrayList<>();
        for (Cart c: cartList){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder_id(orderFullDetail.getId());
            orderDetail.setBook_id(c.getBook_id());
            orderDetail.setQuantity(c.getQuantity());
            orderDetail.setPrice(c.getPromote_price());

            list.add(orderDetail);
        }
        orderFullDetail.setOrderDetails(list);
        return orderFullDetail;
    }

}