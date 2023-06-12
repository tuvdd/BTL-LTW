<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="models.cart_demo.Cart" %>
<%@ page import="repositories.cart_demo.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="repositories.CategoryRepo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if (cart_list != null) {
        CartDao pDao = new CartDao(DbCon.getConnection());
        cartProduct = pDao.getCartProducts(cart_list);
        double total = pDao.getTotalCartPrice(cart_list);
        request.setAttribute("total", total);
        request.setAttribute("cart_list", cart_list);
    }
%>--%>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    List<Cart> cart_list = (List<Cart>) request.getSession().getAttribute("cart-list");
    String auth = (String) request.getSession().getAttribute("userID");
    CartDao pDao = null;
    try {
        System.out.println(123);
        pDao = new CartDao(DbCon.getConnection());
        System.out.println(456);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    cart_list = pDao.getCartProducts("ffe76ad1-10f8-4b45-9aa1-38ef3290a71b");
    System.out.println(789);
    if (auth != null){
        cart_list = pDao.getCartProducts(auth);
    }
    if (cart_list != null) {
        double total = pDao.getTotalCartPrice(cart_list);
        session.setAttribute("total", total);
        session.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>E-Commerce Cart</title>
    <link rel="stylesheet" href="/resources/css/cart.css">

    <style type="text/css">

        .table tbody td{
            vertical-align: middle;
        }
        .btn-incre, .btn-decre{
            box-shadow: none;
            font-size: 25px;
        }
    </style>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="Menu.jsp"></jsp:include>

<div class="container my-3">
    <div class="d-flex py-3"><h3>Total Price: $ ${(total>0)?dcf.format(total):0} </h3> <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a></div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col">Buy Now</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (cart_list != null) {
                CategoryRepo categoryRepo = new CategoryRepo();
                for (Cart c : cart_list) {
        %>
        <tr>
            <td><%=c.getName()%></td>
            <td><%=categoryRepo.getById(c.getCategory_id()).getName()%></td>
            <td><%= dcf.format(c.getPromote_price())%></td>
            <td>
                <form action="order-now" method="post" class="form-inline">
                    <input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
                    <div class="form-group d-flex justify-content-between">
                        <a class="btn bnt-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a>
                        <input type="text" name="quantity" class="form-control"  value="<%=c.getQuantity()%>" readonly>
                        <a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">Buy</button>
                </form>
            </td>
            <td><a href="remove-from-cart?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Remove</a></td>
        </tr>

        <%
                }}%>
        </tbody>
    </table>
</div>

<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
