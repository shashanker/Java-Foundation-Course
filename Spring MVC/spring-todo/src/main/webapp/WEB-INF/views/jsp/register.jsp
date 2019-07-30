
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>

    <div>
        <jsp:include page="include/menu.jsp"/>
    </div>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Registration</title>
        <link href="static/css/style_register.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="simple_form">
        	
            <s:url var="url_reg_form" value="/register"/>
            <f:form action="register" modelAttribute="register" id="reg">
                <h1  style="color:red;">Registration</h1>

                <f:input path="username" placeholder="Enter your user name" id="box"/>
                </br>
                </br>
                </br>
                <f:password path="password" placeholder="Enter your password" id="box"/>
                </br>
                </br>
                </br>
                <f:button id="butt">Register</f:button>
            </f:form>
        </div>
        <div id="footer">
            <footer>
                <jsp:include page="include/footer.jsp"/>
            </footer>
        </div>

    </body>
</html>
