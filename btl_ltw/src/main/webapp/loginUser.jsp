<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet" href="/btl_ltw/admin/resources/css/style.css" />
</head>

<body>
	<div>
		<div id="login-form" class="container center _80pc login-panel">
			<img class="center" src="/btl_ltw/admin/resources/img/logo.png"
				style="width: 30%; margin-left: 35%; margin-bottom: 15px;" />
			<h3 class="center form-header">Đăng nhập</h3>
			<!-- <h4 class="center form-header">hihi</h4> -->
			<form class="center" method="post" action="/btl_ltw/user/login">
                <div class="form-label">
					<img class="form-label-icon" src="/btl_ltw/admin/resources/img/phonecall.png">
					<label class="form-label-text">Số điện thoại:</label>
				</div>
				<input type="text" name="phoneNumber">
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
				
				<div class="btn-form">
					<div class="btn-login">
						<button class="btn1" type="submit">Đăng nhập</button>
					</div>
					
					<div class="btn-register">
						<p>Chưa có tài khoản? </p>
						<a class="underline-link" href="/btl_ltw/user/register">Đăng ký</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>