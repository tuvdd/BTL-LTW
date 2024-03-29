<%@ page
	import="java.util.*, java.text.*, servlets.admin.ServletUtil, models.dtos.*, models.*"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
List<AdminBookView> listAdminBookViews = (List<AdminBookView>) request.getAttribute("listAdminBookViews");
List<Category> listCategories = (List<Category>) request.getAttribute("listCategories");
String message = (String) request.getSession().getAttribute("message");
String messageType = (String) request.getSession().getAttribute("messageType");
request.getSession().removeAttribute("message");
request.getSession().removeAttribute("messageType");
%>
<h1 id="title-page">Quản lý sách</h1>
<div class="overflow">
	<table class="content-table">
		<thead>
			<tr>
				<td>ID</td>
				<td style="min-width: 150px;">Tên</td>
				<td>Ảnh</td>
				<td style="min-width: 200px;">Tác giả</td>
				<td>Năm phát hành</td>
				<td style="min-width: 150px;">Danh mục sách</td>
				<td style="min-width: 100px;">Giá</td>
				<td style="min-width: 100px;">Giảm giá</td>
				<td style="min-width: 500px;">Chi tiết</td>
				<td style="min-width: 150px;">Tóm tắt</td>
				<td style="min-width: 50px;">Trạng thái</td>
				<td style="min-width: 150px;">Ngày tạo</td>
				<td style="min-width: 150px;">Người tạo</td>
				<td style="min-width: 150px;">Ngày sửa</td>
				<td style="min-width: 150px;">Người sửa</td>
				<td style="min-width: 100px;">Thao tác</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="adminBookView" items="${listAdminBookViews}">
				<tr id="tr-${adminBookView.getId()}">

					<td>${adminBookView.getId()}</td>
					<td>${adminBookView.getName()}</td>
					<td><img
						src="data:image/png;base64,${adminBookView.getImageBase64()}"
						style="max-height: 200px; max-width: 200px;" /></td>
					<td>${adminBookView.getAuthor()}</td>
					<td>${adminBookView.getRelease_year()}</td>
					<td>${adminBookView.getCategory_name()}</td>
					<td>${adminBookView.getPrice()}</td>
					<td>${adminBookView.getPromote_price()}</td>
					<td>${adminBookView.getDescription()}</td>
					<td>${adminBookView.getSub_description()}</td>
					<td><a
						href="/admin/book/change-status?id=${adminBookView.getId()}">${adminBookView.getStatus()}</a>
					</td>
					<td>${adminBookView.getCreate_time_string()}</td>
					<td>${adminBookView.getCreate_by_name()}</td>
					<td>${adminBookView.getLast_update_time_string()}</td>
					<td>${adminBookView.getLast_update_by_name()}</td>
					<td>
						<button onclick="showEditModal('tr-${adminBookView.getId()}')">Sửa</button>
						<button
							onclick="window.location.href='/admin/book/delete?id=${adminBookView.getId()}'">Xóa</button>
					</td>

				</tr>
			</c:forEach>
		</tbody>
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
		%><a href="/admin/book?page=<%=i%>">
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
	<form method="post" enctype="multipart/form-data"
		action="/admin/book" id="add-form">
		<h3>Thêm sách</h3>
		<div class="form-data-text">
			<p>Tên</p>
			<input name="name" placeholder="Nhập tên" />
		</div>
		<div class="form-data-text">
			<p>Ảnh</p>
			<input name="image" type="file" accept=".jpg, .png"
				placeholder="Nhập ảnh" />
		</div>
		<div class="form-data-text">
			<p>Tên tác giả</p>
			<input name="author" placeholder="Nhập tên tác giả" />
		</div>
		<div class="form-data-text">
			<p>Năm phát hành</p>
			<input name="release_year" placeholder="Nhập năm phát hành" />
		</div>
		<div class="form-data-text">
			<p>Danh mục</p>
			<select name="category_id" id="status" placeholder="Trạng thái">
				<c:forEach var="category" items="${listCategories}">
					<option value="${category.getId()}">${category.getName()}
					</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-data-text">
			<p>Giá tiền</p>
			<input name="price" placeholder="Giá tiền" />
		</div>
		<div class="form-data-text">
			<p>Giá giảm</p>
			<input name="promote_price" placeholder="Giá giảm" />
		</div>
		<div class="form-data-text">
			<p>Mô tả</p>
			<textarea name="description" placeholder="Mô tả" style="min-height: 200px"></textarea>
		</div>
		<div class="form-data-text">
			<p>Tóm tắt</p>
			<textarea name="sub_description" placeholder="Tóm tắt" style="min-height: 100px"></textarea>
		</div>
		<div class="form-data-button">
			<button type="submit">Thêm</button>
			<button type="reset">Hoàn tác</button>
			<button type="button" id="add-cancel" onclick="closeAddModal()">Hủy</button>
		</div>
	</form>
</div>
<div id="edit-modal" class="modal">
	<form method="post" enctype="multipart/form-data"
		action="/admin/book" id="edit-form">
		<h3>Sửa sách</h3>
		<input id="edit-hidden" type="hidden" name="id" value="" />
		<div class="form-data-text">
			<p>Tên</p>
			<input id="edit-name" name="name" placeholder="Nhập tên" />
		</div>
		<div class="form-data-text">
			<p>Ảnh</p>
			<input name="image" type="file" accept=".jpg, .png"
				placeholder="Nhập ảnh" />
		</div>
		<div class="form-data-text">
			<p>Tên tác giả</p>
			<input id="edit-author" name="author" placeholder="Nhập tên tác giả" />
		</div>
		<div class="form-data-text">
			<p>Năm phát hành</p>
			<input id="edit-release_year" name="release_year"
				placeholder="Nhập năm phát hành" />
		</div>
		<div class="form-data-text">
			<p>Danh mục</p>
			<select name="category_id" id="select_category_id"
				placeholder="Trạng thái">
				<c:forEach var="category" items="${listCategories}">
					<option value="${category.getId()}">${category.getName()}
					</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-data-text">
			<p>Giá tiền</p>
			<input id="edit-price" name="price" placeholder="Giá tiền" />
		</div>
		<div class="form-data-text">
			<p>Giá giảm</p>
			<input id="edit-promote_price" name="promote_price"
				placeholder="Giá giảm" />
		</div>
		<div class="form-data-text">
			<p>Mô tả</p>
			<textarea id="edit-description" name="description"
				placeholder="Mô tả"></textarea>
		</div>
		<div class="form-data-text">
			<p>Tóm tắt</p>
			<textarea id="edit-sub_description" name="sub_description"
				placeholder="Tóm tắt"></textarea>
		</div>
		<div class="form-data-button">
			<button type="submit">Sửa</button>
			<button type="button" id="edit-cancel" onclick="closeEditModal()">Hủy</button>
		</div>
	</form>
</div>
<script src="/admin/resources/js/content.js"
	type="text/javascript"></script>
<script src="/admin/resources/js/book.js" type="text/javascript"></script>