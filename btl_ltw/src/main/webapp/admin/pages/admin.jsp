<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<Admin> listAdmins = (List<Admin>) request.getAttribute("listAdmins");
String message = (String) request.getSession().getAttribute("message");
String messageType = (String) request.getSession().getAttribute("messageType");
request.getSession().removeAttribute("message");
request.getSession().removeAttribute("messageType");
%>
<h1 id="title-page">Quản lý nhân viên</h1>
<div class="overflow">
	<table class="content-table">
		<tr>
			<th>ID</th>
			<th>Tên</th>
			<th>Email</th>
			<th>SDT</th>
			<th>CCCD</th>
			<th>Username</th>
			<th>Thao tác</th>
		</tr>
		<c:forEach var="adminFullDetail" items="${listAdmins}">
			<tr id="tr-${adminFullDetail.getId()}">
				<td>${adminFullDetail.getId()}</td>
				<td>${adminFullDetail.getName()}</td>
				<td>${adminFullDetail.getEmail()}</td>
				<td>${adminFullDetail.getPhonenum()}</td>
				<td>${adminFullDetail.getCccd()}</td>
				<td>${adminFullDetail.getUsername()}</td>
				<td>
					<button onclick="showEditModal('tr-${adminFullDetail.getId()}')">Sửa</button>
					<button onclick="window.location.href='/admin/admin/delete?id=${adminFullDetail.getId()}'">Xóa</button>
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
		%><a href="/admin/admin?page=<%=i%>"><%=i%></a>
		<%
		} else {
		%><p style="display: inline;"><%=i%></p>
		<%
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
	<form method="post" action="/admin/admin" id="add-form">
		<h3>Thêm admin</h3>

		<div class="form-data-text">
			<p>Tên</p>
			<input name="name" placeholder="Nhập tên " id="add-name" required>
		</div>
		<div class="form-data-text">
			<p>Email</p>
			<input name="email" placeholder="Nhập email" id="add-email" type="email" required>
		</div>
		<div class="form-data-text">
			<p>SDT</p>
			<input name="phonenum" placeholder="Nhập SDT" id="add-phonenum" maxlength="12" type="text"  required>
		</div>
		<div class="form-data-text">
			<p>CCCD</p>
			<input name="cccd" placeholder="Nhập cccd" id="add-cccd" maxlength="12" type="text"  required>
		</div>
		<div class="form-data-text">
			<p>Username</p>
			<input name="username" placeholder="Nhập username" id="add-username" required>
		</div>

		<div class="form-data-text">
			<p>Password</p>
			<input name="password" placeholder="Nhập password" id="add-username" type="password" required>
		</div>

		<div class="form-data-button">
			<button type="submit">Thêm</button>
			<button type="button">Hoàn tác</button>
			<button type="button" id="add-cancel" onclick="closeAddModal()">Hủy</button>
		</div>

	</form>
</div>

<div id="edit-modal" class="modal">
	<form method="post" action="/admin/admin" id="edit-form">
		<h3>Sửa admin</h3>
		<input id="edit-hidden" type="hidden" name="id" id="edit_hidden"
			value="" required/>
		<div class="form-data-text">
			<p>Tên</p>
			<input name="name" placeholder="Nhập tên " id="edit-name" required>
		</div>
		<div class="form-data-text">
			<p>Email</p>
			<input name="email" placeholder="Nhập email" id="edit-email" required type="email">
		</div>
		<div class="form-data-text">
			<p>SDT</p>
			<input name="phonenum" placeholder="Nhập SDT" id="edit-phonenum" required type="text"  maxlength="12">
		</div>
		<div class="form-data-text">
			<p>CCCD</p>
			<input name="cccd" placeholder="Nhập cccd" id="edit-cccd" required type="text"  maxlength="12">
		</div>

		<div class="form-data-button">
			<button type="submit">Sửa</button>
			<button type="button">Hoàn tác</button>
			<button type="button" id="edit-cancel" onclick="closeEditModal()">Hủy</button>
		</div>

	</form>
</div>
<script src="/admin/resources/js/admin.js"
	type="text/javascript"></script>
<script src="/admin/resources/js/content.js"
	type="text/javascript"></script>