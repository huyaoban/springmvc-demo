<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book Form</title>
<!-- <style type="text/css">@import url(css/main.css);</style> -->
<style type="text/css">@import url("<c:url value='/css/main.css'/>");</style>
</head>
<body>
<div id="global">
<c:url var="formAction" value="/save-book" />
<form:form commandName="book" action="${formAction}" method="post">
<%-- <form:form commandName="book" action="save-book" method="post"> --%>
<%-- cannot use the follow, the best practice is using <c:url/> --%>
<%-- <form:form commandName="book" action="/save-book" method="post"> --%>
	<fieldset>
		<legend>Add a Book</legend>
		<p>
			<label for="category">Category:</label>
			<form:select id="category" path="category.id" items="${categories}" itemLabel="name" itemValue="id" />
		</p>
		<p>
			<label for="title">Title:</label>
			<form:input id="title" path="title" />
		</p>
		<p>
			<label for="author">Author:</label>
			<form:input id="author" path="author"/>
		</p>
		<p>
			<label for="isbn">ISBN:</label>
			<form:input id="isbn" path="isbn"/>
		</p>
		<p>
			<label for="price">Price:</label>
			<form:input id="price" path="price" />
		</p>
		<p id="buttons">
			<input id="reset" type="reset">
			<input id="submit" type="submit">
		</p>
	</fieldset>
</form:form>
</div>
</body>
</html>