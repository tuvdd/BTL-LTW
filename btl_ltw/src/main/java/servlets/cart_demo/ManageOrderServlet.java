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

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ManageOrderServlet", urlPatterns = "/manageOrder")
public class ManageOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String auth = (String) req.getSession().getAttribute("userID");
        OrderRepo orderRepo = new OrderRepo();
        OrderFullDetail orderFullDetail = orderRepo.getOrderFullDetailById(UUID.fromString("3e1791f0-2843-446e-98c7-8663fdcbc536"));
        List<OrderFullDetail> orderFullDetailList = new ArrayList<>();
        orderFullDetailList.add(orderFullDetail);
        req.setAttribute("order_list", orderFullDetailList);
        req.getRequestDispatcher("/manageOrder.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
