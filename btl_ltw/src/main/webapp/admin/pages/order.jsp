<%@ page import="java.util.*, servlets.admin.ServletUtil, models.dtos.*"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 id="title-page">Quản lý đơn hàng</h1>
<%
List<AdminOrderPreview> listAdminOrderPreviews = (List<AdminOrderPreview>) request.getAttribute("listAdminBookViews");
String message = (String) request.getSession().getAttribute("message");
String messageType = (String) request.getSession().getAttribute("messageType");
request.getSession().removeAttribute("message");
request.getSession().removeAttribute("messageType");
%>
<div class="overflow">
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Ngày tạo</th>
				<th>Trạng thái</th>
				<th>Địa chỉ</th>
				<th>SDT</th>
				<th>Người mua</th>
				<th>Tổng số sản phẩm</th>
				<th>Tổng tiền</th>
				<th>Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="order" items="${listAdminOrderPreviews}">
				<tr>
					<td>${order.id}</td>
					<td>${order.getCreate_time_string()}</td>
					<td><select name="status"
						onchange="updateStatus('${order.id}', this.value)">
							<option value="0"
								<c:if test="${order.status == 0}">selected</c:if>>Chưa
								xác nhận</option>
							<option value="1"
								<c:if test="${order.status == 1}">selected</c:if>>Đã
								xác nhận chờ thanh toán</option>
							<option value="2"
								<c:if test="${order.status == 2}">selected</c:if>>Đã
								thanh toán chờ vận chuyển</option>
							<option value="3"
								<c:if test="${order.status == 3}">selected</c:if>>Đang
								vận chuyển</option>
							<option value="4"
								<c:if test="${order.status == 4}">selected</c:if>>Đã
								vận chuyển</option>
							<option value="5"
								<c:if test="${order.status == 5}">selected</c:if>>Hủy
								đơn trước khi vận chuyển</option>
							<option value="6"
								<c:if test="${order.status == 6}">selected</c:if>>Hủy
								đơn khi đang vận chuyển</option>
							<option value="7"
								<c:if test="${order.status == 7}">selected</c:if>>Hoàn
								đơn</option>
					</select></td>
					<td>${order.address}</td>
					<td>${order.phonenum}</td>
					<td>${order.buyer_name}</td>
					<td>${order.totalProduct}</td>
					<td>${order.totalPrice}</td>
					<td>
						<button
							onclick="window.location.href='/admin/order/detail?id=${order.id}'">Chi
							tiết</button>
						<button
							onclick="window.location.href='/admin/order/delete?id=${order.id}'">Xóa</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		function updateStatus(orderId, status) {
			window.location.href = '/admin/order/change-status?id='
					+ orderId + '&status=' + status
		}
	</script>
</div>
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
		%><a href="/admin/order?page=<%=i%>">
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
<p class="alert-<%=messageType%>">
	<%=(message != null ? message : "")%>
</p>

<script src="/admin/resources/js/content.js"
	type="text/javascript"></script>