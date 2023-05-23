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
			<tr id="tr-${category.getId()}">
				<td>${category.getId()}</td>
				<td>${category.getName()}</td>
				<td><a
					href="/btl_ltw/admin/category/change-status?id=${category.getId()}">${category.getStatus()}</a>
				</td>
				<td>
					<button onclick="showEditModal('tr-${category.getId()}')">Sửa</button>
					<button>Xóa</button>
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
		%><a href="/btl_ltw/admin/category?page=<%=i%>"><%=i%></a> <%
 } else {
 %><p style="display: inline;"><%=i%></p> <%
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
	<form method="post" action="/btl_ltw/admin/category" id="add-form">
		<h3>Thêm danh mục</h3>
		<input name="name" placeholder="Nhập tên danh mục" />
		<button type="submit">Thêm</button>
		<button type="reset">Hoàn tác</button>
		<button type="button" id="add-cancel" onclick="closeAddModal()">Hủy</button>
	</form>
</div>

<div id="edit-modal" class="modal">
	<form method="post" action="/btl_ltw/admin/category" id="edit-form">
		<h3>Sửa danh mục</h3>
		<input id="edit-hidden" type="hidden" name="id" value="" />
		<div class="form-data-text">
			<p>Tên danh mục</p>
			<input id="edit-name" name="name" placeholder="Nhập tên danh mục"
				id="edit-name">
		</div>
		<div class="form-data-button">
			<button type="submit">Sửa</button>
			<button type="button">Hoàn tác</button>
			<button type="button" id="edit-cancel" onclick="closeEditModal()">Hủy</button>
		</div>

	</form>
</div>
<script src="/btl_ltw/admin/resources/js/content.js"
	type="text/javascript"></script>

<script src="/btl_ltw/admin/resources/js/category.js"
	type="text/javascript"></script>