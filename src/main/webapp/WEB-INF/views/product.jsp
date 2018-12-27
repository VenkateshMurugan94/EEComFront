<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	
	<c:set var="contextRoot" value="${pageContext.request.contextPath}"></c:set>

<script>
	$(document).ready(function() {
		$('#myTable').DataTable({
			"pagingType" : "full_numbers",
			"lengthMenu" : [ [ 2, 5, 10, -1 ], [ 2, 5, 10, "All" ] ],
			"ordering" : true,
		});
	});
</script>

<style>
.login-block {
	float: left;
	width: 100%;
	padding: 50px 0;
}

.login-sec {
	padding: 50px 30px;
	position: relative;
}

.login-sec h2 {
	margin-bottom: 30px;
	font-weight: 800;
	font-size: 30px;
	color: #0069c0;
}

.login-sec h2:after {
	content: " ";
	width: 100px;
	height: 5px;
	background: #6ec6ff;
	display: block;
	margin-top: 20px;
	border-radius: 3px;
	margin-left: auto;
	margin-right: auto
}

.btn-login {
	background: #0069c0;
	color: #fff;
	font-weight: 600;
	margin: 30px;
}
</style>

<div class="container">

	<c:if test="${editmode}">
		<c:set var="url" value="${contextRoot}/admin/UpdateProduct"></c:set>
	</c:if>

	<c:if test="${!editmode}">
		<c:set var="url" value="${contextRoot}/admin/CreateProduct"></c:set>
	</c:if>

	<c:if test="${haserror}">
		<div class="alert alert-danger">
			<strong>${error}</strong>
		</div>

	</c:if>

	<div class="row ">
		<div class="col login-sec">
			<h2 class="text-center">Product</h2>

			<form:form enctype="multipart/form-data" class="login-form" modelAttribute="product"
				action="${url}" method="post">

				<c:if test="${editmode}">
					<div class="form-group">
						<form:input type="text" class="form-control" placeholder=""
							path="productId" readonly="true" />
						<form:errors path="productId" cssStyle="color:red"></form:errors>
					</div>
				</c:if>

				<div class="form-group">
					<label for="exampleInputEmail1" class="text-uppercase">Product
						Name</label>
					<form:input type="text" class="form-control" placeholder=""
						path="productName" />
					<form:errors path="productName" cssStyle="color:red"></form:errors>
				</div>

				<div class="form-group">
					<label for="exampleInputPassword1" class="text-uppercase">Product
						Description</label>
					<form:input type="textarea" class="form-control" placeholder=""
						path="productDesc" />
					<form:errors path="productDesc" cssStyle="color:red"></form:errors>
				</div>

				<div class="form-group">
					<label for="exampleInputPassword1" class="text-uppercase">Category
						Name</label>
					<form:select class="form-control" path="catogery.categoryId">
						<c:forEach items="${catlist}" var="c">
							<form:option value="${c.categoryId}">${c.categoryName}</form:option>
						</c:forEach>
					</form:select>
				</div>

				<div class="form-group">
					<label for="exampleInputPassword1" class="text-uppercase">Price</label>
					<form:input type="text" class="form-control" placeholder=""
						path="productPrice" />
					<form:errors path="productPrice" cssStyle="color:red"></form:errors>
				</div>

				<div class="form-group">
					<label for="exampleInputPassword1" class="text-uppercase">Quantity</label>
					<form:input type="text" class="form-control" placeholder=""
						path="productQuantity" />
					<form:errors path="productQuantity" cssStyle="color:red"></form:errors>
				</div>
				
				<div class="form-group">
					<form:input type="file" name="fileToUpload" id="fileToUpload" path="pimage" required="true"/>
				</div>

				<div class="form-check">

					<c:if test="${!editmode}">
						<input type="submit" class="btn btn-login float-right"
							value="Add Product">
					</c:if>

					<c:if test="${editmode}">
						<input type="submit" class="btn btn-login float-right"
							value="Edit Product">
					</c:if>
				</div>

			</form:form>
		</div>
	</div>
</div>

<div class="row">
	<div>
		<div class="table-responsive">
			<table id="myTable" class="table">
				<thead>
					<tr>
						<th>PRODUCT ID</th>
						<th>PRODUCT NAME</th>
						<th>PRODUCT DESCRIPTION</th>
						<th>PRODUCT PRICE</th>
						<th>PRODUCT QUANTITY</th>
						<th>CATEGORY</th>
						<th>IMAGES</th>
						<th class="text-right">Edit/Delete</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${prodlist}" var="c">
						<tr>
							<td>${c.productId}</td>
							<td>${c.productName}</td>
							<td>${c.productDesc}</td>
							<td>${c.productPrice}</td>
							<td>${c.productQuantity}</td>
							<td>${c.catogery.categoryName}</td>
							<td><img src="${contextRoot}/resources/productImages/${c.productId}.jpg" width="100" height="100"/></td>

							<td class="td-actions text-right">
									<a href="${contextRoot}/admin/editproduct?productName=${c.productName}"
										rel="tooltip"
										class="btn btn-success btn-link btn-just-icon btn-sm"
										data-original-title="" title=""> <i class="material-icons">edit</i>
									</a>
									<a href="${contextRoot}/admin/deleteproduct?productName=${c.productName}"
										rel="tooltip"
										class="btn btn-danger btn-link btn-just-icon btn-sm"
										data-original-title="" title=""> <i class="material-icons">close</i>
									</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>