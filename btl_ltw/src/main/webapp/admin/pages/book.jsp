<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*" language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<% List<Book> listBooks = (List<Book>) request.getAttribute("listBooks");
				String message = (String) request.getSession().getAttribute("message");
				String messageType = (String) request.getSession().getAttribute("messageType");
				request.getSession().removeAttribute("message");
				request.getSession().removeAttribute("messageType");
				%>
				<h1 id="title-page">Quản lý sách</h1>
				<div class="overflow">
					<table class="content-table">
						<tr>
							<td>Tên</td>
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
						<c:forEach var="book" items="${listBooks}">
							<tr>
								<td>${book.getName()}</td>
								<td>${book.getAuthor()}</td>
								<td>${book.getRelease_year()}</td>
								<td>${book.getCategory_id()}</td>
								<td>${book.getPrice()}</td>
								<td>${book.getPromote_price()}</td>
								<td>${book.getQuantity()}</td>
								<td>${book.getDescription()}</td>
								<td>${book.getSub_description()}</td>
								<td>${book.getStatus()}</td>
								<td>${book.getCreate_time()}</td>
								<td>${book.getCreate_by()}</td>
								<td>${book.getLast_update_time()}</td>
								<td>${book.getLast_update_by()}</td>
								<td>
									<button onclick="showEditModal()">Sửa</button>
									<button>Xóa</button>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<p class="alert-<%=messageType%>">
					<%=(message !=null ? message : "" )%>
				</p>
				<button id="add-new-button" onclick="showAddModal()">Thêm mới</button>
				<div id="add-modal" class="modal">
					<form method="post" action="/btl_ltw/admin/admin" id="add-form">
						<h3>Thêm danh mục</h3>
						<input name="name" placeholder="Nhập tên danh mục" />
						<button type="submit">Thêm</button>
						<button type="reset">Hoàn tác</button>
						<button type="button" id="add-cancel" onclick="closeAddModal()">Hủy</button>
					</form>
				</div>

				<div id="edit-modal" class="modal">
					<form method="put" action="/btl_ltw/admin/admin" id="edit-form">
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
				<script src="/btl_ltw/admin/resources/js/content.js" type="text/javascript"></script>