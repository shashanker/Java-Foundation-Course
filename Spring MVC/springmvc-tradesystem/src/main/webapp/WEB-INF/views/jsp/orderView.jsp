<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/main.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/product.css" />
<style>
.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	max-width: 300px;
	margin: auto;
	text-align: center;
	font-family: monospace;
}

.price {
	color: grey;
	font-size: 22px;
}

.card button {
	border: none;
	outline: 0;
	padding: 12px;
	color: white;
	background-color: #000;
	text-align: center;
	cursor: pointer;
	width: 100%;
	font-size: 18px;
}

.card button:hover {
	opacity: 0.7;
}
</style>
</head>
<script type="text/javascript">
function orderPurchase(id){
	document.orderForm.method="post";
	document.orderForm.action="${pageContext.request.contextPath}/orderPurchase/"+id;
	document.orderForm.submit();
}
</script>
<body>
	<form name="orderForm">
		<%-- <div align="right">
			<span class="product-grid__btn product-grid__add-to-cart"
				onclick="showCart();"> <i class="fa fa-cart-arrow-down">
					${cart!=null?cart.size():0} items</i></span>
		</div> --%>

		<h2 style="text-align: center">Order Details</h2>

		<div class="card">
			<img height="200" width="200"
				src="${pageContext.request.contextPath }/resources/images/${purchase.stock.imgUrl}"
				alt="${purchase.stock.imgUrl }">
			<h1>${purchase.stock.name }</h1>
			<p class="price">${purchase.value }</p>
			<p style="text-align: left;">${purchase.stock.desc}</p>
			<p>
				<span class="product-grid__btn product-grid__add-to-cart"
					onclick="orderPurchase(${purchase.id})"> <i
					class="fa fa-cart-arrow-down"></i> Confirm Order
				</span> 
				<a href="${pageContext.request.contextPath }"
					class="btn btn-danger" style="margin-left: 20px">Go Back</a>
			</p>
		</div>
	</form>
</body>
</html>
