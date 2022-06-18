<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="java.config.*"%>
<!DOCTYPE html>
<html>
<head>
<head>
<title>Report Form</title>


<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body background ="images/white.jpg">


	<div id="center">
		<div class="suppliers-table">

			<h2>Sales Report</h2>
			<table border ="1" style="background-color: white";> 
				<tr>
					<th></th>
					<th>Name</th>
					<th>Book Purchase</th>
					<th>Price(RM)</th>
					<th>Email</th>
				</tr>

				<c:set var="counter" value="0" />
				<c:set var="totalSales" value="0.0" />

				<%-- Loop all purchases here --%>
				<c:forEach items="${cartList}" var="cart">
					<c:set var="counter" value="${ counter + 1}" />
					<tr>
						<td>${ counter }</td>
						<td>${ cart.customer.username }</td>
						<td>${ cart.product.name }</td>
						<td>${ CurrencyFormatter.format(cart.product.price) }</td>
						<td>${ cart.customer.email }</td>
					</tr>
					<c:set var="totalSales" value="${ totalSales + cart.product.price }" />
				</c:forEach>

				<tr>
					<td colspan="4"><b>Total Sales</b></td>
					<td colspan="1"><b>RM${ CurrencyFormatter.format(totalSales) }</b></td>
				</tr>

			</table>

		</div>
	</div>

	<div class="navbar">
		<div class="navbar-flex">
			<div class="navbar-grid">
			<br>
				<a href="Logout">
					<button>Logout</button>
				</a>
				
			</div>
		</div>
	</div>

</body>
</html>