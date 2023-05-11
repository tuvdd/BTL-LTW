<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="css/home.css" />
    <title>Ecommerce Website</title>
</head>

<body>
    <jsp:include page="Menu.jsp"></jsp:include>
        <div class="header">
            <div class="container">

                <div class="row">
                    <div class="col-2">
                        <h1>
                            Buy your book
                        </h1>
                        <p>
                            Giới thiệu...
                        </p>
                        <a href="#" target="_blank" rel="noopener noreferrer" class="btn">Go Shopping &#8594;</a>
                    </div>
                    <div class="col-2">
                        <img src="Book1.png" alt="" />
                    </div>
                </div>
            </div>
        </div>

        <!-- Featured categories -->
        <div class="categories">
            <div class="small-container">
                <div class="row">
                    <div class="col-3">
                        <img src="category-1.jpg" alt="" />
                    </div>
                    <div class="col-3">
                        <img src="category-2.jpg" alt="" />
                    </div>
                    <div class="col-3">
                        <img src="category-3.jpg" alt="" />
                    </div>
                </div>
            </div>
        </div>

    <!-- Featured products -->
    <div class="small-container">
        <h2 class="title">Featured Products</h2>
    </div>
        <div class="row">
            <c:forEach items="${listRB}" var="o">
                <div class="col-4">
                    <a href="#"><img src="${o.image}" alt="" /></a>
                    <a href="#">
                        <h4>$(o.name)</h4>
                    </a>
                    <div class="rating">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="far fa-star"></i>
                    </div>
                    <p>${o.price}</p>
                </div>
            </c:forEach>

        </div>
        <h2 class="title">Latest Products</h2>
        <div class="row">
            <c:forEach items="${listB}" var="m">
                <div class="col-4">
                    <a href="#"><img src="${m.image}" alt="" /></a>
                    <a href="#">
                        <h4>${m.name}</h4>
                    </a>
                    <div class="rating">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="far fa-star"></i>
                    </div>
                    <p>${m.price}</p>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- offer -->
    <div class="offer">
        <div class="small-container">
            <div class="row">
                <div class="col-2">
                    <img src="${p.image}" alt="" class="offer-img" />
                </div>
                <div class="col-2">
                    <p>SP đề cử</p>
                    <h1>${p.name}</h1>
                    <small>${p.description}</small>
                    <br />
                    <a href="#" class="btn">Buy Now &#8594;</a>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="Footer.jsp"></jsp:include>

</body>

</html>