package models.dtos;

import java.util.List;

import models.Order;
import models.OrderDetail;

public class OrderFullDetail extends Order {
    private List<OrderDetail> orderDetails;

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    
}