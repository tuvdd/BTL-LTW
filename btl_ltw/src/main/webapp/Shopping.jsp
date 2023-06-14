<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@page import="java.util.*, java.text.*, servlets.admin.ServletUtil, models.dtos.*, models.*" language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<!DOCTYPE html>
		<html>

		<head>
			<link rel="stylesheet" href="/resources/css/shopping.css" />
			<title>Ecommerce Website</title>
		</head>

		<body>
			<jsp:include page="Menu.jsp"></jsp:include>

			<div id="wrapper">
				<div id="menu_tab"></div>

				<div class="clr"></div>

				<div id="content">
					<div id="tab1">
						<div id="tab11">
							<h5 style="color: white">Danh mục sản phẩm</h5>
							<hr style="border-top: 1px solid chocolate" />
							<form>
								<a href="/danh-sach-san-pham">
									<c:choose>
										<c:when test="${tenDanhMuc == null}">
											<strong>Tất cả</strong>
										</c:when>
										<c:otherwise>
											Tất cả
										</c:otherwise>
									</c:choose>
								</a>
								<c:forEach items="${categories}" var="category">
									<a href="/danh-sach-san-pham?urldanhmuc=${category.url}">
										<c:choose>
											<c:when test="${tenDanhMuc == category.name}">
												<strong>${category.name}</strong>
											</c:when>
											<c:otherwise>
												${category.name}
											</c:otherwise>
										</c:choose>
									</a>
								</c:forEach>
							</form>	
							<br />
							<h5 style="color: white">Sắp xếp</h5>
							<hr style="border-top: 1px solid white" />
							<form>
								<a href="/danh-sach-san-pham?${urldanhmuc==null ? '' : 'urldanhmuc='}${urldanhmuc==null ? '' : urldanhmuc }">
									<c:choose>
										<c:when test="${tenDanhMuc == null}">
											<strong>Tất cả</strong>
										</c:when>
										<c:otherwise>
											Tất cả
										</c:otherwise>
									</c:choose>
								</a>
								<a
									href="/danh-sach-san-pham?${urldanhmuc==null ? '' : 'urldanhmuc='}${urldanhmuc==null ? '' : urldanhmuc }&filter=AZ${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}">
									<c:choose>
										<c:when test="${filter == 'AZ'}">
											<strong>A đến Z</strong>
										</c:when>
										<c:otherwise>
											A đến Z
										</c:otherwise>
									</c:choose>
								</a> <a
									href="/danh-sach-san-pham?${urldanhmuc==null ? '' : 'urldanhmuc='}${urldanhmuc==null ? '' : urldanhmuc }&filter=ZA${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}">
									<c:choose>
										<c:when test="${filter == 'ZA'}">
											<strong>Z đến A</strong>
										</c:when>
										<c:otherwise>
											Z đến A
										</c:otherwise>
									</c:choose>
								</a> <a
									href="/danh-sach-san-pham?${urldanhmuc==null ? '' : 'urldanhmuc='}${urldanhmuc==null ? '' : urldanhmuc }&filter=NEWEST${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}">
									<c:choose>
										<c:when test="${filter == 'NEWEST'}">
											<strong>Mới nhất</strong>
										</c:when>
										<c:otherwise>
											Mới nhất
										</c:otherwise>
									</c:choose>
								</a> <a
									href="/danh-sach-san-pham?${urldanhmuc==null ? '' : 'urldanhmuc='}${urldanhmuc==null ? '' : urldanhmuc }&filter=OLDEST${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}">
									<c:choose>
										<c:when test="${filter == 'OLDEST'}">
											<strong>Cũ nhất</strong>
										</c:when>
										<c:otherwise>
											Cũ nhất
										</c:otherwise>
									</c:choose>
								</a> <a
									href="/danh-sach-san-pham?${urldanhmuc==null ? '' : 'urldanhmuc='}${urldanhmuc==null ? '' : urldanhmuc }&filter=LOWEST${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}">
									<c:choose>
										<c:when test="${filter == 'LOWEST'}">
											<strong>Giá thấp đến cao</strong>
										</c:when>
										<c:otherwise>
											Giá thấp đến cao
										</c:otherwise>
									</c:choose>
								</a> <a
									href="/danh-sach-san-pham?${urldanhmuc==null ? '' : 'urldanhmuc='}${urldanhmuc==null ? '' : urldanhmuc }&filter=HIGHEST${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}${pricemax != null ? '&pricemax=':''}${pricemax != null ? pricemax : ''}">
									<c:choose>
										<c:when test="${filter == 'HIGHEST'}">
											<strong>Giá cao đến thấp</strong>
										</c:when>
										<c:otherwise>
											Giá cao đến thấp
										</c:otherwise>
									</c:choose>
								</a>
							</form>
							<br />
							<h5 style="color: white">MỨC GIÁ</h5>
							<hr style="border-top: 1px solid white" />
							<form>
								<label for="lowest">Lowest:</label> <input type="text" id="lowest" name="lowest"
									value="${pricemin}"> <label for="highest">Highest:</label>
								<input type="text" id="highest" name="highest" value="${pricemax}">
								<button type="button" onclick="filter()">Lọc</button>
							</form>

							<script>
								function filter() {
									var lowest = document.getElementById("lowest").value;
									var highest = document.getElementById("highest").value;
									var url = "/danh-sach-san-pham?${urldanhmuc==null ? '' : 'urldanhmuc='}${urldanhmuc==null ? '' : urldanhmuc }${filter == null ? '' : '&filter='}${filter == null ? '' : filter }";
									if (lowest !== "") {
										url += "&pricemin=" + lowest;
									}
									if (highest !== "") {
										url += "&pricemax=" + highest;
									}
									window.location.href = url;
								}
							</script>
						</div>

					</div>
					<div id="tab2">
						<div id="tab22">
							<% List<Book> data = (List<Book>) request.getAttribute("data");
									%>
									<c:if test="${data!=null}">
										<h4 style="color: chocolate">${tenDanhMuc}</h4>
										<ul class="item">
											<c:forEach items="${data}" var="b">
												<li><a href="detail?bookid=${b.getId()}"> <img
															src="data:image/png;base64,${b.getImageBase64()}" alt=""
															width="80px" height="80px" />
														<p>${b.name}</p>
														<p>
															Giá gốc: <span class="old">${(b.getPriceFormat())} VNĐ</span>
														</p>
														<p>Giá mới:${b.getPromotePriceFormat()} VNĐ</p>
													</a></li>
											</c:forEach>
										</ul>
										<% int currentPage=request.getAttribute("page")==null ? 1 : Integer.parseInt((String) request.getAttribute("page")); 
											int size=9; 
											int totalRecords=(int) request.getAttribute("total"); 
											int totalPages=totalRecords % size==0 ? totalRecords / size : totalRecords / size + 1; %>
											<p style="display: inline;">Trang</p>
											<ul class="pagination" style="display: inline;">
												<% for (int i=1; i <=totalPages; i++) { %>
													<li style="display: inline;">
														<% if (i !=currentPage) { %><a href="${currentUrl}&page=<%=i%>">
																<%=i%>
															</a>
															<% } else { %>
																<p style="display: inline;">
																	<%=i%>
																</p>
																<% } %>
													</li>
													<% } %>
											</ul>
											<hr />
									</c:if>
						</div>

					</div>
				</div>
			</div>
			<div class="clr"></div>
			<jsp:include page="Footer.jsp"></jsp:include>

		</body>

		</html>