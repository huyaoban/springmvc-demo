<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee Form</title>
<!-- <style type="text/css">@import url(css/main.css);</style> -->
<style type="text/css">@import url("<c:url value='/css/main.css'/>");</style>
</head>
<body>
<div id="global">
<c:url var="formAction" value="/save-employee" />
<form:form commandName="employee" action="${formAction}" method="post">
<%-- <form:form commandName="book" action="save-book" method="post"> --%>
<%-- cannot use the follow, the best practice is using <c:url/> --%>
<%-- <form:form commandName="book" action="/save-book" method="post"> --%>
	<fieldset>
		<legend>Add an Employee</legend>
		<p>
			<label for="firstName">First Name:</label>
			<form:input id="firstName" path="firstName" />
		</p>
		<p>
			<label for="lastName">Last Name:</label>
			<form:input id="lastName" path="lastName" />
		</p>
		<p>
			<form:errors path="birthDate" cssClass="error" />
		</p>
		<p>
			<label for="birthDate">Date of birth:</label>
			<form:input id="birthDate" path="birthDate"/>
		</p>
		<p id="buttons">
			<input id="submit" type="submit">
		</p>
	</fieldset>
</form:form>
</div>
</body>
</html>