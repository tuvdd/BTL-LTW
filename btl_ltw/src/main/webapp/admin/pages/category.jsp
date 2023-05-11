<%@ page
	import="java.util.*, servlets.admin.ServletUtil, models.*"
	language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<Category> listCategories = (List<Category>) request.getAttribute("listCategories");
%>
<h1 id="title-page">Danh Mục Sản Phẩm</h1>
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