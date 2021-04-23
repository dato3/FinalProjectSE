<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
	<div class="container">
		<form action="login2.do" method="POST">
			<h1>Account Login</h1>
			<label>Verification Code</label>
			<input type ="text" class="form-control" name="veriftext">
			<c:if test="${incpsw != null}">
				<p style="color:red">${incpsw}</p>
			</c:if>
			<input type="submit" class="btn" value="Login">
		</form>
	</div>
</body>
</html>