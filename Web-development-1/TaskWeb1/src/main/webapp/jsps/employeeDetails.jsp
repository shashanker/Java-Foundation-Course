<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<script type="text/javascript">
	var xhr; // The variable that makes Ajax possible!
	function ajaxFunction() {

		try {
			// Opera 8.0+, Firefox, Safari 
			xhr = new XMLHttpRequest();
		} catch (e) {

			// Internet Explorer Browsers
			try {
				xhr = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {

				try {
					xhr = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {

					// Something went wrong
					alert("Your browser broke!");
					return false;
				}
			}
		}
	}

	function showDiv() {
		resetFields();
		var x = document.getElementById('addEmployee');
		document.getElementById("submit").style.visibility = "visible";
		document.getElementById("update").style.visibility = "hidden";
		toggleDiv(x);
		

	}

	function showEmployeesList() {
		var x = document.getElementById('showEmployees');
		toggleDiv(x);
	}

	function toggleDiv(elem) {
		if (elem.style.display === "none") {
			elem.style.display = "block";
		} else {
			elem.style.display = "none";
		}
	}

	function addEmployee(action) {
		if(validate() == false){
			return false;
		}
		ajaxFunction();
		if(action == 'edit'){
			xhr.open('PUT', "employeeOperation");
		}
		else{
			xhr.open('POST', "employeeOperation");
		}
		xhr.onreadystatechange = function() {
			if (xhr.readyState > 3 && xhr.status == 200) {
				processList();
				resetFields();
			}
		};
		xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
		xhr.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		var name = document.getElementById('name').value;
		var id = document.getElementById('id').value;
		var age = document.getElementById('age').value;
		console.log("name="+name);
		params = 'name=' + name + '&id=' + id + '&age=' + age;
		xhr.send(params);
	}

	function processList() {
		//alert(xhr.responseText);
		var employees = JSON.parse(xhr.responseText);
		var table = document.getElementById("employeesTable");
		var rowCount = table.rows.length;
		for (var x = rowCount - 1; x >= 0; x--) {
			table.deleteRow(x);
		}
		if (employees == null) {
			alert("NO DATA FOUND");
			return false;
		}
		for (var i = 0; i < employees.length; i++) {

			var row = table.insertRow(i);

			var cell = row.insertCell(0);
			
			cell.innerHTML = employees[i].name;
			
			var editCell = row.insertCell(1);
			var deleteCell = row.insertCell(2);
			
			editCell.innerHTML = '<input type="button" value="Edit" onClick="editEmployee('+employees[i].id+')"/>';
			deleteCell.innerHTML = '<input type="button" value="Delete" onClick="deleteEmployee('+employees[i].id+')"/>';
		}

	}

	function getEmployees() {
		showEmployeesList();
		ajaxFunction();
		xhr.open('GET', "employeeOperation", true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState > 3 && xhr.status == 200) {
				processList();
			}
		};
		xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
		xhr.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');

		xhr.send();
	}
	
	function resetFields()
	{
		document.getElementById('name').value = "";
		document.getElementById('id').value = "";
		document.getElementById('age').value = "";
	}
	
	function editEmployee(empid){
		alert("edit Employee:"+empid);
		document.getElementById("submit").style.visibility = "hidden";
		var elem = document.getElementById('addEmployee');
		if (elem.style.display === "none") {
			elem.style.display = "block";
		}
		document.getElementById("update").style.visibility = "visible";
		
		
		/*
			Send Ajax call to return all the details of the Employee to Edit
		*/
		
		ajaxFunction();
		xhr.open('GET', "employeeOperation?id="+empid+"&action=edit", true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState > 3 && xhr.status == 200) {
				console.log(xhr.responseText);
				var employee = JSON.parse(xhr.responseText);
				document.getElementById('name').value = employee.name;
				document.getElementById('id').value = employee.id;
				document.getElementById('id').readOnly = true;
				document.getElementById('age').value = employee.age;
			}
		};
		xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
		xhr.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');

		xhr.send();
	}
	function deleteEmployee(empid){
		ajaxFunction();
		
		xhr.open('DELETE', "employeeOperation", true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState > 3 && xhr.status == 200) {
				console.log(xhr.responseText);
				processList();
				/* var employee = JSON.parse(xhr.responseText);
				document.getElementById('name').value = employee.name;
				document.getElementById('id').value = employee.id;
				document.getElementById('id').readOnly = true;
				document.getElementById('age').value = employee.age; */
			}
		};
		xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
		xhr.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');

		xhr.send("id="+empid);
		
		
	}
	
	function updateEmployee(action){
		
		addEmployee(action);
		document.getElementById('id').readOnly = false;
	}
	
	function validate()
	{
		var id =document.getElementById('id').value;
		var name =document.getElementById('name').value;
		var age = document.getElementById('age').value;
		
		if(id==null || id=="" || name==null || name=="" || age==null || age==""){
			alert("please enter values for all the fields");
			return false;
		}
		
		return true;
	}
	
	function ValidateNumberOnly()
	{
	if ((event.keyCode < 48 || event.keyCode > 57)) 
	{
	   event.returnValue = false;
	}
	}
</script>
</head>
<body>
	<center>
		<div>
			<h3>Employee Details</h3>
		</div>

		<!-- Div for Adding Employees -->
		<input type="button" name="addEmp" value="Click Add Employee"
			onclick="showDiv()" />
		<div id="addEmployee" style="display: none;">
			<form>
				<table>
					<tr>
						<td>Name :</td>
						<td><input type="text" id="name" /></td>
					</tr>
					<tr>
						<td>ID :</td>
						<td><input type="text" id="id" onkeypress="ValidateNumberOnly()"/></td>
					</tr>
					<tr>
						<td>Age :</td>
						<td><input type="text" id="age" onkeypress="ValidateNumberOnly()"/></td>
					</tr>
					<tr>
						<td><input type="button" value="submit" id="submit"
							onclick="addEmployee('add')" /></td>
						<td><input type="button" value="update" id="update"
							onclick="updateEmployee('edit')"/></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- List of Employees -->
		<c:set var="Date" value="<%=new java.util.Date()%>" />
		
		<input type="button" name="emplist" value="List of Employees"
			onclick="getEmployees()" />
		<div id="showEmployees" style="display: none;">
		<h3>List of Employees as of <fmt:formatDate type="date" value="${Date}" pattern="dd-MM-yy"/>  </h3>
			<table id="employeesTable">
			</table>

		</div>
<%
	Cookie[] cookies = request.getCookies();
	int hitCount = 1;
	for(int i =0;cookies!=null && i<cookies.length;i++){
		if(cookies[i].getName().equalsIgnoreCase("hitcount")){
			hitCount = Integer.parseInt(cookies[i].getValue());
		}
	}
%>
<h3>Hits : <%=hitCount %></h3>
	</center>
</body>
</html>