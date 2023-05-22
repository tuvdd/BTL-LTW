<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<Category> listCategories = (List<Category>) request.getAttribute("listCategories");
String message = (String) request.getSession().getAttribute("message");
String messageType = (String) request.getSession().getAttribute("messageType");
request.getSession().removeAttribute("message");
request.getSession().removeAttribute("messageType");
%>
<h1 id="title-page">Quản lý danh Mục Sản Phẩm</h1>
<div class="overflow">
	<table class="content-table">
		<tr>
			<th>ID</th>
			<th>Tên</th>
			<th>Trạng thái</th>
			<th>Thao tác</th>
		</tr>
		<c:forEach var="category" items="${listCategories}">
			<tr>
				<td>${category.getId()}</td>
				<td>${category.getName()}</td>
				<td>${category.getStatus()}</td>
				<td>
					<button onclick="showEditModal()">Sửa</button>
					<button>Xóa</button>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<p class="alert-<%=messageType%>">
	<%=(message != null ? message : "")%>
</p>
<button id="add-new-button" onclick="showAddModal()">Thêm mới</button>
<div id="add-modal" class="modal">
	<form method="post" action="/btl_ltw/admin/category" id="add-form">
		<h3>Thêm danh mục</h3>
		<input name="name" placeholder="Nhập tên danh mục" />
		<button type="submit">Thêm</button>
		<button type="reset">Hoàn tác</button>
		<button type="button" id="add-cancel" onclick="closeAddModal()">Hủy</button>
	</form>
</div>

<div id="edit-modal" class="modal">
	<form method="put" action="/btl_ltw/admin/category" id="edit-form">
		<h3>Sửa danh mục</h3>

		<div class="form-data-text">
			<p>Tên danh mục</p>
			<input name="name" placeholder="Nhập tên danh mục" id="edit-name">
		</div>
		<div class="form-data-checkbox">
			<p>Trạng thái</p>
			<input name="status" type="checkbox" id="edit-status">
		</div>
		<div class="form-data-button">
			<button type="submit">Sửa</button>
			<button type="button">Hoàn tác</button>
			<button type="button" id="add-cancel" onclick="closeEditModal()">Hủy</button>
		</div>

	</form>
</div>
<script src="/btl_ltw/admin/resources/js/content.js"
	type="text/javascript"></script>