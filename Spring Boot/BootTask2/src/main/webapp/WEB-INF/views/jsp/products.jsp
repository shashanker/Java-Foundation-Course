
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
<title>Products Page</title>
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
</head>
<script type="text/javascript">
	function showDetail(id) {
		document.productForm.action="${pageContext.request.contextPath }/showDetail/"+id;
		document.productForm.submit();
		
	}
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
	<div align="right">
		<span class="product-grid__btn product-grid__add-to-cart" onclick="showCart();"> <i
			class="fa fa-cart-arrow-down">  ${cart!=null?cart.size():0} items</i></span>
	</div>
	<div class="wrapper">


		<div class="content">
			<!-- content here -->
			<div class="product-grid product-grid--flexbox">
				<div class="product-grid__wrapper">
					<form action="" name="productForm">
						<!-- Product list start here -->
						<c:forEach items="${products}" var="product">
							<!-- Single product -->
							<div class="product-grid__product-wrapper">
								<div class="product-grid__product">
									<div class="product-grid__img-wrapper"
										onClick="showDetail(${product.id})">
										<img
											src="${pageContext.request.contextPath }/resources/images/${product.imgUrl }"
											alt="${product.imgUrl }" class="product-grid__img" />
									</div>
									<span class="product-grid__title">${product.name}</span> <span
										class="product-grid__price">$${product.price}</span>
									<div class="product-grid__extend-wrapper">
										<div class="product-grid__extend">
											<%-- <p class="product-grid__description">${product.desc }</p> --%>
											<span class="product-grid__btn product-grid__add-to-cart" onclick="addToCart(${product.id})"><i
												class="fa fa-cart-arrow-down"
												></i> Add to cart</span>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>