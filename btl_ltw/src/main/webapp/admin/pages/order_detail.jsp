<%@ page import="java.util.*, servlets.admin.ServletUtil, models.dtos.*"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 id="title-page">Quản lý đơn hàng</h1>
<%
OrderFullDetail orderFullDetail = (OrderFullDetail) request.getAttribute("orderFullDetail");
%>
<div class="overflow">
	<table>
		<thead>
			<tr>
				<th style="max-width: 150px;">ID</th>
				<th>Ngày đặt hàng</th>
				<th>Tên khách hàng</th>
				<th style="min-width: 400px;">Địa chỉ</th>
				<th>Số điện thoại</th>
				<th>Trạng thái</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${orderFullDetail.id}</td>
				<td>${orderFullDetail.getCreate_time_string()}</td>
				<td>${orderFullDetail.buyer_name}</td>
				<td>${orderFullDetail.address}</td>
				<td>${orderFullDetail.phonenum}</td>
				<td><select name="status"
					onchange="updateStatus('${orderFullDetail.id}', this.value)">
						<option value="0"
							<c:if test="${orderFullDetail.status == 0}">selected</c:if>>Chưa
							xác nhận</option>
						<option value="1"
							<c:if test="${orderFullDetail.status == 1}">selected</c:if>>Đã
							xác nhận chờ thanh toán</option>
						<option value="2"
							<c:if test="${orderFullDetail.status == 2}">selected</c:if>>Đã
							thanh toán chờ vận chuyển</option>
						<option value="3"
							<c:if test="${orderFullDetail.status == 3}">selected</c:if>>Đang
							vận chuyển</option>
						<option value="4"
							<c:if test="${orderFullDetail.status == 4}">selected</c:if>>Đã
							vận chuyển</option>
						<option value="5"
							<c:if test="${orderFullDetail.status == 5}">selected</c:if>>Hủy
							đơn trước khi vận chuyển</option>
						<option value="6"
							<c:if test="${orderFullDetail.status == 6}">selected</c:if>>Hủy
							đơn khi đang vận chuyển</option>
						<option value="7"
							<c:if test="${orderFullDetail.status == 7}">selected</c:if>>Hoàn
							đơn</option>
				</select> <script>
					function updateStatus(orderId, status) {
						window.location.href = '/admin/order/change-status?id='
								+ orderId + '&status=' + status
					}
				</script></td>
			</tr>
		</tbody>
	</table>
	<h2>Chi tiết đơn hàng</h2>
	<table>
		<tr>
			<th>Sản phẩm</th>
			<th class="td-number">Số lượng</th>
			<th class="td-number">Đơn giá</th>
			<th class="td-number">Thành tiền</th>
		</tr>
		<c:forEach var="orderDetail" items="${orderFullDetail.orderDetails}">
			<tr>
				<td>${orderDetail.book_name}</td>
				<td class="td-number">${orderDetail.quantity}</td>
				<td class="td-number">${orderDetail.price}</td>
				<td class="td-number">${orderDetail.quantity*orderDetail.price}</td>
			</tr>
		</c:forEach>
	</table>
</div>
<script src="/admin/resources/js/content.js"
	type="text/javascript"></script>