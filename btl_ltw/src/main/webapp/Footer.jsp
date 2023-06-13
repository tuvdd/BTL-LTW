<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/css/footer.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
</head>
<body>
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="footer-col">
                    <h4>Thông tin của cửa hàng</h4>
                    <ul>
                        <li><a href="/gioi-thieu">Giới thiệu</a></li>
                    </ul>
                </div>
                <div class="footer-col">
                    <h4>Chúng tôi có</h4>
                    <ul>
                        <li><a href="/danh-sach-san-pham">Cửa hàng</a></li>
                        <li><a href="/account">Tài khoản</a></li>
                        <li><a href="/cart2.jsp">Giỏ hàng</a></li>
                    </ul>
                </div>
                <div class="footer-col">
                    <h4>E-commerce Bookstore</h4>
                    <ul>
                        <li><a href="/danh-sach-san-pham?urldanhmuc=self-help1">Self help</a></li>
                        <li><a href="/danh-sach-san-pham?urldanhmuc=truyen-ngan">Truyện ngắn</a></li>
                        <li><a href="/danh-sach-san-pham?urldanhmuc=ngon-tinh">Ngôn Tình</a></li>
                        <li><a href="/danh-sach-san-pham?urldanhmuc=12-cung-hoang-dao">12 Cung Hoàng Đạo</a></li>
                        <li><a href="/danh-sach-san-pham?urldanhmuc=kinh-doanh">Kinh doanh</a></li>
                    </ul>
                    
                </div>
            </div>
        </div>
    </footer>
</body>
</html>
