<%@ page import="java.util.UUID,servlets.admin.ServletUtil"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (!ServletUtil.IsSessionExsited(request, response)) {
		response.sendRedirect("/btl_ltw/admin/login");
		return;
	}
%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="resources/css/style.css" />
<title>Admin/Home</title>
</head>

<body>
	<div id="flex-box">
		<div id="left-sidebar">
			<h1 class="left-sidebar-item">Hệ thống quản lý bán sách</h1>
			<img class="left-sidebar-img" src="resources/img/logo.png" />
			<script type="text/javascript">
				function redirect(url) {
					window.location.href = url;
				}
			</script>
			<div class="left-sidebar-item" style="margin-top: 20px;">
				<p style="color: white; font-size: 14; text-align: left; margin: 0;">Trang
					chủ</p>
				<hr />
				<button class="left-sidebar-item-button"
					onclick="redirect('/btl_ltw')">
					<img class="left-sidebar-item-button-img"
						src="resources/img/home.png" />
					<p class="left-sidebar-item-button-text">Trang chủ người dùng</p>
					<img class="left-sidebar-item-button-img"
						src="resources/img/next.png" style="float: right;" />
				</button>

				<button class="left-sidebar-item-button"
					onclick="redirect('/btl_ltw/admin')">
					<img class="left-sidebar-item-button-img"
						src="resources/img/home.png" />
					<p class="left-sidebar-item-button-text">Trang chủ quản lý</p>
					<img class="left-sidebar-item-button-img"
						src="resources/img/next.png" style="float: right;" />
				</button>
			</div>
			<div class="left-sidebar-item" style="margin-top: 20px;">
				<p style="color: white; font-size: 14; text-align: left; margin: 0;">Quản
					lý</p>
				<hr />
				<button class="left-sidebar-item-button"
					onclick="redirect('/btl_ltw/admin/user')">
					<img class="left-sidebar-item-button-img"
						src="resources/img/user.png" />
					<p class="left-sidebar-item-button-text">Quản lý nhân viên</p>
					<img class="left-sidebar-item-button-img"
						src="resources/img/next.png" style="float: right;" />
				</button>
				<button class="left-sidebar-item-button"
					onclick="redirect('/btl_ltw/admin/category')">
					<img class="left-sidebar-item-button-img"
						src="resources/img/list.png" />
					<p class="left-sidebar-item-button-text">Quản lý danh mục</p>
					<img class="left-sidebar-item-button-img"
						src="resources/img/next.png" style="float: right;" />
				</button>
				<button class="left-sidebar-item-button"
					onclick="redirect('/btl_ltw/admin/book')">
					<img class="left-sidebar-item-button-img"
						src="resources/img/book.png" />
					<p class="left-sidebar-item-button-text">Quản lý sách</p>
					<img class="left-sidebar-item-button-img"
						src="resources/img/next.png" style="float: right;" />
				</button>
				<button class="left-sidebar-item-button"
					onclick="redirect('/btl_ltw/admin/order')">
					<img class="left-sidebar-item-button-img"
						src="resources/img/order.png" />
					<p class="left-sidebar-item-button-text">Quản lý đơn hàng</p>
					<img class="left-sidebar-item-button-img"
						src="resources/img/next.png" style="float: right;" />
				</button>
				<button class="left-sidebar-item-button"
					onclick="redirect('/btl_ltw/admin/email')">
					<img class="left-sidebar-item-button-img"
						src="resources/img/email.png" />
					<p class="left-sidebar-item-button-text">List Email nhận tin</p>
					<img class="left-sidebar-item-button-img"
						src="resources/img/next.png" style="float: right;" />
				</button>
				
			</div>
			<div class="left-sidebar-item" style="margin-top: 20px;">
				<button class="left-sidebar-item-button"
					onclick="redirect('/btl_ltw/admin/logout')">
					<img class="left-sidebar-item-button-img"
						src="resources/img/email.png" />
					<p class="left-sidebar-item-button-text">Đăng xuất</p>
					<img class="left-sidebar-item-button-img"
						src="resources/img/next.png" style="float: right;" />
				</button>
				
			</div>
		</div>
		<div id="content">
			<%%>
		</div>
	</div>
</body>

</html>