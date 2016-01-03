<%@page import="javax.naming.InitialContext"%>
<%@page import="com.actionbazaar.listing02.TurtleShippingStatistics"%>
<%@page import="java.util.List"%>
<%@page import="com.actionbazaar.listing02.TurtleShippingRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pending Shipping Request</title>
</head>
<body>
	The following requests are awaiting action:
	<br>
	<%
	    TurtleShippingStatistics stats = (TurtleShippingStatistics) new InitialContext()
	            .lookup("java:global/actionbazaar-chapter4/TurtleShippingStatistics");
	    List<TurtleShippingRequest> shippingRequests = stats.getPendingRequests();
	    request.setAttribute("shippingRequests", shippingRequests);
	%>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>Pending Requests</h2>
			</caption>
			<tr>
				<th>Id</th>
				<th>Item</th>
				<th>Shipping Address</th>
				<th>Shipping Method</th>
				<th>Insurance Amount</th>
			</tr>
			<c:forEach var="shippingRequest" items="${shippingRequests}">
				<tr>	
					<td><c:out value="${shippingRequest.id}" /></td>
					<td><c:out value="${shippingRequest.item}" /></td>
					<td><c:out value="${shippingRequest.shippingAddress}" /></td>
					<td><c:out value="${shippingRequest.shippingMethod}" /></td>
					<td><c:out value="${shippingRequest.insuranceAmount}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>