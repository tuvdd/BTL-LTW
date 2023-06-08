
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*" %>
<% 
    List<Comment> listComments = (List<Comment>) request.getAttribute("listComments");
    int numberOfPages = (int) request.getAttribute("numberOfPages");
    String bookid = (String) request.getAttribute("bookid");
    Book book = (Book) request.getAttribute("book");
    int numberComment = (int) request.getAttribute("numberComment");
    float averageComment = (float) request.getAttribute("averageComment");
    int fullStars = (int) averageComment;
    boolean hasHalfStar = averageComment % 1 != 0;
%>
<%
    String error = (String) session.getAttribute("error");
    if (error != null) {
%>
    <script>alert("error", "aa")</script>
<%  } %>
            
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Product Card/Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/resources/css/detail2.css">
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
                    
                </div>
                <!-- card right -->
                <div class = "product-content">
                    <h2 class = "product-title">${book.name}</h2>
                    <div class = "product-rating">
                        <%
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
                        <span>Rating ${averageComment} (${numberComment})</span>
                    </div>

                    <div class = "product-price">
                        <p class = "last-price">Old Price: <span>${book.price}</span></p>
                        <p class = "new-price">New Price: <span>${book.promote_price}</span></p>
                    </div>

                    <div class = "product-detail">
                        <h2>about this item: </h2>
                        <p>${book.description}</p>
                        <!-- <p> Còn lại: ${book.getQuantity()}</p> -->
                    </div>

                    <div class = "purchase-info">
                        <input type = "number" min = "0" value = "1">
                        <button type = "button" class = "btn">
                            Add to Cart <i class = "fas fa-shopping-cart"></i>
                        </button>
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

        <div class="comment-wrapper">
            <form action="/btl_ltw/detail" method="post">
                <div class="add-comment">
                    <h1>Add Comment</h1>
                    <h3>Rating</h3>
                    <div class="rate">
                        <input type="radio" id="star5" name="rate" value="5" />
                        <label for="star5" title="text">5 stars</label>
                        <input type="radio" id="star4" name="rate" value="4" />
                        <label for="star4" title="text">4 stars</label>
                        <input type="radio" id="star3" name="rate" value="3" />
                        <label for="star3" title="text">3 stars</label>
                        <input type="radio" id="star2" name="rate" value="2" />
                        <label for="star2" title="text">2 stars</label>
                        <input type="radio" id="star1" name="rate" value="1" />
                        <label for="star1" title="text">1 star</label>
                    </div>

                    <textarea id="comment_text", name="comment_text" placeholder="Write your comment..."></textarea>
                    <button class="btn">Submit</button>
                </div>
            </form>

            
            <div class="review-comments">
                <h1 class="title-reviews">Reviews</h1>
                <div class="list-comments">
                    <% if (listComments.size() == 0) { %>
                        <p>Chưa có review nào!</p>
                    <%} else { %>
                        <c:forEach var="comment" items="${listComments}">
                            <div class="user_comment">
                                <div class="username">
                                    <h3>${comment.getUsername()}</h3>
                                    <p>${comment.getStringCreate_at()}</p>
                                </div>
                                <div class="rating">
                                    <div class="stars">
                                    <c:forEach var="number" begin="1" end="${comment.getRate()}">
                                            <div class="star_yellow"></div>
                                        </c:forEach>
                                        <c:forEach var="number" begin="1" end="${5 - comment.getRate()}">
                                            <div class="star_gray"></div>
                                        </c:forEach>

                                    </div>
                                    <div class="rating_text">
                                        <p>${comment.getRate()} stars</p>
                                    </div>
                                </div>
                                <div class="text_cmt">
                                    ${comment.getComment()}
                                </div>
                            </div>
                        </c:forEach>
                    <%}%>
                </div>
            </div>
              
            <div class="pagination">
                <% for (int i = 1; i <= numberOfPages; i++) { %>
                    <div class="page-item">
                        <a href="/btl_ltw/detail?bookid=${bookid}&page=<%= i %>"><%= i %></a>
                    </div>
                <% } %>
              </div>
              
        </div>
        
 
        <script src="/btl_ltw/resources/detail.js"></script>
    <jsp:include page="Footer.jsp"></jsp:include>

    </body>
</html>