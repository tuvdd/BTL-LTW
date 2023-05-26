
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Product Card/Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/btl_ltw/resources/css/detail.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    </head>

    <body>
    <jsp:include page="Menu.jsp"></jsp:include>
        <div class = "card-wrapper">
            <div class = "card">
                <!-- card left -->
                <div class = "product-imgs">
                    <div class = "img-display">
                        <div class = "img-showcase">
                            <img src = "data:image/png;base64,${book.getImageBase64()}" alt = "shoe image">
                            <img src = "data:image/png;base64,${book.getImageBase64()}" alt = "shoe image">
                            <img src = "data:image/png;base64,${book.getImageBase64()}" alt = "shoe image">
                            <img src = "data:image/png;base64,${book.getImageBase64()}" alt = "shoe image">
                        </div>
                    </div>
                    <div class = "img-select">
                        <div class = "img-item">
                            <a href = "#" data-id = "1">
                                <img src = "data:image/png;base64,${book.getImageBase64()}" alt = "shoe image">
                            </a>
                        </div>
                        <div class = "img-item">
                            <a href = "#" data-id = "2">
                                <img src = "data:image/png;base64,${book.getImageBase64()}" alt = "shoe image">
                            </a>
                        </div>
                        <div class = "img-item">
                            <a href = "#" data-id = "3">
                                <img src = "data:image/png;base64,${book.getImageBase64()}" alt = "shoe image">
                            </a>
                        </div>
                        <div class = "img-item">
                            <a href = "#" data-id = "4">
                                <img src = "data:image/png;base64,${book.getImageBase64()}" alt = "shoe image">
                            </a>
                        </div>
                    </div>
                </div>
                <!-- card right -->
                <div class = "product-content">
                    <h2 class = "product-title">${book.name}</h2>
                    <a href = "#" class = "product-link">visit nike store</a>
                    <div class = "product-rating">
                        <i class = "fas fa-star"></i>
                        <i class = "fas fa-star"></i>
                        <i class = "fas fa-star"></i>
                        <i class = "fas fa-star"></i>
                        <i class = "fas fa-star-half-alt"></i>
                        <span>4.7(21)</span>
                    </div>

                    <div class = "product-price">
                        <p class = "last-price">Old Price: <span>${book.price}</span></p>
                        <p class = "new-price">New Price: <span>${book.promote_price}</span></p>
                    </div>

                    <div class = "product-detail">
                        <h2>about this item: </h2>
                        <p>${book.description}</p>
                        <ul>
                            <li>Color: <span>Black</span></li>
                            <li>Available: <span>Còn hàng</span></li>
                            <li>Category: <span>Trinh thám</span></li>
                            <li>Shipping Area: <span>Toàn thế giới</span></li>
                            <li>Shipping Fee: <span>Free</span></li>
                        </ul>
                    </div>

                    <div class = "purchase-info">
                        <input type = "number" min = "0" value = "1">
                        <button type = "button" class = "btn">
                            Add to Cart <i class = "fas fa-shopping-cart"></i>
                        </button>
                        <button type = "button" class = "btn">Compare</button>
                    </div>

                    <div class = "social-links">
                        <p>Share At: </p>
                        <a href = "#">
                            <i class = "fab fa-facebook-f"></i>
                        </a>
                        <a href = "#">
                            <i class = "fab fa-twitter"></i>
                        </a>
                        <a href = "#">
                            <i class = "fab fa-instagram"></i>
                        </a>
                        <a href = "#">
                            <i class = "fab fa-whatsapp"></i>
                        </a>
                        <a href = "#">
                            <i class = "fab fa-pinterest"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>


        <script src="/btl_ltw/resources/detail.js"></script>
    <jsp:include page="Footer.jsp"></jsp:include>

    </body>
</html>