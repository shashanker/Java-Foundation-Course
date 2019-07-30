<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Here</title>
<%-- <link rel="icon" type="image/x-icon"
	href="<c:url value="/resource/images/favicon1.png"/>" />
<link rel="stylesheet"
	href="<c:url value="/resource/bootstrap/css/bootstrap.min.css"/>">
<script src="<c:url value="/resource/js/jquery.js"/>"></script>
<script src="<c:url value="/resource/bootstrap/js/bootstrap.min.js"/>"></script> --%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

</head>
<body>
	<div class="col-md-3 col-md-offset-5">
	<h2>${msg}</h2>
    <h2>Register Here!</h2>
    <form:form name='register' action="${pageContext.request.contextPath}/registration" method='POST' modelAttribute="register">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" required />
            <div  class="help-block">Username is required</div>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" type="password" class="form-control" name="password" placeholder="********" required />
            <div class="help-block">Password is required</div>
        </div>
        <div class="form-group">
            <label for="password">Confirm Password</label>
            <input id ="confirmpass" type="password" class="form-control" name="confirmpass" placeholder="********" required />
            <div class="help-block">Password is required</div>
        </div>
        <div class="form-group">
            <label for="Role">Role</label>
            <select class="form-control" name="role">
            	<option value="USER">USER</option>
            	<option value="ADMIN">ADMIN</option>
            </select>
            <!-- <input type="password" class="form-control" name="confirmPwd" placeholder="********" required />
            <div class="help-block">Password is required</div> -->
        </div>
        <div class="form-group">
            <button  class="btn btn-primary" name="submit" type="submit" onClick="return Validate()">Register</button>
            <a href="${pageContext.request.contextPath }" class="btn btn-danger"
			style="margin-left: 20px">Go Back</a>
        </div>
    </form:form>
</div>
	<!-- Validating Password -->
	<script type="text/javascript">
		function Validate() {
			var password = document.getElementById("password").value;
			var confirmpass = document.getElementById("confirmpass").value;
			if (password != confirmpass) {
				alert("Password does Not Match.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>