<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="shortcut icon" href="resources/Images/Logo.ico"/>
<title>${title }</title>
</head>

<body>
	<c:import url="navbar.jsp"></c:import>
	<div style="min-height: 500px;">
	<c:if test="${carouselpage}">
	<c:import url="carousel.jsp"></c:import>
	</c:if>
	<c:if test="${aboutuspage}">
	<c:import url="aboutus.jsp"></c:import>
	</c:if>
	<c:if test="${contactuspage}">
	<c:import url="contactus.jsp"></c:import>
	</c:if>
	<c:if test="${loginpage}">
	<c:import url="Login.jsp"></c:import>
	</c:if>
	<c:if test="${registerpage}">
	<c:import url="register.jsp"></c:import>
	</c:if>
	<c:if test="${categorypage}">
	<c:import url="category.jsp"></c:import>
	</c:if>
	<c:if test="${productpage}">
	<c:import url="product.jsp"></c:import>
	</c:if>
	<c:if test="${viewallproductPage}">
	<c:import url="viewallproduct.jsp"></c:import>
	</c:if>
	<c:if test="${ViewOneProductPage}">
	<c:import url="viewoneproduct.jsp"></c:import>
	</c:if>
	
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>
