<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="fragments/header.jsp" %>

<script>
window.onload = function() {
    var successMsg = "${successMessage}";
    if(successMsg && successMsg !== "null"){
        alert(successMsg);
    }
};
</script>

<div class="container mt-5">

    <!-- Page Title -->
    <div class="text-center mb-4">
        <h2 class="fw-bold">Your Shopping Cart</h2>
        <p class="text-muted">Review your items before checkout</p>
    </div>

    <!-- EMPTY CART -->
    <c:if test="${empty cartItems}">
        <div class="card shadow border-0 text-center p-5">
            <h4 class="mb-3">Your cart is empty</h4>
            <p class="text-muted">Looks like you have not added anything yet.</p>

            <a href="${pageContext.request.contextPath}/user/products"
               class="btn btn-primary btn-lg mt-3">
                Continue Shopping
            </a>
        </div>
    </c:if>

    <!-- CART WITH ITEMS -->
    <c:if test="${not empty cartItems}">

        <div class="card shadow border-0">
            <div class="card-body">

                <table class="table align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>Product</th>
                            <th width="150">Price</th>
                            <th width="120">Quantity</th>
                            <th width="150">Total</th>
                        </tr>
                    </thead>

                    <tbody>

                        <c:set var="grandTotal" value="0"/>

                        <c:forEach items="${cartItems}" var="item">

                            <c:set var="itemTotal"
                                   value="${item.product.price * item.quantity}" />

                            <c:set var="grandTotal"
                                   value="${grandTotal + itemTotal}" />

                            <tr>
                                <td class="fw-semibold">
                                    ${item.product.name}
                                </td>

                                <td class="text-success fw-bold">
                                    Rs. ${item.product.price}
                                </td>

                                <td>
                                    <span class="badge bg-primary">
                                        ${item.quantity}
                                    </span>
                                </td>

                                <td class="fw-bold">
                                    Rs. ${itemTotal}
                                </td>
                            </tr>

                        </c:forEach>

                    </tbody>
                </table>

            </div>
        </div>

        <!-- GRAND TOTAL -->
        <div class="card mt-4 shadow border-0">
            <div class="card-body d-flex justify-content-between align-items-center flex-wrap">

                <h4 class="mb-2">
                    Grand Total:
                    <span class="text-success fw-bold">
                        Rs. ${grandTotal}
                    </span>
                </h4>

                <div class="d-flex gap-2 flex-wrap">

                    <a href="${pageContext.request.contextPath}/user/products"
                       class="btn btn-outline-secondary">
                        Continue Shopping
                    </a>

                    <a href="${pageContext.request.contextPath}/user/checkout"
                       class="btn btn-success">
                        Checkout
                    </a>

                    <form action="${pageContext.request.contextPath}/user/clear-cart"
                          method="post">

                        <button type="submit"
                                class="btn btn-danger"
                                onclick="return confirm('Are you sure you want to clear the cart?');">
                            Clear Cart
                        </button>

                    </form>

                </div>
            </div>
        </div>

    </c:if>

</div>

<%@ include file="fragments/footer.jsp" %>
