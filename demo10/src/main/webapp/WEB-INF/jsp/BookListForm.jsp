<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book List</title>
<!-- <style type="text/css">@import url(css/main.css);</style> -->
<style type="text/css">@import url("<c:url value='/css/main.css'/>");</style>
</head>
<body>
<div id="global">
<h1>Book List</h1>
<a href="<c:url value="/input-book"/>">Add Book</a>
<table>
<tr>
	<th>Category</th>
	<th>Title</th>
	<th>ISBN</th>
	<th>Author</th>
	<th>Price</th>
	<th>&nbsp;</th>
</tr>
<c:forEach items="${books}" var="book">
	<tr>
		<td>${book.category.name}</td>
		<td>${book.title}</td>
		<td>${book.isbn}</td>
		<td>${book.author}</td>
		<td>${book.price}</td>
<%-- 		<td><a href="<c:url value="/edit-book/${book.id}"/>">Edit</a></td> --%>
		<td><a href="edit-book/${book.id}">Edit</a></td>
<%-- 		cannot use the following, the best practice is using <c:url/> --%>
<%-- 		<td><a href="/edit-book/${book.id}">Edit</a></td> --%>
	</tr>
</c:forEach>
</table>
</div>
</body>
</html>