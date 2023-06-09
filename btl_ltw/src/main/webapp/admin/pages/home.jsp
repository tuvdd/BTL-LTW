<%@ page
	import="java.util.List,java.math.BigDecimal, java.text.DecimalFormat,models.dtos.BookDashboardDTO, servlets.admin.ServletUtil"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
int countO0 = (int) request.getAttribute("Số lượng đơn chờ xác nhận");
int countO1 = (int) request.getAttribute("Số lượng đơn chờ thanh toán");
int countO2 = (int) request.getAttribute("Số lượng đơn chờ vận chuyển");
BigDecimal totalThang = (BigDecimal) request.getAttribute("Tổng doanh thu tháng");
BigDecimal totalNam = (BigDecimal) request.getAttribute("Tổng doanh thu năm");

DecimalFormat df = new DecimalFormat("#,### VND");

String message = (String) request.getSession().getAttribute("message");
String messageType = (String) request.getSession().getAttribute("messageType");
request.getSession().removeAttribute("message");
request.getSession().removeAttribute("messageType");
%>

<h1 id="title-page">Trang chủ</h1>

<p>
	Số lượng đơn chờ xác nhận:
	<%=countO0%>
</p>
<p>
	Số lượng đơn chờ thanh toán:
	<%=countO1%>
</p>
<p>
	Số lượng đơn chờ vận chuyển:
	<%=countO2%>
</p>
<p>
	Tổng doanh thu tháng:
	<%=df.format(totalThang.intValue())%>
</p>
<p>
	Tổng doanh thu năm:
	<%=df.format(totalNam.intValue())%>
</p>

<script src="/btl_ltw/admin/resources/js/home.js" type="text/javascript"></script>