<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <h1>Shopping cart </h1>
        </div>
    </header>

    <div class="container">

        <section id="cart">
            <c:forEach items="${order_list}" var="c">
            <article class="product">
                <header>
                    <input type="hidden" name="id" value="${c.getId()}" class="form-input">
                </header>

                <div class="content">
                    <h1><a href="/orderdetail?orderID=${c.getId()}">${c.getCreated_time()} </a></h1>
<%--                    <h1>${c.getCreated_time()} </h1>--%>

                </div>

                <footer class="content">
                    <span class="qt">Status: ${c.getStatus()}</span>
<%--                    <h2 class="full-price">--%>
<%--                            ${c.getPromote_price()*c.getQuantity()} VNĐ--%>
<%--                    </h2>--%>

<%--                    <h2 class="price">--%>
<%--                            ${c.getPromote_price()} VNĐ--%>

<%--                            &lt;%&ndash;        ${c.promote_price} VNĐ&ndash;%&gt;--%>
<%--                    </h2>--%>
                </footer>
            </article>

            </c:forEach>

<%--            <footer id="site-footer">--%>
<%--                <div class="container clearfix">--%>

<%--                    <div class="left">--%>
<%--                        <h1 class="total">Total: <span>${total}</span>VNĐ</h1>--%>
<%--                        <a href = "orders.jsp" class="btn">Checkout</a>--%>
<%--                    </div>--%>


<%--                </div>--%>
<%--            </footer>--%>

    </div>



    <jsp:include page="Footer.jsp"></jsp:include>

</body>
</html>