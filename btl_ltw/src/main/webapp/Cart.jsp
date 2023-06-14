<%@ page import="models.cart_demo.Cart" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Cart> cart_list = (List<Cart>) request.getAttribute("cart_list");
    System.out.println(cart_list + "cart_jsp");
    session.setAttribute("cart_list", cart_list);
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
            <h1>Shopping cart </h1>
        </div>
    </header>

    <div class="container">

        <section id="cart">
            <%
            if(cart_list!=null){
                for(Cart c : cart_list){


            %>
            <article class="product">
                <header>
                    <input type="hidden" name="id" value="<%=c.getId()%>" class="form-input">
                    <a class="remove">
                        <a href="#"><img src="data:image/png;base64,<%=c.getImageBase64()%>" alt="" /></a>
                        <h3><a href="remove-from-cart?id=<%=c.getId()%>" >Remove product</a></h3>

                    </a>
                </header>

                <div class="content">

                    <h1><%=c.getName()%></h1>

                </div>

                <footer class="content">
                    <a href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><span class="qt-minus">-</span></a>
                    <span class="qt"><%=c.getQuantity()%></span>
                    <a href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><span class="qt-minus">+</span></a>


                    <h2 class="full-price">
                            <%=c.getPromote_price()*c.getQuantity()%> VNĐ
                    </h2>

                    <h2 class="price">
                            <%=c.getPromote_price()%> VNĐ

                    </h2>
                </footer>
            </article>

            <%
                    }
                }
            %>

            <footer id="site-footer">
                <div class="container clearfix">
                    <%
                        if(cart_list == null){

                    %>
                    <h2>Giỏ hàng trống !</h2>
                    <%
                        }
                    %>

                    <div class="left">
                        <h1 class="total">Total: <span>${total}</span>VNĐ</h1>
                        <a href = "orders.jsp" class="btn">Checkout</a>
                    </div>


                </div>
            </footer>

    </div>
</div>


<div class="clr"></div>
<div id = wrapper>
    <jsp:include page="Footer.jsp"></jsp:include>

</div>

</body>
</html>