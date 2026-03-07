<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <style>

        body{
            height:100vh;
            background: linear-gradient(135deg,#00c6ff,#0072ff);
            display:flex;
            align-items:center;
            justify-content:center;
            font-family:'Segoe UI',sans-serif;
        }

        .register-card{
            width:100%;
            max-width:450px;
            border-radius:18px;
            border:none;
        }

        .register-header{
            background: linear-gradient(45deg,#28a745,#20c997);
            border-radius:18px 18px 0 0;
            padding:20px;
        }

        .form-control{
            border-radius:10px;
            padding:10px;
        }

        .form-control:focus{
            border-color:#28a745;
            box-shadow:0 0 0 .15rem rgba(40,167,69,.25);
        }

        .btn-register{
            border-radius:10px;
            padding:10px;
            font-weight:600;
            background: linear-gradient(45deg,#28a745,#20c997);
            border:none;
            transition:.3s;
        }

        .btn-register:hover{
            transform:translateY(-2px);
            box-shadow:0 6px 18px rgba(0,0,0,.2);
        }

        .login-link{
            text-decoration:none;
            font-weight:600;
            color:#0072ff;
        }

        .login-link:hover{
            text-decoration:underline;
        }

    </style>
    
    <script>

		function togglePassword(){
		
		    const passwordField = document.getElementById("password");
		    const button = event.target;
		
		    if(passwordField.type === "password"){
		        passwordField.type = "text";
		        button.innerText = "Hide";
		    }else{
		        passwordField.type = "password";
		        button.innerText = "Show";	
		    }
		
		}

</script>
</head>

<body>

<div class="card shadow-lg register-card">

    <div class="register-header text-white text-center">
        <h3>Create Account</h3>
        <small>Join our ecommerce platform</small>
    </div>

    <div class="card-body p-4">

        <!-- ERROR MESSAGE -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center">
                ${error}
            </div>
        </c:if>

        <form method="post"
              action="${pageContext.request.contextPath}/perform_register">

            <div class="mb-3">
                <label class="form-label fw-semibold">Username</label>
                <input type="text"
                       name="username"
                       class="form-control"
                       placeholder="Enter username"
                       required>
            </div>

            <div class="mb-3">
                <label class="form-label fw-semibold">Email</label>
                <input type="email"
                       name="email"
                       class="form-control"
                       placeholder="Enter email"
                       required>
            </div>

			<div class="mb-4">
				<label class="form-label fw-semibold">Password</label>

				<div class="input-group">
					<input type="password" name="password" id="password"
						class="form-control" placeholder="Create password" required>

					<button class="btn btn-outline-secondary" type="button"
						onclick="togglePassword()">Show</button>
				</div>
			</div>

				<button class="btn btn-register w-100">
                Register
            </button>

        </form>

        <div class="text-center mt-4">
            <small>
                Already registered?
                <a href="${pageContext.request.contextPath}/login"
                   class="login-link">
                   Login here
                </a>
            </small>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
