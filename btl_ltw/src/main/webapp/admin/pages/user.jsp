<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<User> listUsers = (List<User>) request.getAttribute("listUsers");
String message = (String) request.getSession().getAttribute("message");
String messageType = (String) request.getSession().getAttribute("messageType");
request.getSession().removeAttribute("message");
request.getSession().removeAttribute("messageType");
%>
<h1 id="title-page">Quản lý người dùng</h1>

<div class="overflow">
	<table class="content-table">
		<tr>
			<th>ID</th>
			<th>Tên</th>
			<th>SDT</th>
			<th>Email</th>
			<th>Trạng thái</th>
			<th>Tạo lúc</th>
			<th>Sửa lần cuối</th>
			<th>Tên tài khoản</th>
			<th>Thao tác</th>
		</tr>
		<c:forEach var="user" items="${listUsers}">
			<tr id="tr-${user.getId()}">
				<td>${user.getId()}</td>
				<td>${user.getName()}</td>
				<td>${user.getPhonenum()}</td>
				<td>${user.getEmail()}</td>
				<td><a
					href="/admin/user/change-status?id=${user.getId()}">${user.getStatus()}</a>
				</td>
				<td>${user.getCreated_time()}</td>
				<td>${user.getLast_update_time()}</td>
				<td>${user.getUsername()}</td>
				<td>
					<button onclick="showEditModal('tr-${user.getId()}')">Sửa</button>
					<button
						onclick="window.location.href='/admin/user/delete?id=${user.getId()}'">Xóa</button>
				</td>
			</tr>
		</c:forEach>
	</table>
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
		%><a href="/admin/admin?page=<%=i%>">
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
<button id="add-new-button" onclick="showAddModal()">Thêm mới</button>
<div id="add-modal" class="modal">
	<form method="post" action="/admin/user" id="add-form">

		<h3>Thêm user</h3>

		<div class="form-data-text">
			<p>Tên</p>
			<input name="name" placeholder="Nhập tên " id="add-name" required>
		</div>
		<div class="form-data-text">
			<p>Email</p>
			<input name="email" placeholder="Nhập email" id="add-email"
				type="email" required>
		</div>
		<div class="form-data-text">
			<p>SDT</p>
			<input name="phonenum" placeholder="Nhập SDT" id="add-phonenum"
				maxlength="12" type="text" required>
		</div>
		<div class="form-data-text">
			<p>Username</p>
			<input name="username" placeholder="Nhập username" id="add-username"
				required>
		</div>

		<div class="form-data-text">
			<p>Password</p>
			<input name="password" placeholder="Nhập password" id="add-password"
				type="password" required>
		</div>

		<div class="form-data-button">
			<button type="submit">Thêm</button>
			<button type="button">Hoàn tác</button>
			<button type="button" id="add-cancel" onclick="closeAddModal()">Hủy</button>
		</div>

	</form>
</div>

<div id="edit-modal" class="modal">
	<form method="post" action="/admin/user" id="edit-form">

		<h3>Sửa admin</h3>
		<input id="edit-hidden" type="hidden" name="id" id="edit_hidden"
			value="" required/>

		<div class="form-data-text">
			<p>Tên</p>
			<input name="name" placeholder="Nhập tên " id="edit-name" required>
		</div>
		<div class="form-data-text">
			<p>Email</p>
			<input name="email" placeholder="Nhập email" id="edit-email"
				type="email" required>
		</div>
		<div class="form-data-text">
			<p>SDT</p>
			<input name="phonenum" placeholder="Nhập SDT" id="edit-phonenum"
				maxlength="12" type="text" required>
		</div>

		<div class="form-data-button">
			<button type="submit">Sửa</button>
			<button type="button" id="add-cancel" onclick="closeEditModal()">Hủy</button>
		</div>

	</form>
</div>

<script src="/admin/resources/js/user.js" type="text/javascript"></script>
<script src="/admin/resources/js/content.js"
	type="text/javascript"></script>