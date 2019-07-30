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
function addToCart(id){
	document.productForm.method="post";
	document.productForm.action="${pageContext.request.contextPath }/addToCart/"+id;
	document.productForm.submit();
}
function showCart(){
	document.productForm.action="${pageContext.request.contextPath }/showCart";
	document.productForm.submit();
}
</script>
<body>
	<form name="productForm">
		<div align="right">
			<span class="product-grid__btn product-grid__add-to-cart"
				onclick="showCart();"> <i class="fa fa-cart-arrow-down">
					${cart!=null?cart.size():0} items</i></span>
		</div>

		<h2 style="text-align: center">Product Card</h2>

		<div class="card">
			<img
				src="${pageContext.request.contextPath }/resources/images/${product.imgUrl }"
				alt="${product.imgUrl }">
			<h1>${product.name }</h1>
			<p class="price">$${product.price }</p>
			<p style="text-align: left;">${product.desc }</p>
			<p>
				<span class="product-grid__btn product-grid__add-to-cart"
					onclick="addToCart(${product.id})"> <i
					class="fa fa-cart-arrow-down"></i> Add to cart
				</span>
			</p>
		</div>
	</form>
</body>
</html>
