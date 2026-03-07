<%@ include file="fragments/header.jsp" %>

<div class="container mt-5">

    <div class="card shadow-lg">

        <div class="card-header bg-warning text-dark">
            <h4>Edit Product</h4>
        </div>

        <div class="card-body">

            <form method="post"
                  action="${pageContext.request.contextPath}/admin/update-product">

                <input type="hidden"
                       name="productId"
                       value="${product.productId}" />

                <div class="mb-3">
                    <label class="form-label">Product Name</label>
                    <input type="text"
                           name="name"
                           value="${product.name}"
                           class="form-control"
                           required />
                </div>

                <div class="mb-3">
                    <label class="form-label">Description</label>
                    <textarea name="description"
                              class="form-control"
                              rows="3"
                              required>${product.description}</textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Price</label>
                    <input type="number"
                           step="0.01"
                           name="price"
                           value="${product.price}"
                           class="form-control"
                           required />
                </div>

                <div class="mb-3">
                    <label class="form-label">Stock</label>
                    <input type="number"
                           name="stock"
                           value="${product.stock}"
                           class="form-control"
                           required />
                </div>

                <button type="submit"
                        class="btn btn-success">
                    Update Product
                </button>

                <a href="${pageContext.request.contextPath}/admin/products"
                   class="btn btn-secondary">
                    Cancel
                </a>

            </form>

        </div>

    </div>

</div>

<%@ include file="fragments/footer.jsp" %>
