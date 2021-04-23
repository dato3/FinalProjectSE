<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="assets/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="container">
		<form action="changePassword.do" method="POST">
			<h1>Change Password</h1>
			<label>Enter new Password</label>
			<input type ="password" class="form-control" id="password" name="password">   
			<span><i class="fa fa-eye" id="eye" onclick="toggle()"></i></span>
			<label>Confirm Password</label>
			<input type ="password" class="form-control" id="password2" name="confirmPassword">   
			<span><i class="fa fa-eye" id="eye2" onclick="toggle2()"></i></span>
			<script>
				var state = false;
				function toggle() {
					if(state) {
						document.getElementById("password").setAttribute("type", "password");
						state = false;
						document.getElementById("eye").style.color='#7a797e';
					}
					else {
						document.getElementById("password").setAttribute("type", "text");
						state = true;
						document.getElementById("eye").style.color='#478ac9';
					}
				}
				var state2 = false;
				function toggle2() {
					if(state2) {
						document.getElementById("password2").setAttribute("type", "password");
						state2 = false;
						document.getElementById("eye2").style.color='#7a797e';
					}
					else {
						document.getElementById("password2").setAttribute("type", "text");
						state2 = true;
						document.getElementById("eye2").style.color='#478ac9';
					}
				}
			</script>
			<c:if test="${incpsw != null}">
				<p style="color:red">${incpsw}</p>
			</c:if>
			<input type="submit" class="btn" value="Change">
		</form>
	</div>
</body>
</html>