<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student List</title>
<link rel="stylesheet" type="text/css" href="CSS/WebAppExamples.css">
</head>
<body>
	<form action="StudentListServlet" method="GET">
		<input type="submit" value="Show students" />
	</form>
	<br/><br/>
	<c:if test="${ studentList != null && studentList.size() > 0 }">
			<table>
				<thead>
					<tr>
						<th>Student ID</th><th>Last Name</th><th>First Name</th><th>Street</th>
						<th>Postcode</th><th>Post Office</th><th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ studentList }" var="studentObject">
						<tr>
							<td><c:out value="${ studentObject.id}" /></td>
							<td><c:out value="${ studentObject.lastname }" /></td>
							<td><c:out value="${ studentObject.firstname }" /></td>
							<td><c:out value="${ studentObject.streetaddress }" /></td>
							<td><c:out value="${ studentObject.postcode }" /></td>
							<td><c:out value="${ studentObject.postoffice }" /></td>
							<td><a href='DeleteStudent?studentId=<c:out value="${ studentObject.id }" />' onClick="return confirm('Do you want to delete the student?')" />Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<%-- If the list is empty, then show an appropriate message --%>
		<c:if test="${ studentList != null && studentList.size() == 0 }">
			There are no students
		</c:if>
</body>
</html>
