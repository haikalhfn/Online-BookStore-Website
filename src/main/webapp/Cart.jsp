<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="config.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Purchase List</title>
</head>
<body background ="images/white.jpg">


	<div id="center">
		<div class="products-table">
     <h1>PURCHASE LIST</h1>
			<c:set var="totalPrice" value="0.0" />

			<%-- Loop all products in cart here --%>
			<c:forEach items="${cartList}" var="cart">

				<form class="product" action="cart" method="post">
					<input type="hidden" name="cartActivity" value="removeFromCart">
					<input type="hidden" name="cartId" value="${ cart.id }"> <img
						src="${ cart.product.image }" alt="product-image"  width="200" height="200">

					<div class="product-desc">
                        <b>Book name:</b><span class="product-name">${cart.product.name}</span><br>
						<b>Book description:</b><span class="product-description">${cart.product.description} </span><br>
						<b>Book publish:</b><span class="product-publish">${cart.product.publish} </span><br>
						 <b>Category:</b><span class="product-category">${cart.product.category} </span><br>
<b>______________________________________________________________________________________________________________________________________________________________</b>			

						<%-- 
						<span class="product-buttons">
							<button>Remove From Cart</button>
						</span>
						--%>
					</div>
				</form>

				<c:set var="totalPrice" value="${ totalPrice + cart.product.price }" />
            <c:set var="customerEmail" value="${ cart.customer.email }" />
			</c:forEach>

		</div>
	</div>

	<div id="cart-footer">
		<div class="grid-item">
			<h2><b>Name: ${ username } <br> Phone: ${ phone } <br> Email Address: ${ customerEmail }
			<br> Shipping Method: via Email</b></h2>
  
		</div>
		<div class="grid-item">

			<%-- Check if there is item in cart --%>
			
			          <a href="order">
						<button>Sales Report</button>
						</a>
		
			            <h1></h1><a href="Logout">
						<button>Logout</button>
						</a></h1>	
					

		</div>
	</div>
</body>
</html>