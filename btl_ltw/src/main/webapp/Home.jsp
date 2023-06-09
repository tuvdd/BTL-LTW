<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <div class="rating">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="far fa-star"></i>
                    </div>
                    <p>${o.price}vnd</p>
                </div>
            </c:forEach>

        </div>
        <h2 class="title">Sản phẩm mới nhất</h2>
        <div class="row">
            <c:forEach items="${listB}" var="m">
                <div class="col-4">
                    <a href="detail?bookid=${m.getId()}"><img src="data:image/png;base64,${m.getImageBase64()}" alt="" /></a>
                    <a href="detail?bookid=${m.getId()}">
                        <h4>${m.name}</h4>
                    </a>
                    <div class="rating">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="far fa-star"></i>
                    </div>
                    <p>Price: ${m.price}vnd</p>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- offer -->
    <div class="offer">
        <div class="small-container">
            <div class="row">
                <div class="col-2">
                    <img src="data:image/png;base64,${p.getImageBase64()}" alt="" class="offer-img" />
                </div>
                <div class="col-2">
                    <h2>SP đề cử</h2>
                    <h3>${p.name}</h3>
                    <small>${p.sub_description}</small>
                    <br />
                    <a href="#" class="btn">Buy Now &#8594;</a>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="Footer.jsp"></jsp:include>

</body>

</html>