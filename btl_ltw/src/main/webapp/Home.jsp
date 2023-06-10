<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="/btl_ltw/resources/css/home.css" />
    <title>Ecommerce Website</title>
</head>

<body>
    <jsp:include page="Menu.jsp"></jsp:include>
        <div class="header">
            <div class="container">

                <div class="row">
                    <div class="col-2">
                        <h1>
                            Chào mừng bạn đến với hiệu sách của chúng tôi
                        </h1>
                        <h2>
                        	Hiệu sách chúng tôi có tất cả sách mà bạn muốn tìm.
                        </h2>
                      <!--   <a href="/shopping" target="_blank" rel="noopener noreferrer" class="btn">Go Shopping &#8594;</a> -->
                    </div>
                    <div class="col-2">
                        <img src="/resources/img/Book1.png" alt="" />
                    </div>
                </div>
            </div>
        </div>

        <!-- Featured categories -->
        <div class="categories">
            <div class="small-container">
                <div class="row">
                    <div class="col-3">
                        <img src="/resources/img/category-1.jpg" alt="" />
                    </div>
                    <div class="col-3">
                        <img src="/resources/img/category-2.jpg" alt="" />
                    </div>
                    <div class="col-3">
                        <img src="/resources/img/category-3.jpg" alt="" />
                    </div>
                </div>
            </div>
        </div>

    <!-- Featured products -->
    <%
    List<Integer> listNumberCommentRB = (List<Integer>) request.getAttribute("listNumberCommentRB");
	List<Float> listAverageCommentRB = (List<Float>) request.getAttribute("listAverageCommentRB"); 
  	int countRB = 0;
	%>
    <div class="small-container">
        <h2 class="title">Sản phẩm nổi bật</h2>
    </div>
        <div class="row">
            <c:forEach items="${listRB}" var="o">
                <div class="col-4">
                    <a href="detail?bookid=${o.getId()}"><img src="data:image/png;base64,${o.getImageBase64()}" alt="" /></a>
                    <a href="detail?bookid=${o.getId()}">
                        <h4>${o.name}</h4>
                    </a>
                    <div class="rating" >
                        <%
                        	float averageCommentRB = listAverageCommentRB.get(countRB);
                        	int numberCommentRB = listNumberCommentRB.get(countRB);
	                        int fullStars = (int) averageCommentRB;
	                        boolean hasHalfStar = averageCommentRB % 1 != 0;
                            int countLocal = 0;
                            for (int i = 0; i < fullStars; i++) {
                                countLocal += 1;
                        %>
                            <div class="star_yellow"></div>
                        <%
                            }
                            if (hasHalfStar) {
                                countLocal += 1;
                        %>
                            <div class="half_star"></div>
                        <%
                            }
                            for (int i=0; i< 5-countLocal; i++) {
                        %>      
                            <div class="star_gray"></div>
                        <% } %>
                    </div>
                    <div class = "product-price">
                        <p class = "last-price">Giá cũ: <span>${o.price}vnd</span></p>
                        <p class = "new-price">Giá mới: <span>${o.promote_price}vnd</span></p>
                    </div>
                    <% countRB += 1;  %>
                </div>
            </c:forEach>

        </div>
        <%
    List<Integer> listNumberCommentB = (List<Integer>) request.getAttribute("listNumberCommentB");
	List<Float> listAverageCommentB = (List<Float>) request.getAttribute("listAverageCommentB"); 
  	int countB = 0;
	%>
        <h2 class="title">Sản phẩm mới nhất</h2>
        <div class="row">
            <c:forEach items="${listB}" var="m">
                <div class="col-4">
                    <a href="detail?bookid=${m.getId()}"><img src="data:image/png;base64,${m.getImageBase64()}" alt="" /></a>
                    <a href="detail?bookid=${m.getId()}">
                        <h4>${m.name}</h4>
                    </a>
                    <div class="rating">
                        <%
                        	float averageComment = listAverageCommentB.get(countB);
                        	int numberComment = listNumberCommentB.get(countB);
	                        int fullStars = (int) averageComment;
	                        boolean hasHalfStar = averageComment % 1 != 0;
                            int countLocal = 0;
                            for (int i = 0; i < fullStars; i++) {
                                countLocal += 1;
                        %>
                            <div class="star_yellow"></div>
                        <%
                            }
                            if (hasHalfStar) {
                                countLocal += 1;
                        %>
                            <div class="half_star"></div>
                        <%
                            }
                            for (int i=0; i< 5-countLocal; i++) {
                        %>      
                            <div class="star_gray"></div>
                        <% } %>
                    </div>
                    <div class = "product-price">
                        <p class = "last-price">Giá cũ: <span>${m.price}vnd</span></p>
                        <p class = "new-price">Giá mới: <span>${m.promote_price}vnd</span></p>
                    </div>
                    <% countB += 1;  %>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- offer -->
    <div class="offer">
        <div class="small-container">
            <div class="row">
                <div class="col-2">
                    <a href="detail?bookid=${p.getId()}"><img src="data:image/png;base64,${p.getImageBase64()}" alt="" class="offer-img" /></a>
                </div>
                <div class="col-2">
                    <h2>Sản phẩm đề cử</h2>
                    <a href="detail?bookid=${p.getId()}"><h3>${p.name}</h3></a>
                    <small>${p.sub_description}</small>
                    <br />
                    <a href="/btl_ltw/detail?bookid=${p.getId()}" class="btn">Mua ngay &#8594;</a>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="Footer.jsp"></jsp:include>

</body>

</html>