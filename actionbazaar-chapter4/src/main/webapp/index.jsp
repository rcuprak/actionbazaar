<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Message Driven Bean Demo</title>
</head>
<body>	
	Fill the form below and submit to send a MDB to Turtle Shipping Company 
	<form method="post" action="ActionBazaarShippingRequestServlet">
		<pre>Item             <input name="Item"/><br>
		<pre>Shipping Address <input name="ShippingAddress"/><br>
		<pre>Shipping Method  <input name="ShippingMethod"/><br>
		<pre>Insurance Amount <input name="InsuranceAmount"/><br>
		<input type="submit"><br>
	</form>

</body>
</html>