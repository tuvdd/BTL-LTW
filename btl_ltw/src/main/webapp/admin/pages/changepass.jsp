<%@ page import="java.util.*, servlets.admin.ServletUtil, models.*"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String message = (String) request.getSession().getAttribute("message");
String messageType = (String) request.getSession().getAttribute("messageType");
request.getSession().removeAttribute("message");
request.getSession().removeAttribute("messageType");
%>
<h1>Đổi mật khẩu</h1>
<form method="POST" action="/admin/changepass">
	<label for="oldpass">Mật khẩu cũ:</label>
	 <input type="password"	name="oldpass" id="oldpass" /> 
	 <br />
	<label for="newpass">Mật khẩu mới:</label> 
	<input type="password" name="newpass" id="newpass" />
	<br>
	<label for="confirmnewpass">Xác nhận mật khẩu mới:</label> 
	<input type="password" name="confirmnewpass" id="confirmnewpass" />
	<br>
	<button type="submit">Đổi mật khẩu</button>
	<p class="alert-<%=messageType%>">
		<%=(message != null ? message : "")%>
	</p>
</form>