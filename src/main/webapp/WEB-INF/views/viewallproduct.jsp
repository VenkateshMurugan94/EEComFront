<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<c:set var="contextRoot" value="${pageContext.request.contextPath}"></c:set>

<style>
* {
	box-sizing: border-box;
}

.thumbnail {
	min-height: 350px;
}

.zoom {
    transition: transform 5s;
    margin: 0 auto;
}
.zoom:hover {
    -ms-transform: scale(2); /* IE 9 */
    -webkit-transform: scale(2); /* Safari 3-8 */
    transform: scale(2);
}

.btn{
	background: #0069c0; color:#fff; font-weight:600;
	margin-bottom:10px;
}

.text-center .label{
	background:#007b5e;

}

</style>

<div class="container">
	<div class="row">
		<c:forEach items="${prodlist}" var="p">
			<div class="col-sm-4 col-md-4 col-">
				<div class="thumbnail w3-container w3-center w3-animate-bottom">
					<h4 class="text-center">
						<span class="label label-info">${p.productName}</span>
					</h4>
					<img class="zoom" src="${contextRoot}/resources/productImages/${p.productId}.jpg"
						height="200" width="200">
					<div class="row">
						<div class="text-center">
							<h3>&#8377 ${p.productPrice}/Kg</h3>
						</div>
						<div class="text-center">
							<a class="btn" href="${contextRoot}/viewoneproduct?productname=${p.productName}">View</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>