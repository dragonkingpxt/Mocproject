<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Login</title>
</head>
<body>
	<div class="pannel pannel-primary col-md-8 col-md-offset-2">
		<form:form modelAttribute="user" action="j_spring_security_check" method="post"
			cssClass="form-horizontal">
			<div>${message}</div>
			<h1>${user_error}</h1>
			<c:if test="${param.error == 'true'}">
				<div style="color: red; margin: 10px 0px;">

					Login Failed!!!<br /> Reason :
					User or password is incorrect

				</div>
			</c:if>
			<div>
			<form:errors path="id"></form:errors>
				<form:input path="id" placeholder="Username" cssClass="form-control" />
			</div>
			${pass_error}
			<div>
			<form:errors path="password"></form:errors>
				<form:input path="password" placeholder="Password"
					cssClass="form-control" />
			</div>
			<form:button type="submit" class="btn btn-default">Login</form:button>
		</form:form>
	</div>
</body>
</html>