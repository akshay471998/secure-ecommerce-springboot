<%@ include file="fragments/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mt-5">

    <!-- Welcome Banner -->
    <div class="p-4 mb-4 bg-primary text-white rounded shadow">
        <h2 class="mb-0">Welcome, ${username}</h2>
        <small>Your ecommerce dashboard</small>
    </div>

    <!-- Success / Error Alerts -->
    <c:if test="${param.showSuccessMessage == true}">
        <div class="alert alert-success alert-dismissible fade show">
            Product saved successfully.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <c:if test="${param.showSuccessMessage == false}">
        <div class="alert alert-danger alert-dismissible fade show">
            Something went wrong!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <!-- Dashboard Cards -->
    <div class="row g-4 mt-2">

        <!-- View Products -->
        <div class="col-md-4">
            <div class="card border-0 shadow-lg h-100 dashboard-card">
                <div class="card-body text-center">
                    <h1>Products</h1>
                    <h5 class="card-title mt-2">Browse Products</h5>
                    <p class="text-muted">View and shop from available items</p>
                    <a href="/user/products" class="btn btn-primary w-100">
                        View Products
                    </a>
                </div>
            </div>
        </div>

        <!-- Admin Add Product -->
        <c:if test="${role == 'ADMIN'}">
            <div class="col-md-4">
                <div class="card border-0 shadow-lg h-100">
                    <div class="card-body text-center">
                        <h1>Add</h1>
                        <h5 class="card-title mt-2">Add Product</h5>
                        <p class="text-muted">Insert new products to inventory</p>
                        <a href="/admin/product" class="btn btn-success w-100">
                            Add Product
                        </a>
                    </div>
                </div>
            </div>
        </c:if>

        <!-- Admin Delete Product -->
        <c:if test="${role == 'ADMIN'}">
            <div class="col-md-4">
                <div class="card border-0 shadow-lg h-100">
                    <div class="card-body text-center">
                        <h1>Delete</h1>
                        <h5 class="card-title mt-2">Manage Products</h5>
                        <p class="text-muted">Delete or control inventory</p>
                        <a href="/admin/products" class="btn btn-danger w-100">
                            Manage Products
                        </a>
                    </div>
                </div>
            </div>
        </c:if>

    </div>

</div>

<style>
.dashboard-card:hover {
    transform: translateY(-8px);
    transition: 0.3s;
}
</style>

<%@ include file="fragments/footer.jsp" %>
