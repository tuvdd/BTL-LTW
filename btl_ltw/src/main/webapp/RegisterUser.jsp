<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<link rel="stylesheet" href="/btl_ltw/admin/resources/css/style.css" />
</head>

<body>
	<div>
		<div id="login-form" class="container center _80pc login-panel">
			<img class="center" src="/btl_ltw/admin/resources/img/logo.png"
				style="width: 30%; margin-left: 35%; margin-bottom: 15px;" />
			<h3 class="center form-header">Đăng Kí</h3>
			<!-- <h4 class="center form-header">hihi</h4> -->
			<form class="center" method="post" action="/btl_ltw/user/register">
				<div class="form-label">
					<img class="form-label-icon" src="/btl_ltw/admin/resources/img/email.png">
					<label class="form-label-text">Email:</label>
				</div>
				<input type="text" name="email">
                <div class="form-label">
					<img class="form-label-icon" src="/btl_ltw/admin/resources/img/phonecall.png">
					<label class="form-label-text">Số điện thoại:</label>
				</div>
				<input type="text" name="phoneNumber">
				<div class="form-label">
					<img class="form-label-icon" src="/btl_ltw/admin/resources/img/usericon.png">
					<label class="form-label-text">Tên đăng nhập:</label>
				</div>
				<input type="text" name="username">
				<div class="form-label">
					<img class="form-label-icon" src="/btl_ltw/admin/resources/img/passicon.png">
					<label class="form-label-text">Mật khẩu:</label>
				</div>
				<input type="password" name="password"> <br />

				<%
				String error = (String) request.getSession().getAttribute("error");
				if (error != null) {
				%>

				<p style="color: red; font-size: 15px;"><%=error%></p>
				<%
				request.getSession().removeAttribute("error");
				}
				%>

				<button class="btn1 right" type="submit">Đăng kí</button>
			</form>
		</div>
	</div>
</body>

</html>