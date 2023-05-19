<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="css/shopping.css" />
    <title>Ecommerce Website</title>
</head>

<body>
    <jsp:include page="Menu.jsp"></jsp:include>
    <div>
        <script src = "shopping.js"></script>
        <div id="content">
            <div id="tab1">
                <c:set var="chid" value="${requestScope.chid}"/>
                <h5 style="color: chocolate">TÊN HÃNG</h5>
                <hr style="border-top: 1px solid chocolate "/>
                <form id="f1" action="home1">
                    <input type="checkbox" id="c0" name="cidd"
                    ${chid[0]?"checked":""}
                           value="${0}" onclick="setCheck(this)"/>All<br/>
                </form>
                <h5 style="color: chocolate">MỨC GIÁ</h5>
                <hr style="border-top: 1px solid chocolate "/>
                <c:set var="pp" value="${requestScope.pp}"/>
                <c:set var="pb" value="${requestScope.pb}"/>
                <form id="f2" action="home1">
                    <input type="checkbox" id="g0" name="price"
                    ${pb[0]?"checked":""}
                           value="0" onclick="setCheck1(this)"/>All<br/>
                    <c:forEach begin="0" end="${4}"  var="i">
                        <input type="checkbox" id="g1" name="price"
                            ${pb[i+1]?"checked":""}
                               value="${(i+1)}" onclick="setCheck1(this)"/>${pp[i]}<br/>
                    </c:forEach>
                </form>
            </div>
            <div id="tab2">
                <c:set var="news" value="${requestScope.news}"/>
                <c:if test="${news!=null}">
                    <h4 style="color: chocolate">ĐIỆN THOẠI MỚI </h4>
                    <ul class="item">
                        <c:forEach items="${news}" var="p">
                            <li>
                                <a href="#">
                                    <img src="${p.image}" width="80px" height="80px"/>
                                    <p>${p.name}</p>
                                    <p>gia goc: <span class="old">${(p.price)}</span>VND</p>
                                    <p>Sale:${(p.price)}VND</p>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                    <hr/>
                </c:if>
                <c:set var="olds" value="${requestScope.olds}"/>
                <c:if test="${olds!=null}">
                    <h4 style="color: chocolate">ĐIỆN THOẠI KHUYẾN MẠI </h4>
                    <ul class="item">
                        <c:forEach items="${olds}" var="p">
                            <li>
                                <a href="#">
                                    <img src="${p.image}" width="80px" height="80px"/>
                                    <p>${p.name}</p>
                                    <p>gia goc: <span class="old">${(p.price)}</span>VND</p>
                                    <p>Sale:${(p.price)}VND</p>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                    <hr/>
                </c:if>
                <c:set var="list" value="${requestScope.products}"/>
                <c:if test="${list!=null}">
                    <h4 style="color: chocolate">ĐIỆN THOẠI (${list.size()} sản phẩm)</h4>
                    <ul class="item">
                        <c:forEach items="${list}" var="p">
                            <li>
                                <a href="#">
                                    <img src="${p.image}" width="80px" height="80px"/>
                                    <p>${p.name}</p>
                                    <p>gia goc: <span class="old">${(p.price)}</span>VND</p>
                                    <p>Sale:${(p.price)}VND</p>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>


            </div>
    </div>
    <jsp:include page="Footer.jsp"></jsp:include>
</body>

</html>