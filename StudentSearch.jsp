<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student search</title>
<link rel="stylesheet" type="text/css" href="CSS/WebAppExamples.css">
</head>
<body>
	<form action="StudentSearchServlet" method="GET">
			<label>Student ID: </label>
			<input type="text" name="txtID" />
			<input type="submit" value="Show students" />
	</form>
		<br/><br/>
	<c:if test="${ student != null }">
			<c:out value="${ student.id }  ${ student.firstname } ${ student.lastname }" />
	</c:if>
</body>
</html>
