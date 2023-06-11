<%@ page import="repositories.cart_demo.DbCon" %>
<%@ page import="repositories.cart_demo.CartDao" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="models.cart_demo.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="repositories.CategoryRepo" %>
<%@ page import="models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  DecimalFormat dcf = new DecimalFormat("#.##");
  request.setAttribute("dcf", dcf);
  String auth = (String) request.getSession().getAttribute("userID");
//  if (auth != null) {
//    request.setAttribute("person", auth);
//  }
  List<Cart> cart_list = (List<Cart>) session.getAttribute("cart-list");
  CartDao pDao = new CartDao(DbCon.getConnection());

  if (cart_list == null) {
    cart_list = pDao.getCartProducts("ffe76ad1-10f8-4b45-9aa1-38ef3290a71b");
  session.setAttribute("cart-list", cart_list);
 }
  double total = pDao.getTotalCartPrice(cart_list);
  session.setAttribute("total", total);
/*  DecimalFormat dcf = new DecimalFormat("#.##");
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
  }*/
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
  <link rel = stylesheet href="/resources/css/cart.css">

</head>
<body>


<header id="site-header">
  <div class="container">
    <h1>Shopping cart </h1>
  </div>
</header>

<div class="container">

  <section id="cart">
      <%
            if (cart_list != null) {
                for (Cart c : cart_list) {
        %>

<%--  <c:forEach items="${cart_list}" var="c">--%>
    <article class="product">
      <header>
        <input type="hidden" name="id" value="<%=c.getId() %>" class="form-input">
        <a class="remove">
          <a href="#"><img src="data:image/png;base64,<%=c.getImageBase64() %>" alt="" /></a>
            <h3><a href="remove-from-cart?id=<%=c.getId() %>" >Remove product</a></h3>

<%--          <h3 href="remove-from-cart?id=<%=c.getId() %>"><span>Remove product</span> </h3>--%>
        </a>
      </header>

      <div class="content">

        <h1><%=c.getName()%></h1>
<%--        <%=c.getSub_description()%>--%>

      </div>

      <footer class="content">
        <a href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><span class="qt-minus">-</span></a>
        <span class="qt"><%=c.getQuantity()%></span>
        <a href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><span class="qt-minus">+</span></a>

<%--        <span class="qt-plus" href="quantity-inc-dec?action=inc&id=${c.getId()}">+</span>--%>

        <h2 class="full-price">
          <%=c.getPromote_price()*c.getQuantity()%> VNĐ
<%--          ${c.promote_price*c.quantity} VNĐ--%>
        </h2>

        <h2 class="price">
          <%=c.getPromote_price()%> VNĐ

<%--        ${c.promote_price} VNĐ--%>
        </h2>
      </footer>
    </article>
            <%
                }}%>

<%--      </c:forEach>--%>
<%--    <article class="product">--%>
<%--      <header>--%>
<%--        <a class="remove">--%>
<%--          <img src="http://www.astudio.si/preview/blockedwp/wp-content/uploads/2012/08/3.jpg" alt="">--%>

<%--          <h3>Remove product</h3>--%>
<%--        </a>--%>
<%--      </header>--%>

<%--      <div class="content">--%>

<%--        <h1>Lorem ipsum dolor</h1>--%>

<%--        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Soluta, numquam quis perspiciatis ea ad omnis provident laborum dolore in atque.--%>

<%--        <div title="You have selected this product to be shipped in the color red." style="top: 0" class="color red"></div>--%>
<%--        <div title="You have selected this product to be shipped sized Small."  style="top: 43px" class="type small">Small</div>--%>
<%--      </div>--%>

<%--      <footer class="content">--%>

<%--        <span class="qt-minus">-</span>--%>
<%--        <span class="qt">1</span>--%>
<%--        <span class="qt-plus">+</span>--%>

<%--        <h2 class="full-price">--%>
<%--          79.99€--%>
<%--        </h2>--%>

<%--        <h2 class="price">--%>
<%--          79.99€--%>
<%--        </h2>--%>
<%--      </footer>--%>
<%--    </article>--%>

<%--    <article class="product">--%>
<%--      <header>--%>
<%--        <a class="remove">--%>
<%--          <img src="http://www.astudio.si/preview/blockedwp/wp-content/uploads/2012/08/5.jpg" alt="">--%>

<%--          <h3>Remove product</h3>--%>
<%--        </a>--%>
<%--      </header>--%>

<%--      <div class="content">--%>

<%--        <h1>Lorem ipsum dolor ipsdu</h1>--%>

<%--        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Soluta, numquam quis perspiciatis ea ad omnis provident laborum dolore in atque.--%>

<%--        <div title="You have selected this product to be shipped in the color blue." style="top: 0" class="color blue"></div>--%>
<%--        <div style="top: 43px" class="type small">Large</div>--%>
<%--      </div>--%>

<%--      <footer class="content">--%>

<%--        <span class="qt-minus">-</span>--%>
<%--        <span class="qt">3</span>--%>
<%--        <span class="qt-plus">+</span>--%>

<%--        <h2 class="full-price">--%>
<%--          53.99€--%>
<%--        </h2>--%>

<%--        <h2 class="price">--%>
<%--          17.99€--%>
<%--        </h2>--%>
<%--      </footer>--%>
<%--    </article>--%>

<%--  </section>--%>

<%--</div>--%>

<footer id="site-footer">
  <div class="container clearfix">

    <div class="left">
        <h1 class="total">Total: <span>${total}</span>VNĐ</h1>
        <a class="btn">Checkout</a>
    </div>


  </div>
</footer>


</body>
</html>