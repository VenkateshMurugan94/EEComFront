<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CR"
	value="${pageContext.request.contextPath}/resources/productImages" />
<c:set var="contextroot" value="${pageContext.request.contextPath}"></c:set>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<style>
.table>tbody>tr>td, .table>tfoot>tr>td {
	vertical-align: middle;
}

@media screen and (max-width: 600px) {
	table#cart tbody td .form-control {
		width: 20%;
		display: inline !important;
	}
	.actions .btn {
		width: 36%;
		margin: 1.5em 0;
	}
	.actions .btn-info {
		float: left;
	}
	.actions .btn-danger {
		float: right;
	}
	table#cart thead {
		display: none;
	}
	table#cart tbody td {
		display: block;
		padding: .6rem;
		min-width: 320px;
	}
	table#cart tbody tr td:first-child {
		background: #333;
		color: #fff;
	}
	table#cart tbody td:before {
		content: attr(data-th);
		font-weight: bold;
		display: inline-block;
		width: 8rem;
	}
	table#cart tfoot td {
		display: block;
	}
	table#cart tfoot td .btn {
		display: block;
	}
	.img-responsive {
		height: 100px;
		width: 100px;
	}
}
</style>

<div class="container">
	<c:if test="${empty(cartlist)}">
		<h2 class="jumbotron">Cart Is Empty</h2>
		<a href="${contextroot}/viewallproduct" class="btn button"><i
			class="fa fa-angle-left"></i> Continue Shopping</a>
	</c:if>
	<c:if test="${!empty(cartlist)}">
		<table id="cart" class="table table-hover table-condensed">
			<thead>
				<tr>
					<th style="width: 40%">Product</th>
					<th style="width: 8%">Price</th>
					<th style="width: 22%">Quantity</th>
					<th style="width: 20%" class="text-center">Subtotal</th>
					<th style="width: 10%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cartlist}" var="l">
					<tr>
						<td data-th="Product">
							<div class="row">
								<div class="col-sm-4 hidden-xs">
									<img src="${CR}/${l.productId}.jpg" alt="..." class="img-responsive" />
								</div>
								<div class="col-sm-8">
									<h4 class="nomargin" style="padding-top: 50px;">${l.productName}</h4>
								</div>
							</div>
						</td>
						<td data-th="Price">${l.productPrice}</td>
						<td data-th="Quantity">
							<div>
								<a href="${contextroot }/user/decqty?itemid=${l.dummyId}"
									class="btn btn-info btn-sm"> - </a> <input type="text"
									name="itemqty" value="${l.productQuantity}" style="width: 35px;" /> <a
									href="${contextroot}/user/incqty?itemid=${l.dummyId}"
									class="btn btn-info btn-sm"> + </a>
							</div>
							<div>
								<c:if test="${error}">
									<c:if test="${productId==l.productId}">
										<strong style="color: red;">Min Qty 1 Max Qty 3</strong>
									</c:if>
								</c:if>
								<c:if test="${error1}">
									<c:if test="${productId==l.productId}">
										<strong style="color: red;">Only ${stock} pieces
											available</strong>
									</c:if>
								</c:if>
							</div>
						</td>
						<td data-th="Subtotal" class="text-center">${l.subTotal}</td>
						<td class="actions" data-th=""><a
							href="${contextroot }/user/deleteitem?itemid=${l.dummyId}"
							class="btn btn-danger btn-sm"> <i class="fa fa-trash-o"></i>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td><a href="${contextroot}/viewallproduct"
						class="btn btn-warning"><i class="fa fa-angle-left"></i>
							Continue Shopping</a></td>
					<td />
					<td />
					<td />
					<td><a href="${contextroot}/user/Address"
						class="btn btn-success btn-block">Checkout <i
							class="fa fa-angle-right"></i>
					</a></td>
				</tr>
			</tfoot>
		</table>
	</c:if>
</div>
