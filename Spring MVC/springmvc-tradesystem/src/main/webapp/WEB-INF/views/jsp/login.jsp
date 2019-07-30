<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body>

<div class="col-md-6 col-md-offset-3">
	<c:if test="${not empty error}"><div>${error}</div></c:if>
	<c:if test="${not empty message}"><div>${message}</div></c:if>
    <h2>Login</h2>
    <form name='login' action="<c:url value='/loginPage' />" method='POST'>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" required />
            <div  class="help-block">Username is required</div>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" required />
            <div class="help-block">Password is required</div>
        </div>
        <div class="form-group">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button  class="btn btn-primary" name="submit" type="submit">Login</button>
           <a href="<c:url value="/registration"/>"
									class="btn btn-sm btn-success">Register</a>
        </div>
    </form>
</div>

</body>
</html>