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
	function purchaseRemove(id){
		document.purchaseViewForm.method="post";
		document.purchaseViewForm.action="${pageContext.request.contextPath}/purchaseRemove/"+id;
		document.purchaseViewForm.submit();
	}
	
	function checkout(){
		document.purchaseViewForm.method="post";
		document.purchaseViewForm.action="${pageContext.request.contextPath}/checkout";
		document.purchaseViewForm.submit();
	}
	
	function orderView(id){
		document.purchaseViewForm.method="get";
		document.purchaseViewForm.action="${pageContext.request.contextPath}/orderView/"+id;
		document.purchaseViewForm.submit();
	}
	
	function editPurchase(id){
		document.purchaseViewForm.method="get";
		document.purchaseViewForm.action="${pageContext.request.contextPath}/editPurchase/"+id;
		document.purchaseViewForm.submit();
	}
</script>
<body>
	<form name="purchaseViewForm">
		<div class="container"
			style="width: 1145px; margin-top: 20px; margin-bottom: 180px;">
			<h2>${msg }</h2>
			<div style="margin-bottom: 30px">
				<div>
					<br> List of Products Purchased
					<%-- <div>

						<a href="${pageContext.request.contextPath }/clearCart"
							class="btn btn-danger pull-left"
							style="margin-top: 15px; margin-left: 20px"> <span
							class="glyphicon glyphicon-remove-sign"> </span>Clear Cart
						</a>
					</div>
					<div>
						<c:if test="${cart!=null &&  cart.intValue() != 0}">
							<a onClick="checkout()" class="btn btn-danger pull-left"
								style="margin-top: 15px; margin-left: 20px"> <span
								class="glyphicon glyphicon-shipping-cart"> </span>Check Out
							</a>
						</c:if>
					</div> --%>
					<table class="table table-hover" id="productList">
						<thead>
							<tr>
								<th>Name</th>
								<th>Quantity</th>
								<th>Price</th>
								<th>status</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:set var="totalAmt" value="0"></c:set>
							<c:forEach items="${purchases}" var="purchase">
								<%-- <c:set var="product" value="${cartItem.key}"></c:set> --%>
								<tr>
									<c:set var="totalAmt" value="${totalAmt + purchase.value}"></c:set>
									<td>${purchase.stock.name}</td>
									<td>${purchase.quantity}</td>
									<td>${purchase.value}</td>
									<td>${purchase.status }</td>
									<c:if test="${purchase.status eq 'Not-ordered'}">
									<td><a onClick="purchaseRemove(${purchase.id})"
										class="btn btn-danger"
										style="margin-top: 0px;"> <span
											class="glyphicon glyphicon-trash"></span>remove
									</a></td>
									<td>
									
									<a onClick="editPurchase(${purchase.id})"
										class="btn btn-danger"
										style="margin-top: 0px;"> <span
											class="glyphicon">Edit</span>
									</a>
									</td>
									<td>
									
									<a onClick="orderView(${purchase.id})"
										class="btn btn-danger"
										style="margin-top: 0px;"> <span
											class="glyphicon">Order</span>
									</a>
									</td>
									
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- <b>Grand Total Price:</b> $${totalAmt} -->
				</div>

				<a href="${pageContext.request.contextPath }"
					class="btn btn-danger" style="margin-left: 20px">Continue
					Trading</a>
			</div>
		</div>
	</form>
</body>
</html>