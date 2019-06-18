<%@page language="java" pageEncoding="ISO-8859-1" session="true"  %>
<%@taglib prefix="json" uri="http://www.epam.com/taglibs/json" %>



<html>
<body>
<center>
<h2>Custom Tag for JSON Representation</h2>
<json:object prettyPrint="true">
  <json:property name="itemCount" value="abc"/>
  <json:property name="subtotal" value="dsre"/>
  <json:array name="items">
  <json:object>
      <json:property name="title" value="afsdf" trim="true"/>
      <json:property name="description" value="daf"/>
      <json:property name="imageUrl" value="resdf"/>
      <json:property name="price" value="s432e"/>
      <json:property name="qty" value="dsa234"/>
    </json:object>
    <json:object>
      <json:property name="title" value="afsdf" trim="true"/>
      <json:property name="description" value="daf"/>
      <json:property name="imageUrl" value="resdf"/>
      <json:property name="price" value="s432e"/>
      <json:property name="qty" value="dsa234"/>
    </json:object>
    
    <json:object>
      <json:property name="title" value="afsdf" trim="true"/>
      <json:property name="description" value="daf"/>
      <json:property name="imageUrl" value="resdf"/>
      <json:property name="price" value="s432e"/>
      <json:property name="qty" value="dsa234"/>
    </json:object>
    </json:array>
</json:object>

</center>
</body>
</html>
