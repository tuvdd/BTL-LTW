<%@ page
	import="java.util.*, servlets.admin.ServletUtil, models.*"
	language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String pageUri = "/category";

if (request.getAttribute("isAccessFromServlet") == null) {
	response.sendRedirect("/btl_ltw/admin" + pageUri);
	return;
}

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
<link rel="stylesheet" href="/btl_ltw/admin/resources/css/style.css" />
<title>Admin/Home</title>
</head>

<body>
	<div id="flex-box">
		<jsp:include page="pages/left-sidebar.jsp"/>
		<div id="content">
			<%
			List<Category> listCategories = (List<Category>) request.getAttribute("listCategories");
			%>
			<table class="content-table">
				<tr>
					<th>ID</th>
					<th>Tên</th>
					<th>Trạng thái</th>
				</tr>
				<c:forEach var="category" items="${listCategories}">
					<tr>
						<td>${category.getId()}</td>
						<td>${category.getName()}</td>
						<td>${category.getStatus()}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>