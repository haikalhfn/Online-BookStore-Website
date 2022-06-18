<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="config.*"%>
 
<!DOCTYPE html>
<html lang="en">

<head>
<!-- Load an icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<div class="navbar">
  <a class="active" href="#"><i class="fa fa-fw fa-home"></i> Home</a>
  <a href="#"><i class="fa fa-fw fa-search"></i> Search</a>
  <a href="#"><i class="fa fa-fw fa-envelope"></i> Contact</a>
  <a href="#"><i class="fa fa-fw fa-user"></i> Login</a>
</div>
	
	<meta  http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
	
	
	<title>Book Lists</title>
	<b>_______________</b>
	

</head>

<body background="images/white.jpg">



<h1>Book Lists</h1>




	 


<div id="center">
		<div class="products-table">

			<%-- Loop all products here --%>
			<c:forEach items="${productList}" var="product">


					<input type="hidden" class="cart-activity" name="cartActivity"
						value="addToCart"> <input type="hidden" class="product-id"
						name="productId" value="${ product.id }"> <img
						src="${product.image}" alt="product-image" width="200" height="200">

					<div class="product-desc">
						<b>Book name:</b><span class="product-name">${product.name}</span><br>
						<b>Book description:</b><span class="product-description">${product.description} </span><br>
						<b>Book publish:</b><span class="product-publish">${product.publish} </span><br>
						 <b>Category:</b><span class="product-category">${product.category} </span><br>
							
						<b>Price:</b><span class="product-price">RM${ CurrencyFormatter.format(product.price) }
							| <b>Quantity:</b> ${ product.quantity }</span>
							
							<a href="cart?Productadd=true&productId=${ product.id }" >
							
							  <button id="${ product.id }" class="buy">Buy</button>
							</a>

<b>############################################################################################################################################################################</b><br>						
						

						
					</div>
				
			</c:forEach>

		</div>

	</div>
    <script type="text/javascript" src="./static/home.js"></script>
</body>

</html>