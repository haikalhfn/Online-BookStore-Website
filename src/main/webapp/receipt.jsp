<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="config.*"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<div id="center">
		<div class="resit">
			<div class="resit-top">
				Order Resit #<%=(new Date()).getTime()%></div>
			<table id="user-details"  border ="1" style="background-color: white";> 
				<tr>
					<th>Name</th>
					<th>Phone</th>
					<th>Send to Email</th>
					
				</tr>

				<tr>
					<td>${ name }</td>
					<td>${ phone }</td>
					<td>${ email }</td>
					
				</tr>
			</table>

			<br>

			<table id="order-details"  border ="1" style="background-color: white";>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Price(RM)</th>

				</tr>

				<c:set var="totalPrice" value="0.0" />

				<c:forEach items="${purchaseList}" var="purchase">
					<tr>
						<td>${ purchase.product.name }</td>
						<td>${ purchase.quantity }</td>
						<td>${ CurrencyFormatter.format(purchase.product.price) }</td>
					</tr>

					<c:set var="totalPrice" value="${ totalPrice + purchase.price }" />
				</c:forEach>

				<tr>
					<td colspan="2"><b>Total Price</b></td>
					<td><b>${ CurrencyFormatter.format(totalPrice) }</b></td>
				</tr>
			</table>
			<br>
			<button onclick="location.href = 'home';">Go To Home</button>
		</div>
	</div>

</body>

</html>