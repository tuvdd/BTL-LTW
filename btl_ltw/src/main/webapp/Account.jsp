<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*" %>

<% User user = (User) request.getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang quản lý người dùng</title>
    <link rel="stylesheet" href="/resources/css/account.css">
</head>

<body>
    <jsp:include page="Menu.jsp"></jsp:include>
    <div class="account">
        <div class="container-account">
             <div class="avatar">
                <span>${user.getUsername().charAt(0)}</span>
            </div>
            <div class="user-info">
                <h2>Thông tin người dùng</h2>
                <p><strong>Họ tên:</strong>  ${user.getUsername()}</p>
                <p><strong>Email:</strong>  ${user.getEmail()}</p>
                <p><strong>Số điện thoại:</strong>  ${user.getPhonenum()}</p>
            </div>
            <form class="form-logout" action="/account" method="post">
                <button class="logout-btn">Đăng xuất</button>
            </form>
            <div style="clear:both;"></div>
        </div>
    </div>
    <div class="order-info">
            <div class="cart">
                <div class="order-info-item">
                    <h3>Đơn hàng trong giỏ hàng</h3>
                    <p>100</p>
                </div>
            </div>
            <div class="cart">
                <div class="order-info-item">
                    <h3>Đơn hàng đã mua</h3>
                    <p>100</p>
                </div>
            </div>
    </div>
    <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>