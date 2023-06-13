<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Test Page</title>
  <link rel = stylesheet href="/resources/css/orders.css">
</head>
<body>
  <jsp:include page="Menu.jsp"></jsp:include>
  <div id = wrapper>
    <div class="row">
      <div class="col-75">
        <div class="container">
          <form action = "cart-check-out">
            <div class="row">
              <div class="col-50">
                <h3>Check Out</h3>
                <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                <input type="text" id="fname" name="name" placeholder="Tên">
                <label for="phone"><i class="fa fa-phone"></i> Phone</label>
                <input type="text" id="phone" name="phone" placeholder="012345678">
                <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                <input type="text" id="adr" name="address" placeholder="ngõ xxx phố yyy">
              </div>

              <div class="col-50">
                <h3>Thanh toán</h3>
                <label> Giao tiền khi nhận hàng</label>
              </div>
              <input type="submit" value="Xác nhận" class="btn">
            </div>

          </form>
        </div>
      </div>

      <div class="col-25">
        <div class="container">
          <h4>Cart
            <span class="price" style="color:black">
                    <i class="fa fa-shopping-cart"></i>
                    <b>4</b>
                  </span>
          </h4>
          <p><a href="#">Product 1</a> <span class="price">$15</span></p>
          <p><a href="#">Product 2</a> <span class="price">$5</span></p>
          <p><a href="#">Product 3</a> <span class="price">$8</span></p>
          <p><a href="#">Product 4</a> <span class="price">$2</span></p>
          <hr>
          <p>Total <span class="price" style="color:black"><b>$30</b></span></p>
        </div>
      </div>
    </div>

  </div>
  <div class="clr"></div>
  <div id = wrapper>
    <jsp:include page="Footer.jsp"></jsp:include>

  </div>
</body>
</html>
