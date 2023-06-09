<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="/btl_ltw/resources/css/shopping.css" />
    <title>Ecommerce Website</title>
</head>

<body>
    <jsp:include page="Menu.jsp"></jsp:include>

    <div id = "wrapper">
        <div id="menu_tab">
            <c:set var="cat" value="${requestScope.data}"/>
            <c:set var="cid" value="${requestScope.cid}"/>
            <ul class="menu">
                <li><a class="${cid==0?"active":""}" href="shopping1?cid=0">ALL</a></li>
                <c:forEach items="${cat}" var="c">
                    <li><a class="${cat.indexOf(c)+1==cid?"active":""}" href="shopping1?cid=${cat.indexOf(c)+1}">${c.name}</a></li>
                </c:forEach>
            </ul>
        </div>

        <div class="clr"></div>

        <div id="content">
            <div id="tab1">
                <div id = "tab11">
                    <c:set var="chid" value="${requestScope.chid}"/>
                    <h5 style="color: chocolate">TÊN HÀNG</h5>
                    <hr style="border-top: 1px solid chocolate "/>
                    <form id="f1" action="shopping1">
                        <input type="checkbox" id="c0" name="cidd"
                        ${chid[0]?"checked":""}
                               value="${0}" onclick="setCheck(this)"/>All<br/>
                        <c:forEach begin="0" end="${cat.size()-1}"  var="i">
                            <input type="checkbox" id="cm" name="cidd"
                                ${i+1==cid?"checked":""}
                                   value="${i+1}"
                                ${chid[i+1]?"checked":""}   onclick="setCheck(this)" />
                            ${cat.get(i).getName()}
                            <br/>
                        </c:forEach>
                    </form>
                    <h5 style="color: chocolate">MỨC GIÁ</h5>
                    <hr style="border-top: 1px solid chocolate "/>
                    <c:set var="pp" value="${requestScope.pp}"/>
                    <c:set var="pb" value="${requestScope.pb}"/>
                    <form id="f2" action="shopping1">
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

            </div>
            <div id="tab2">
                <div id = "tab22">
                    <c:set var="news" value="${requestScope.news}"/>
                    <c:if test="${news!=null}">
                        <h4 style="color: chocolate">SÁCH MỚI </h4>
                        <ul class="item">
                            <c:forEach items="${news}" var="p">
                                <li>
                                    <a href="detail?bookid=${p.getId()}">
                                        <img src="data:image/png;base64,${p.getImageBase64()}" alt="" width="80px" height="80px" />
                                        <p>${p.name}</p>
                                        <p>gia goc: <span class="old">${(p.price)}</span>VND</p>
                                        <p>Sale:${(p.price)}VND</p>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
	                        <%
							int currentPage = (int) request.getAttribute("currentPage");
							%>
							<%
							int totalPages = (int) request.getAttribute("totalPages");
							%>
							<p style="display: inline;">Trang</p>
							<ul class="pagination" style="display: inline;">
								<%
								for (int i = 1; i <= totalPages; i++) {
								%>
								<li style="display: inline;">
									<%
									if (i != currentPage) {
									%><a href="/btl_ltw/shopping?newspage=<%=i%>">
										<%=i%>
								</a> <%
							 } else {
							 %>
									<p style="display: inline;">
										<%=i%>
									</p> <%
							 }
							 %>
								</li>
								<%
								}
								%>
						</ul>
                        <hr/>
                    </c:if>
			
                    <c:set var="olds" value="${requestScope.olds}"/>
                    <c:if test="${olds!=null}">
                        <h4 style="color: chocolate">SÁCH KHUYẾN MẠI </h4>
                        <ul class="item">
                            <c:forEach items="${olds}" var="p">
                                <li>
                                    <a href="detail?bookid=${p.getId()}">
                                        <img src="data:image/png;base64,${p.getImageBase64()}" alt="" width="80px" height="80px" />
                                        <p>${p.name}</p>
                                        <p>gia goc: <span class="old">${(p.price)}</span>VND</p>
                                        <p>Sale:${(p.price)}VND</p>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        </ul>
	                        <%
							int currentPage = (int) request.getAttribute("currentPage");
							%>
							<%
							int totalPages = (int) request.getAttribute("totalPages");
							%>
							<p style="display: inline;">Trang</p>
							<ul class="pagination" style="display: inline;">
								<%
								for (int i = 1; i <= totalPages; i++) {
								%>
								<li style="display: inline;">
									<%
									if (i != currentPage) {
									%><a href="/btl_ltw/shopping?oldspage=<%=i%>">
										<%=i%>
								</a> <%
							 } else {
							 %>
									<p style="display: inline;">
										<%=i%>
									</p> <%
							 }
							 %>
								</li>
								<%
								}
								%>
						</ul>
                        <hr/>
                    </c:if>

                    <c:set var="list" value="${requestScope.products}"/>
                    <c:if test="${list!=null}">
                        <h4 style="color: chocolate">SÁCH (${list.size()} sản phẩm)</h4>
                        <ul class="item">
                            <c:forEach items="${list}" var="p">
                                <li>
                                    <a href="detail?bookid=${p.getId()}">
                                        <img src="data:image/png;base64,${p.getImageBase64()}" alt="" width="80px" height="80px" />
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
        </div>
    </div>
    <div class="clr"></div>
    <jsp:include page="Footer.jsp"></jsp:include>

</body>

</html>