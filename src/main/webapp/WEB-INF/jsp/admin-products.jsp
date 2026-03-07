<%@ include file="fragments/header.jsp"%>

<div class="container mt-5">

    <h2 class="mb-4 text-center">Admin Product Control</h2>

    <c:if test="${not empty success}">
        <div class="alert alert-success">
            ${success}
        </div>
    </c:if>
    
    <c:if test="${not empty error}">
	    <div class="alert alert-danger">
	        ${error}
	    </div>
	</c:if>

    <table class="table table-bordered table-hover shadow">

        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach items="${products}" var="p">

                <tr>
                    <td>${p.productId}</td>
                    <td>${p.name}</td>
                    <td>Rs. ${p.price}</td>
                    <td>${p.stock}</td>

					<td>

						<div class="d-flex gap-2">

							<a
								href="${pageContext.request.contextPath}/admin/edit-product/${p.productId}"
								class="btn btn-primary btn-sm"> Edit </a>

							<form method="post"
								action="${pageContext.request.contextPath}/admin/delete-product">

								<input type="hidden" name="productId" value="${p.productId}" />

								<button class="btn btn-danger btn-sm"
									onclick="return confirm('Delete this product?')">
									Delete</button>

							</form>

						</div>

					</td>

				</tr>

            </c:forEach>

        </tbody>

    </table>

</div>

<%@ include file="fragments/footer.jsp"%>
