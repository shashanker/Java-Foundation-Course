<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Purchase</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body>
<div class="col-md-3 col-md-offset-5">
	<c:if test="${not empty error}"><div>${error}</div></c:if>
	<c:if test="${not empty message}"><div>${message}</div></c:if>
    <h2>Edit Purchase!</h2>
   <%--  <form:form  action="<c:url value='/editPurchase' />" method='POST' modelAttribute="purchase"> --%>
   <form:form action="${pageContext.request.contextPath}/editPurchase" method="POST" modelAttribute="purchase">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" value=${purchase.stock.name } readonly="readonly"  />
           <!--  <div  class="help-block">Username is required</div> -->
        </div>
        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="text" class="form-control" name="quantity" value="${purchase.quantity }"  required/>
        </div>
        
        <div class="form-group">
            <input type="hidden" class="form-control" name="id" value="${purchase.id }"  required/>
        </div>
        <div class="form-group">
            <button  class="btn btn-primary" name="submit" type="submit">Edit</button>
        </div>
    </form:form>
</div>
</body>
</html>