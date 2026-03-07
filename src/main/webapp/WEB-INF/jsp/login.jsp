<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Secure Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet">

<style>

/* Beautiful gradient background */
body{
    height:100vh;
    background: linear-gradient(135deg, #4facfe, #00f2fe);
    display:flex;
    justify-content:center;
    align-items:center;
}

/* Glassmorphism card */
.login-card{
    width:420px;
    border:none;
    border-radius:20px;
    backdrop-filter: blur(15px);
    background: rgba(255,255,255,0.15);
    box-shadow: 0 8px 32px rgba(0,0,0,0.2);
}

/* Title styling */
.brand-title{
    font-weight:700;
    letter-spacing:1px;
}

/* Inputs */
.form-control{
    border-radius:10px;
}

/* Button */
.btn-login{
    border-radius:10px;
    font-weight:600;
    transition:0.3s;
}

.btn-login:hover{
    transform: translateY(-2px);
    box-shadow:0 5px 15px rgba(0,0,0,0.3);
}

/* Footer link */
.register-link{
    text-decoration:none;
    font-weight:500;
}

.register-link:hover{
    text-decoration:underline;
}

</style>

</head>

<body>

<div class="card login-card p-4">

    <div class="text-center mb-3">
        <h2 class="text-white brand-title">E-Commerce</h2>
        <small class="text-light">Secure Login Portal</small>
    </div>

    <!-- Alerts -->

    <c:if test="${param.error == 'true'}">
        <div class="alert alert-danger">
            Invalid username or password
        </div>
    </c:if>

    <c:if test="${param.registered == 'true'}">
        <div class="alert alert-success">
            Registration successful. Please login.
        </div>
    </c:if>

    <form method="post"
          action="${pageContext.request.contextPath}/perform_login">

        <div class="mb-3">
            <label class="form-label text-white">Username</label>
            <input type="text"
                   name="username"
                   class="form-control"
                   placeholder="Enter username"
                   required />
        </div>

        <div class="mb-4">
            <label class="form-label text-white">Password</label>
            <input type="password"
                   name="password"
                   class="form-control"
                   placeholder="Enter password"
                   required />
        </div>

        <button type="submit"
                class="btn btn-dark w-100 btn-login">
            Login
        </button>

    </form>

    <div class="text-center mt-3 text-white">
        Don't have an account?
        <a class="register-link text-warning"
           href="${pageContext.request.contextPath}/register">
           Register
        </a>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
