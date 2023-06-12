<%--
  Created by IntelliJ IDEA.
  User: ductu1w
  Date: 5/11/2023
  Time: 5:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="/resources/css/menu.css">
</head>
<body>
  <header>
    <div class="logo"><a href="/btl_ltw">E-commerce Bookstore</a></div>
    <div class="search">
    <form method="get" action="search">
       <input name="search" type="text" placeholder="Search products" id="input">
    </form>
    </div>
    <div class="heading">
      <ul>
        <li><a href="/" class="under">HOME</a></li>
        <li><a href="/shopping" class="under">SHOPPING</a></li>
        <li><a href="#" class="under">CONTACT</a></li>
        <li><a href="/account" class="under">ACCOUNT</a></li>
        <li><a href="/cart2.jsp" class="under">CART</a></li>
      </ul>
    </div>
  </header>
</body>
</html>
