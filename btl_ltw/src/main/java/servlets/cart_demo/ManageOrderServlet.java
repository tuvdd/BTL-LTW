package servlets.cart_demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.cart_demo.Cart;
import models.dtos.OrderFullDetail;
import repositories.OrderRepo;
import repositories.cart_demo.CartDao;
import repositories.cart_demo.DbCon;
import repositories.cart_demo.OrderDao;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

@WebServlet(name = "ManageOrderServlet", urlPatterns = "/manageOrder")
public class ManageOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderRepo orderRepo = new OrderRepo();

        String auth = (String) req.getSession().getAttribute("userID");
        String id_raw = req.getParameter("id");
        String cancel = req.getParameter("cancel");
        String status_raw = req.getParameter("status");
        if(id_raw != null && cancel != null && status_raw != null){
            int status = Integer.parseInt(status_raw);
            if(status < 4){
                switch (status){
                    case 0,1,2: status = 5;break;
                    case 3: status = 6; break;
                }
                int updateStatus = orderRepo.updateStatus(UUID.fromString(id_raw), status);
                System.out.println("Update :" + updateStatus);
            }
        }
        List<String> orderListId = new ArrayList<>();
        List<OrderFullDetail> orderFullDetailList = new ArrayList<>();
        try {
            OrderDao orderDao = new OrderDao(DbCon.getConnection());
            orderListId = orderDao.getOrderListId(auth);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(String orderId : orderListId){
            OrderFullDetail orderFullDetail = orderRepo.getOrderFullDetailById(UUID.fromString(orderId));
            orderFullDetailList.add(orderFullDetail);
        }
        Collections.sort(orderFullDetailList, new Comparator<OrderFullDetail>() {
            @Override
            public int compare(OrderFullDetail o1, OrderFullDetail o2) {
                return o1.created_time.compareTo(o2.created_time);
            }
        });
        Collections.reverse(orderFullDetailList);


        req.setAttribute("order_list", orderFullDetailList);
        req.getRequestDispatcher("/manageOrder.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
