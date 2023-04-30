<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="left-sidebar">
	<h1 class="left-sidebar-item">Hệ thống quản lý bán sách</h1>
	<script type="text/javascript">
		function redirect(url) {
			window.location.href = url;
		}
	</script>
	<div class="left-sidebar-item" style="margin-top: 20px;">
		<p style="color: white; font-size: 14; text-align: left; margin: 0;">Trang chủ</p>
		<hr />
		<button class="left-sidebar-item-button"
			onclick="redirect('/btl_ltw')">
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/home.png" />
			<p class="left-sidebar-item-button-text">Trang chủ người dùng</p>
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/next.png" style="float: right;" />
		</button>

		<button class="left-sidebar-item-button"
			onclick="redirect('/btl_ltw/admin')">
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/home.png" />
			<p class="left-sidebar-item-button-text">Trang chủ quản lý</p>
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/next.png" style="float: right;" />
		</button>
	</div>
	<div class="left-sidebar-item" style="margin-top: 20px;">
		<p style="color: white; font-size: 14; text-align: left; margin: 0;">Quản
			lý</p>
		<hr />
		<button class="left-sidebar-item-button"
			onclick="redirect('/btl_ltw/admin/user')">
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/user.png" />
			<p class="left-sidebar-item-button-text">Quản lý nhân viên</p>
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/next.png" style="float: right;" />
		</button>
		<button class="left-sidebar-item-button"
			onclick="redirect('/btl_ltw/admin/category')">
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/list.png" />
			<p class="left-sidebar-item-button-text">Quản lý danh mục</p>
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/next.png" style="float: right;" />
		</button>
		<button class="left-sidebar-item-button"
			onclick="redirect('/btl_ltw/admin/book')">
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/book.png" />
			<p class="left-sidebar-item-button-text">Quản lý sách</p>
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/next.png" style="float: right;" />
		</button>
		<button class="left-sidebar-item-button"
			onclick="redirect('/btl_ltw/admin/order')">
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/order.png" />
			<p class="left-sidebar-item-button-text">Quản lý đơn hàng</p>
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/next.png" style="float: right;" />
		</button>
		<button class="left-sidebar-item-button"
			onclick="redirect('/btl_ltw/admin/email')">
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/email.png" />
			<p class="left-sidebar-item-button-text">List Email nhận tin</p>
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/next.png" style="float: right;" />
		</button>

	</div>
	<div class="left-sidebar-item" style="margin-top: 20px;">
		<button class="left-sidebar-item-button"
			onclick="redirect('/btl_ltw/admin/logout')">
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/logout.png" />
			<p class="left-sidebar-item-button-text">Đăng xuất</p>
			<img class="left-sidebar-item-button-img"
				src="/btl_ltw/admin/resources/img/next.png" style="float: right;" />
		</button>

	</div>
</div>