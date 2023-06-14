<%@ page import="models.dtos.OrderFullDetail" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.OrderDetail" %>
<%@ page import="repositories.BookRepo" %>
<%@ page import="models.Book" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<OrderFullDetail> orderFullDetailList = new ArrayList<>();
    orderFullDetailList = (List<OrderFullDetail>) request.getAttribute("order_list");
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
            <h1>Đơn hàng đã mua </h1>
        </div>
    </header>

    <div class="container">

        <section id="cart">
            <%
                for(OrderFullDetail o:orderFullDetailList){
                    double total = 0;
                    List<OrderDetail> orderDetailList = o.getOrderDetails();
                    for(OrderDetail orderDetail: orderDetailList){
                        total += orderDetail.getPrice()*orderDetail.getQuantity();
                    }
                    OrderDetail fisrt_product = orderDetailList.get(0);
                    Book book = bookRepo.getById((fisrt_product.getBook_id()));
                    int status = o.status;


            %>
            <article class="product">
                    <header>
                        <input type="hidden" name="id" value="<%=o.id%>" class="form-input">
                        <a class="remove">
                            <a><img src="data:image/png;base64,<%=book.getImageBase64()%>" alt="" /></a>
                            <h3 style = "text-align: center"><a href="manageOrder?id=<%=o.id%>&cancel=1&status=<%=o.status%>" >Hủy đơn</a></h3>
                        </a>
                    </header>

                <div class="content">
                    <h1><a href="/orderdetail?orderID=<%=o.id%>">Đơn hàng tạo lúc: <%=o.created_time%> </a></h1>
                    Địa chỉ: <%=o.address%> (Khách hàng: <%=o.buyer_name%>)
                    <h1>Tổng tiền: <%=total%> VNĐ</h1>

                </div>

                <footer class="content">

                    <%
                        if(status == 0){
                    %>
                    <span class="full-price">Chưa xác nhận</span>
                    <%
                        }
                    %>
                    <%
                        if(status == 1){
                    %>
                    <span class="full-price">Chờ thanh toán</span>
                    <%
                        }
                    %>
                    <%
                        if(status == 2){
                    %>
                    <span class="full-price">Chờ vận chuyển</span>
                    <%
                        }
                    %>
                    <%
                        if(status == 3){
                    %>
                    <span class="full-price">Đang vận chuyển</span>
                    <%
                        }
                    %>
                    <%
                        if(status == 4){
                    %>
                    <span class="full-price">Đã vận chuyển</span>
                    <%
                        }
                    %>
                    <%
                        if(status == 5){
                    %>
                    <span class="full-price">Đã hủy đơn trước vận chuyển</span>
                    <%
                        }
                    %>
                    <%
                        if(status == 6){
                    %>
                    <span class="full-price">Đã hủy đơn khi vận chuyển</span>
                    <%
                        }
                    %>
                    <%
                        if(status == 7){
                    %>
                    <span class="full-price">Hoàn đơn</span>
                    <%
                        }
                    %>
                </footer>
            </article>
                <%
                    }
                %>

    </div>



    <jsp:include page="Footer.jsp"></jsp:include>

</body>
</html>