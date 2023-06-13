package servlets.cart_demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.OrderDetail;
import models.dtos.OrderFullDetail;
import repositories.OrderRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "OrderDetailServlet", urlPatterns = "/orderdetail")
public class OrderDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderRepo orderRepo = new OrderRepo();
        String orderID = req.getParameter("orderID");
        OrderFullDetail orderFullDetail = orderRepo.getOrderFullDetailById(UUID.fromString(orderID));
        List<OrderDetail> orderDetailList = orderFullDetail.getOrderDetails();
        double total = 0;
        for(OrderDetail orderDetail: orderDetailList){
            total += orderDetail.getPrice()*orderDetail.getQuantity();
        }
        req.setAttribute("order_detail_list", orderDetailList);
        req.setAttribute("total", total);
        req.getRequestDispatcher("/orderDetail.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
