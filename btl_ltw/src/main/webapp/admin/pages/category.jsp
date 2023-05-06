<%@ page
	import="java.util.*, servlets.admin.ServletUtil, models.*"
	language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<Category> listCategories = (List<Category>) request.getAttribute("listCategories");
    String message = (String) request.getSession().getAttribute("message");
    String messageType = (String) request.getSession().getAttribute("messageType");
    request.getSession().removeAttribute("message");
    request.getSession().removeAttribute("messageType");
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
<p class="alert-<%=messageType%>"><%=(message!=null?message:"")%></p>
<button id="add-new-button">Thêm mới</button>
<div id="add-new-modal">
    <form method="post" action="/btl_ltw/admin/category" id="add-new-form">
        <input name="name" placeholder="Nhập tên danh mục"/>
        <button type="submit">Thêm</button>
    </form>
</div>
<script src="/btl_ltw/admin/resources/js/main.js" type="text/javascript"></script>