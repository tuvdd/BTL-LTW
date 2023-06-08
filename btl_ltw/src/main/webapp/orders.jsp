<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>
<%@ page import="models.cart_demo.*" %>
<%@ page import="repositories.cart_demo.DbCon" %>
<%@ page import="repositories.cart_demo.OrderDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  DecimalFormat dcf = new DecimalFormat("#.##");
  request.setAttribute("dcf", dcf);
  User auth = (User) request.getSession().getAttribute("auth");
  List<Order> orders = null;
  if (auth != null) {
    request.setAttribute("person", auth);
    OrderDao orderDao  = new OrderDao(DbCon.getConnection());
    orders = orderDao.userOrders(auth.getId());
  }else{
    response.sendRedirect("login.jsp");
  }
  ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
  if (cart_list != null) {
    request.setAttribute("cart_list", cart_list);
  }

%>
<!DOCTYPE html>
<html>
<head>
  <title>E-Commerce Cart</title>
  <link rel="stylesheet" href="/resources/css/orders.css">
</head>
<body>
<jsp:include page="Menu.jsp"></jsp:include>
<div class="container">
  <div class="card-header my-3">All Orders</div>
  <table class="table table-light">
    <thead>
    <tr>
      <th scope="col">Date</th>
      <th scope="col">Name</th>
      <th scope="col">Category</th>
      <th scope="col">Quantity</th>
      <th scope="col">Price</th>
      <th scope="col">Cancel</th>
    </tr>
    </thead>
    <tbody>

    <%
      if(orders != null){
        for(Order o:orders){%>
    <tr>
      <td><%=o.getDate() %></td>
      <td><%=o.getName() %></td>
      <td><%=o.getCategory() %></td>
      <td><%=o.getQunatity() %></td>
      <td><%=dcf.format(o.getPrice()) %></td>
      <td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderId()%>">Cancel Order</a></td>
    </tr>
    <%}
    }
    %>

    </tbody>
  </table>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
