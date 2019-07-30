<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HomePage</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/main.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/product.css" />
	<script type="text/javascript">
	function addToCart(id){
		
		var qnty=document.getElementById("qnty_"+id).value;
		
		document.stockForm.method="post";
		document.stockForm.action="${pageContext.request.contextPath }/addToCart/"+id+"/"+qnty;
		document.stockForm.submit();
	}
	
	function showPurchases()
	{
		document.stockForm.method="get";
		document.stockForm.action="${pageContext.request.contextPath }/showPurchases";
		document.stockForm.submit();
	}
	</script>
</head>
<body>
	<c:url value="/logout" var="logoutUrl" />
	<div align="right">
		<span class="product-grid__btn product-grid__add-to-cart"
			onclick="showPurchases();"> <i class="fa fa-cart-arrow-down">
				${cart!=null?cart.intValue():0} items</i></span>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
							<a href="javascript:document.getElementById('logout').submit()">Logout</a>
						</c:if>
				
	</div>
	<div class="wrapper">
		<h2>Welcome ${pageContext.request.userPrincipal.name}</h2>

		<div class="content">
			<!-- content here -->
			<div class="product-grid product-grid--flexbox">
				<div class="product-grid__wrapper">
					<form id="logout" action="${logoutUrl}" method="post" name="stockForm">
						
						<!-- Product list start here -->
						<c:forEach items="${stocks}" var="stock">
							<!-- Single product -->
							<div class="product-grid__product-wrapper">
								<div class="product-grid__product">
									<div class="product-grid__img-wrapper"
										onClick="showDetail(${stock.id})">
										<img
											src="${pageContext.request.contextPath }/resources/images/${stock.imgUrl}"
											alt="${stock.imgUrl}" class="product-grid__img" />
									</div>
									<span class="product-grid__title">${stock.name}</span> 
									<span class="product-grid__price">${stock.price}</span>
									<span class="product-grid__title">Quantity <input type="text" name="qnty_${stock.id }" id="qnty_${stock.id }"></span> 
									<div class="product-grid__extend-wrapper">
										<div class="product-grid__extend">
											<%-- <p class="product-grid__description">${product.desc }</p> --%>
											<span class="product-grid__btn product-grid__add-to-cart"
												onclick="addToCart(${stock.id})"><i
												class="fa fa-cart-arrow-down"></i> Add to cart</span>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
					</form>
				</div>
			</div>
		</div>
	</div>



	<%-- <div align="center">
		<h2>Welcome ${pageContext.request.userPrincipal.name}</h2>

		<c:url value="/logout" var="logoutUrl" />
		<form id="logout" action="${logoutUrl}" method="post">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<a href="javascript:document.getElementById('logout').submit()">Logout</a>
			</c:if>
			<div align="right">
				<span class="product-grid__btn product-grid__add-to-cart"
					onclick="showCart();"> <i class="fa fa-cart-arrow-down">
						0 items</i></span>
			</div>

			<h2 style="text-align: center">Product Card</h2>

			<div class="card">
				<img
					src="${pageContext.request.contextPath }/resources/images/dollar.jpg }"
					alt="dollar.jpg">
				<h1>US Dollar</h1>
				<p class="price">100</p>
				<p style="text-align: left;">US Dollar</p>
				<p>
					<span class="product-grid__btn product-grid__add-to-cart"
						onclick="addToCart(123)"> <i class="fa fa-cart-arrow-down"></i>
						Add to cart
					</span>
				</p>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

	</div> --%>
</body>
</html>