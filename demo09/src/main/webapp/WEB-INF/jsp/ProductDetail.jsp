<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Save Product</title>
<style type="text/css">@import url("<c:url value="/css/main.css"/>");</style>
</head>
<body>
<div id="global">
	<h4>The Product has been saved.</h4>
	<p>
		<h5>Details:</h5>
		Product Name: ${product.name}<br/>
		Description: ${product.description}<br/>
		Price:${product.price}
	</p>
</div>
</body>
</html>