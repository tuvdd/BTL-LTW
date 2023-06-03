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
            #bag{
                text-align: right;
                margin-right: 30px;
                margin-top: 30px;
            }
            .tr{
                text-align: right;
            }
        </style>
    </head>
    <body>
        <p id="bag">
            <img src="images/icon.png" width="50px" height="50px"/>
            <a href="show">Mybag(${requestScope.size}) items</a>
        </p>
        <h1>List of Products</h1>
        <form name="f" action="" method="post">
            Enter number of items to buy:
            <input style="text-align: center" type="number" name="num" value="1"/>
            <hr/>
            <table border="1px" width="40%">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${requestScope.data}" var="p">
                    <c:set var="id" value="${p.id}"/>
                    <tr>
                        <td>${id}</td>
                        <td>${p.name}</td>
                        <td class="tr">${p.quantity}</td>
                        <td class="tr">
                            <fmt:formatNumber pattern="##.#" value="${(p.price*2)}"/></td>
                        <td><input type="button" onclick="buy('${id}')" value="Buy item"/></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
<script type="text/javascript">
    function buy(id){
        var m=document.f.num.value;
        document.f.action="buy?id="+id+"&num="+m;
        document.f.submit();
    }
</script>    