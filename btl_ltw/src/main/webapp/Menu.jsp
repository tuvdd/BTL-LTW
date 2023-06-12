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
    <div class="logo"><a href="/">E-commerce Bookstore</a></div>
    <div class="search">
    <form method="get" action="search">
       <input name="search" type="text" placeholder="Search products" id="input">
    </form>
    </div>
    <div class="heading">
      <ul>
        <li><a href="/" class="under">Trang chủ</a></li>
        <li><a href="/shopping" class="under">Cửa hàng</a></li>
        <li><a href="#" class="under">Giới thiệu</a></li>
        <li><a href="/account" class="under">Tài khoản</a></li>
        <li><a href="/cart2.jsp" class="under">Giỏ hàng</a></li>
      </ul>
    </div>
  </header>
</body>
</html>
