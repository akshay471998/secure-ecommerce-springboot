<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>My Orders</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/dashboard">SecureShop</a>
        <button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#nav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="nav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="/user/products">Products</a></li>
                <li class="nav-item"><a class="nav-link" href="/user/cart">Cart</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-5">

    <div class="card shadow-lg">
        <div class="card-header bg-dark text-white text-center">
            <h3>My Orders</h3>
        </div>

        <div class="card-body">

            <!-- SUCCESS MESSAGE -->
            <c:if test="${not empty success}">
                <div class="alert alert-success text-center">
                    ${success}
                </div>
            </c:if>

            <!-- ERROR MESSAGE -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger text-center">
                    ${error}
                </div>
            </c:if>

            <!-- NO ORDERS -->
            <c:if test="${empty orders}">
                <div class="alert alert-info text-center">
                    You have not placed any orders yet.
                </div>
            </c:if>

            <!-- ORDERS TABLE -->
            <c:if test="${not empty orders}">
                <table class="table table-striped table-hover text-center align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>Order ID</th>
                            <th>Total Amount</th>
                            <th>Status</th>
                            <th>Order Date</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${orders}" var="o">
                            <tr>
                                <td>
                                    <span class="badge bg-secondary">
                                        #${o.orderId}
                                    </span>
                                </td>

                                <td class="text-success fw-bold">
                                    ₹ ${o.totalAmount}
                                </td>

                                <td>
                                    <span class="badge 
                                        ${o.status == 'PLACED' ? 'bg-primary' : 
                                          o.status == 'SHIPPED' ? 'bg-warning text-dark' :
                                          o.status == 'DELIVERED' ? 'bg-success' :
                                          'bg-secondary'}">
                                        ${o.status}
                                    </span>
                                </td>

                                <td>
                                    ${o.orderDate}
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

        </div>

        <div class="card-footer text-center">
            <a href="${pageContext.request.contextPath}/user/products"
               class="btn btn-primary">
               Continue Shopping
            </a>
        </div>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
