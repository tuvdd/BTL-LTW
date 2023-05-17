<%@ page
	import="java.util.*, servlets.admin.ServletUtil, models.dtos.*, models.*"
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
		<tr>
			<td>ID</td>
			<td>Tên</td>
			<td>Ảnh</td>
			<td>Tác giả</td>
			<td>Năm phát hành</td>
			<td>Danh mục sách</td>
			<td>Giá</td>
			<td>Giảm giá</td>
			<td>Số lượng trong kho</td>
			<td>Chi tiết</td>
			<td>Tóm tắt</td>
			<td>Trạng thái</td>
			<td>Ngày tạo</td>
			<td>Người tạo</td>
			<td>Ngày sửa</td>
			<td>Người sửa</td>
			<td>Thao tác</td>
		</tr>
		<c:forEach var="adminBookView" items="${listAdminBookViews}">
			<tr>
				<td>${adminBookView.getId()}</td>
				<td>${adminBookView.getName()}</td>
				<td><img
					src="data:image/png;base64,${adminBookView.getImageBase64()}" /></td>
				<td>${adminBookView.getAuthor()}</td>
				<td>${adminBookView.getRelease_year()}</td>
				<td>${adminBookView.getCategory_name()}</td>
				<td>${adminBookView.getPrice()}</td>
				<td>${adminBookView.getPromote_price()}</td>
				<td>${adminBookView.getQuantity()}</td>
				<td>${adminBookView.getDescription()}</td>
				<td>${adminBookView.getSub_description()}</td>
				<td>${adminBookView.getStatus()}</td>
				<td>${adminBookView.getCreate_time()}</td>
				<td>${adminBookView.getCreate_by_name()}</td>
				<td>${adminBookView.getLast_update_time()}</td>
				<td>${adminBookView.getLast_update_by_name()}</td>
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
	<form method="post" enctype="multipart/form-data"
		action="/btl_ltw/admin/book" id="add-form">
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
					<option value="${category.getId()}">${category.getName()}</option>
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
			<p>Số lượng trong kho</p>
			<input name="quantity" placeholder="Số lượng trong kho" />
		</div>
		<div class="form-data-text">
			<p>Mô tả</p>
			<input name="description" placeholder="Mô tả" />
		</div>
		<div class="form-data-text">
			<p>Tóm tắt</p>
			<input name="sub_description" placeholder="Tóm tắt" />
		</div>
		<div class="form-data-text">
			<p>Trạng thái</p>
			<select name="status" id="status" placeholder="Trạng thái">
				<option value="1">Bán</option>
				<option value="2">Chưa bán</option>
				<option value="3">Dừng bán</option>
			</select>
		</div>
		<div class="form-data-button">
			<button type="submit">Thêm</button>
			<button type="reset">Hoàn tác</button>
			<button type="button" id="add-cancel" onclick="closeAddModal()">Hủy</button>
		</div>
	</form>
</div>
<script src="/btl_ltw/admin/resources/js/content.js"
	type="text/javascript"></script>