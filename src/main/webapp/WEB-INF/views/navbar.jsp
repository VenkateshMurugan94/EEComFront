<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}"></c:set>
<style>
nav.navbar-webmaster { background: #4dff88;}
nav.navbar-webmaster a { color: #fff; }
nav.navbar-webmaster ul.navbar-nav a { color: #fff; border-style: solid; border-width: 2px 0 0 0; border-color:#4dff88; }
nav.navbar-webmaster ul.navbar-nav a:hover,
nav.navbar-webmaster ul.navbar-nav a:visited,
nav.navbar-webmaster ul.navbar-nav a:focus,
nav.navbar-webmaster ul.navbar-nav a:active { background:#4ce600; }
nav.navbar-webmaster ul.navbar-nav a:hover {border-color: #5fb000; }
nav.navbar-webmaster li.divider { background: #ccc; }
nav.navbar-webmaster button.navbar-toggle { background:  #1b242c; border-radius: 2px; }
nav.navbar-webmaster button.navbar-toggle:hover { background: #999; }
nav.navbar-webmaster button.navbar-toggle > span.icon-bar { background: #fff; }
nav.navbar-webmaster ul.dropdown-menu { border: 0; background: #fff; border-radius: 4px; margin: 4px 0; box-shadow: 0 0 4px 0 #ccc; }
nav.navbar-webmaster ul.dropdown-menu > li > a { color: #444; }
nav.navbar-webmaster ul.dropdown-menu > li > a:hover { background: #f14444; color: #4dff88; }
nav.navbar-webmaster span.badge { background: #f14444; font-weight: normal; font-size: 11px; margin: 0 4px; }
nav.navbar-webmaster span.badge.new { background: rgba(255, 0, 0, 0.8); color: #fff; }

.container .collapse .navbar-collapse .nav .navbar-nav .navbar-right{
	margin-bottom:20%;
}

</style>


<nav class="navbar navbar-webmaster">
    <div class="container">
    	<div class="navbar-header">
			<img src="${contextRoot}/resources/Images/Logo.jpg" class="navbar-brand">
		</div>
		
		<div class="collapse navbar-collapse" id="navbar">
		
		<c:choose>
		<c:when test="${sessionScope.userlogin==true}">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${contextRoot}/index">Home<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/contactus">Contact Us<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/aboutus">About Us<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/viewallproduct">View All Product<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/user/viewCart">Cart<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/logout">Logout<span class="sr-only">(current)</span></a></li>
			</ul>
		</c:when>
		
		<c:when test="${sessionScope.userlogin==false}">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${contextRoot}/index">Home<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/admin/category">Category<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/admin/product">Product<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/contactus">Contact Us<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/aboutus">About Us<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/viewallproduct">View All Product<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/logout">Logout<span class="sr-only">(current)</span></a></li>
			</ul>
		</c:when>
		
		<c:otherwise>
		<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${contextRoot}/index">Home<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/contactus">Contact Us<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/aboutus">About Us<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/viewallproduct">View All Product<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/register">Register<span class="sr-only">(current)</span></a></li>
				<li class="active"><a href="${contextRoot}/Login">Login<span class="sr-only">(current)</span></a></li>
			</ul>
		</c:otherwise>
		
		</c:choose>
		</div>
	</div>
</nav>