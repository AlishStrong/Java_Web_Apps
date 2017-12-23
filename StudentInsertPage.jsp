<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert student</title>
<link rel="stylesheet" type="text/css" href="CSS/WebAppExamples.css">
</head>
<body>
<form action="StudentInsertServlet" method="GET">
			<table>
			<tr>
			<td>ID</td>
			<td><input type="text" name="txtID" /></td>
			</tr>
			<tr>
			<td>Name</td>
			<td><input type="text" name="txtName" /></td>
			</tr>
			<tr>
			<td>Surname</td>
			<td><input type="text" name="txtSurname" /></td>
			</tr>
			<tr>
			<td>Address</td>
			<td><input type="text" name="txtAddress" /></td>
			</tr>
			<tr>
			<td>Postcode</td>
			<td><input type="text" name="txtPostcode" /></td>
			</tr>
			<tr>
			<td>City</td>
			<td><input type="text" name="txtPostoffice" /></td>
			</tr>
			</table>
			<input type="submit" value="Insert student" />
	</form>
	<br/><br/>
	<c:out value="${ message }"></c:out>
</body>
</html>
