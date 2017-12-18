<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product Form</title>
<!-- <style type="text/css">@import url(css/main.css);</style> -->
<style type="text/css">@import url("<c:url value='/css/main.css'/>");</style>
</head>
<body>
<div id="global">
<c:url var="formAction" value="/save-product" />
<form:form commandName="product" action="${formAction}" method="post">
<%-- <form:form commandName="book" action="save-book" method="post"> --%>
<%-- cannot use the follow, the best practice is using <c:url/> --%>
<%-- <form:form commandName="book" action="/save-book" method="post"> --%>
	<fieldset>
		<legend>Add a Product</legend>
<!-- 		<p class="errorLine"> -->
<%-- 			<form:errors path="*" cssClass="error" /> --%>
<!-- 		</p> -->
		<p class="errorLine">
			<form:errors path="name" cssClass="error" />
		</p>
		<p>
			<label for="name">Name:</label>
			<form:input id="name" path="name" />
		</p>
		<p>
			<label for="description">Description:</label>
			<form:input id="description" path="description" />
		</p>
		<p class="errorLine">
			<form:errors path="price" cssClass="error" />
		</p>
		<p>
			<label for="price">Price:</label>
			<form:input id="price" path="price"/>
		</p>
		<p class="errorLine">
			<form:errors path="productionDate" cssClass="error" />
		</p>
		<p>
			<label for="productionDate">Date of Production:</label>
			<form:input id="productionDate" path="productionDate"/>
		</p>
		<p id="buttons">
			<input id="submit" type="submit">
		</p>
	</fieldset>
</form:form>
</div>
</body>
</html>