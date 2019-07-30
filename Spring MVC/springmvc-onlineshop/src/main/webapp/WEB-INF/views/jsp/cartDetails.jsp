<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart Details</title>
<link rel="icon" type="image/x-icon"
	href="<c:url value="/resource/images/favicon1.png"/>" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script> -->
</head>
<script type="text/javascript">
	function cartRemove(id){
		document.cartDetailsForm.method="post";
		document.cartDetailsForm.action="${pageContext.request.contextPath}/cartRemove/"+id;
		document.cartDetailsForm.submit();
	}
	
	function checkout(){
		document.cartDetailsForm.method="post";
		document.cartDetailsForm.action="${pageContext.request.contextPath}/checkout";
		document.cartDetailsForm.submit();
	}
</script>
<body>
	<form name="cartDetailsForm">
		<div class="container"
			style="width: 1145px; margin-top: 20px; margin-bottom: 180px;">
			<div style="margin-bottom: 30px">
				<div>
					<br> List of Products Purchased
					<div>

						<a href="${pageContext.request.contextPath }/clearCart"
							class="btn btn-danger pull-left"
							style="margin-top: 15px; margin-left: 20px"> <span
							class="glyphicon glyphicon-remove-sign"> </span>Clear Cart
						</a>
					</div>
					<div>
						<c:if test="${cart!=null &&  cart.size() != 0}">
							<a onClick="checkout()" class="btn btn-danger pull-left"
								style="margin-top: 15px; margin-left: 20px"> <span
								class="glyphicon glyphicon-shipping-cart"> </span>Check Out
							</a>
						</c:if>
					</div>
					<table class="table table-hover" id="productList">
						<thead>
							<tr>
								<th>Product Name</th>
								<th>Quantity</th>
								<th>Price</th>
								<th>Total Price</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="totalAmt" value="0"></c:set>
							<c:forEach items="${cart}" var="cartItem">
								<c:set var="product" value="${cartItem.key}"></c:set>
								<tr>
									<td>${product.name}</td>
									<td>${cartItem.value}</td>
									<td>${product.price}</td>
									<td>$${cartItem.value * product.price}</td>
									<td><a onClick="cartRemove(${product.id})"
										class="btn btn-danger"
										<c:set var="totalAmt" value="${totalAmt + (cartItem.value * product.price)}"></c:set>
										style="margin-top: 0px;"> <span
											class="glyphicon glyphicon-trash"></span>remove
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<b>Grand Total Price:</b> $${totalAmt}
				</div>

				<a href="${pageContext.request.contextPath }"
					class="btn btn-danger" style="margin-left: 20px">Continue
					Shopping</a>
			</div>
		</div>
	</form>
</body>
</html>