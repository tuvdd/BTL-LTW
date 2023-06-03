
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table{
                border-collapse: collapse;
            }
            .tr{
                text-align: right;
            }
            a{
                text-decoration: none;
                color: chocolate;
                font-size: 22px;
                font-weight: bold;
            }
        </style>
        
    </head>
    <body>
        <h1>Shopping cart online</h1>
        <table border="1px" width="40%">
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total money</th>
                <th>Action</th>
            </tr>
            <c:set var="o" value="${requestScope.cart}"/>
            <c:set var="tt" value="0"/>
            <c:forEach items="${o.items}" var="i">
                <c:set var="tt" value="${tt+1}"/>
                <tr>
                    <td>${tt}</td>
                    <td>${i.product.name}</td>
                    <td style="text-align: center">
                        <button><a href="process?num=-1&id=${i.product.id}">-</a></button>
                        ${i.quantity}
                        <button><a href="process?num=1&id=${i.product.id}">+</a></button>
                    </td>
                    <td class="tr">
                        $<fmt:formatNumber pattern="##.#" value="${i.price}"/>
                    </td>
                    <td class="tr">
                        $<fmt:formatNumber pattern="##.#" value="${(i.price*i.quantity)}"/>
                    </td>
                    <td style="text-align: center">
                        <form action="process" method="post">
                            <input type="hidden" name="id" value="${i.product.id}"/>
                            <input type="submit" value="Return item"/>   
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h3>Total money:$ ${o.totalMoney}</h3>
        <hr/>
        <form action="checkout" method="post">
            <input type="submit" value="Check out"/>
        </form>
        <hr/>
        <a href="shop">CLICK ME TO CONTINUE SHOPPING</a>
    </body>
</html>
