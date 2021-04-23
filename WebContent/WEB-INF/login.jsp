<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="assets/css/login.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="container">
		<form action="login.do" method="POST">
			<h1>Account Login</h1>
			<c:forEach var="error" items="${form.formErrors}">
				<h3 style="color:red"> ${error} </h3>
			</c:forEach>
			<table>
          		<c:forEach var="field" items="${form.visibleFields}">
          			<tr>
          				<td>
          					<div class="form-group">
          						<c:if test="${field.name == 'userName'}">
						            <label>Email</label>
									<input type="${field.type}" class="form-control" id="emailAddr" name="${field.name}" value="${field.value}"/>           	
					            </c:if>
					            <c:if test="${field.name == 'password'}">
						            <label>Password</label>
									<input type="${field.type}" class="form-control" id="password" name="${field.name}" value="${field.value}"/>   
									<span><i class="fa fa-eye" id="eye" onclick="toggle()"></i></span>
					            </c:if>
          					</div>
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
								function func() {
									document.getElementById("atag").href = "forgot.do?emailAddress="+document.getElementById("emailAddr").value;
									alert("Your password was sent to your email address");
								}
							</script>
          				</td>
          			</tr>
          			<tr>
          				<td style="color: red;padding-bottom: 15px">
          					${field.error}
          				</td>
          			</tr>
          		</c:forEach>
          	</table>
			<!-- <input type="hidden" name="emailAddress" id="email" value=""> -->
			<a href="forgot.do" id="atag" onclick="func()">Forgot password ?</a>
			<input type="submit" class="btn" value="Login">
		</form>
	</div>
</body>
</html>