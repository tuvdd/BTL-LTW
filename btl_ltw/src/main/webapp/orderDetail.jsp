<%@ page import="models.cart_demo.Cart" %>
<%@ page import="repositories.BookRepo" %>
<%@ page import="models.Book" %>
<%@ page import="models.OrderDetail" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<OrderDetail> orderDetailList = (List<OrderDetail>) request.getAttribute("order_detail_list");
    BookRepo bookRepo = new BookRepo();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel = stylesheet href="/resources/css/cart.css">

</head>
<body>
<jsp:include page="Menu.jsp"></jsp:include>
<div id = wrapper>
    <header id="site-header">
        <div class="container">
            <h1>Chi tiết đơn hàng </h1>
        </div>
    </header>

    <div class="container">

        <section id="cart">
            <%
                for(OrderDetail orderDetail: orderDetailList){
                    Book book = bookRepo.getById((orderDetail.getBook_id()));

            %>
            <article class="product">
                <header>
                    <input type="hidden" name="id" value="<%=orderDetail.getId()%>" class="form-input">
                    <a class="remove">
                        <a href="/detail?bookid=<%=orderDetail.getBook_id()%>"><img src="data:image/png;base64,<%=book.getImageBase64()%>" alt="" /></a>
                    </a>
                </header>

                <div class="content">

                    <h1><%=orderDetail.getBook_name()%></h1>

                </div>

                <footer class="content">
                    <span class="qt"><%=orderDetail.getQuantity()%></span>
                    <h2 class="full-price">
                        <%=orderDetail.getPrice()*orderDetail.getQuantity()%> VNĐ
                    </h2>

                    <h2 class="price">
                        <%=orderDetail.getPrice()%> VNĐ
                    </h2>
                </footer>
            </article>

            <%
                    }
            %>

            <footer id="site-footer">
                <div class="container clearfix">

                    <div class="left">
                        <h1 class="total">Total: <span>${total}</span>VNĐ</h1>
                    </div>


                </div>
            </footer>

    </div>



    <jsp:include page="Footer.jsp"></jsp:include>

</body>
</html>