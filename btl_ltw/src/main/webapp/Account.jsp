<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*" %>

<% User user = (User) request.getAttribute("user"); %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Trang quản lý người dùng</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            .account {
                background-color: grey;
                color: #fff;
                padding: 10px;
            }

            .container {
                max-width: 80%;
                margin: 20px auto;
                padding: 20px;
                display: flex;
                /* Sử dụng Flexbox */
                align-items: center;
                /* Căn chỉnh phần tử theo chiều dọc */

            }

            .avatar {
                width: 200px;
                height: 200px;
                border-radius: 50%;
                background-color: green;
                margin-right: 40px;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .avatar span {
                position: absolute;
                font-size: 5em;
                color: #fff;
            }

            .user-info {
                float: left;
                display: flex;
                flex-direction: column;
            }

            .user-info h2 {
                margin-top: 0;
            }

            .user-info p {
                margin-bottom: 10px;
            }

            .form-logout {
                margin-left: auto;
            }

            .logout-btn {
                background-color: #f44336;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .order-info {
                width: 80%;
                display: flex;
                align-items: center;
                justify-content: center;
                margin-top: 20px;
                margin: auto;
                margin-top: 50px;
                margin-bottom: 50px;
            }

            .cart {
                width: 350px;
                height: 400px;
                padding: 50px 50px;
                border-radius: 5px;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .order-info-item {
                width: 100%;
                height: 100%;
                border-radius: 30px;
                background-color: olive;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .order-info-item h3 {
                text-align: center;
                margin-top: 50px;
                padding-left: 10px;
                padding-right: 10px;
            }

            .order-info-item p {
                width: 100px;
                height: 100px;
                text-align: center;
                font-size: 2em;
                margin-top: 40px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
        <div class="account">
            <div class="container">
                <div class="avatar">
                    <span>${user.getUsername().charAt(0)}</span>
                </div>
                <div class="user-info">
                    <h2>Thông tin người dùng</h2>
                    <p><strong>Họ tên:</strong>  ${user.getUsername()}</p>
                    <p><strong>Email:</strong>  ${user.getEmail()}</p>
                    <p><strong>Số điện thoại:</strong>  ${user.getPhonenum()}</p>
                </div>
                <form class="form-logout" action="/user/account" method="post">
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