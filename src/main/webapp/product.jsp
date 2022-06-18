<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="config.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Table</title>
</head>
<body background ="images/white.jpg">
	

	<div id="center">
		<div class="products-table">

			<h1>Books Table</h1>

			<table border ="1" style="background-color: white";>
				<tr>
					<th>Book Id</th>
					<th>Book Image</th>
					<th>Book Name</th>
					<th>Book Description</th>
					<th>Year Publish</th>
					<th>Category</th>
					<th>Price(RM)</th>
					<th>Quantity </th>
					<th>Update</th>
					<th>Delete</th>
				</tr>

				<%-- Loop all products here --%>
				<c:forEach items="${productList}" var="product">
					<tr>
						<td>${ product.id }</td>
						<td>
							<img class="product-image" src="${ product.image }"
								alt="product-image" width="200" height="200">
						</td>
						<td>${ product.name }</td>
						<td>${ product.description }</td>
						<td>${ product.publish }</td>
						<td>${ product.category }</td>
						<td>${  CurrencyFormatter.format(product.price) }</td>
						<td>${ product.quantity }</td>
						<td>
							<a href="product?updateProduct=true&productId=${ product.id }">
								<button id="${ product.id }" class="update-button">Update</button>
							</a>
						</td>
						<td>
						<a href="product?deleteProduct=true&productId=${ product.id }" >
							 <button id="${ product.id }" class="delete-button">Delete</button>
						</a>
						</td>
					</tr>
				</c:forEach>

			</table>

		</div>
	</div>

	<div class="navbar">
		<div class="navbar-flex">
			<div class="navbar-grid">
				
			
			<a href="product?addProduct=true">
				<button>Add Book</button>
			</a>
				
				
				
				<a href="Logout">
				<button>Logout</button>
			</a>
			</div>
		</div>
	</div>

	</body>
</html>