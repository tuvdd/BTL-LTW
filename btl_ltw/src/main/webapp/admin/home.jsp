<%@ page import="java.util.UUID,servlets.admin.ServletUtil"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String pageUri = "";
	
	if (request.getAttribute("isAccessFromServlet") == null){
		response.sendRedirect("/btl_ltw/admin"+pageUri);
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
		<jsp:include page="pages/left-sidebar.jsp" />
		<div id="content">
			<%%>
		</div>
	</div>
</body>

</html>