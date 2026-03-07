<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="fragments/header.jsp" %>

<div class="container mt-4">
    <div class="col-md-6 mx-auto">
        <div class="card shadow">

            <div class="card-header bg-dark text-white">
                Add New Product (Admin Only)
            </div>

            <div class="card-body">

                <c:if test="${param.success != null}">
                    <div class="alert alert-success">
                        Product added successfully.
                    </div>
                </c:if>

                <form method="post" action="/admin/add-product">

                    <div class="mb-3">
                        <label>Product Name</label>
                        <input type="text" name="name" class="form-control" required />
                    </div>

                    <div class="mb-3">
                        <label>Description</label>
                        <textarea name="description" class="form-control"></textarea>
                    </div>

                    <div class="mb-3">
                        <label>Price</label>
                        <input type="number" step="0.01" name="price" class="form-control" required />
                    </div>

                    <div class="mb-3">
                        <label>Stock</label>
                        <input type="number" name="stock" class="form-control" required />
                    </div>

                    <button class="btn btn-success w-100" type="submit">
                        Save Product
                    </button>

                </form>

            </div>
        </div>
    </div>
</div>

<%@ include file="fragments/footer.jsp" %>
