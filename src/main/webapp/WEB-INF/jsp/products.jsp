<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="fragments/header.jsp"%>

<script>
window.onload = function() {
    var errorMsg = "${errorMessage}";
    if(errorMsg && errorMsg !== "null"){
        alert(errorMsg);
    }
};
</script>

<div class="container py-5">

    <!-- Page Title -->
    <div class="text-center mb-5">
        <h1 class="fw-bold">Our Products</h1>
        <p class="text-muted">Discover the best items curated just for you</p>
    </div>

    <div class="row g-4">

        <c:forEach items="${products}" var="p">

            <div class="col-lg-4 col-md-6">

                <div class="card product-card border-0 shadow-sm h-100">

                    <!-- Product Image -->
                    <div class="product-img-wrapper">
                        <img src="https://via.placeholder.com/400x250"
                             class="card-img-top product-img"
                             alt="Product Image">
                    </div>

                    <div class="card-body d-flex flex-column">

                        <h5 class="card-title fw-bold">
                            ${p.name}
                        </h5>

                        <p class="card-text text-muted small flex-grow-1">
                            ${p.description}
                        </p>

                        <h4 class="text-success fw-bold mb-3">
                            ₹ ${p.price}
                        </h4>

                        <form action="${pageContext.request.contextPath}/user/add-to-cart"
                              method="post">

                            <div class="mb-3">
                                <input type="number"
                                       name="quantity"
                                       value="1"
                                       min="1"
                                       class="form-control text-center"
                                       required />
                            </div>

                            <button type="submit"
                                    name="productId"
                                    value="${p.productId}"
                                    class="btn btn-primary w-100 add-btn">

                                Add to Cart
                            </button>

                        </form>

                    </div>

                </div>

            </div>

        </c:forEach>

    </div>

    <!-- Cart Button -->
    <div class="text-center mt-5">
        <a href="${pageContext.request.contextPath}/user/cart"
           class="btn btn-success btn-lg px-5 shadow">

           View Cart
        </a>
    </div>

</div>

<style>

/* PRODUCT CARD */
.product-card{
    transition: all 0.3s ease;
    border-radius: 15px;
}

.product-card:hover{
    transform: translateY(-10px);
    box-shadow: 0 15px 35px rgba(0,0,0,0.15);
}

/* IMAGE ZOOM EFFECT */
.product-img-wrapper{
    overflow: hidden;
    border-top-left-radius: 15px;
    border-top-right-radius: 15px;
}

.product-img{
    transition: transform 0.4s ease;
}

.product-card:hover .product-img{
    transform: scale(1.08);
}

/* BUTTON */
.add-btn{
    font-weight: 600;
    letter-spacing: 0.5px;
}

</style>

<%@ include file="fragments/footer.jsp"%>
