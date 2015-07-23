<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	         <%@ taglib prefix="m" uri="../WEB-INF/showMessage.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>AirFast</title>
<link rel="stylesheet" href="resources/css/reg.css">
</head>
<body>
<jsp:include page="headers/unloginedHeader.jsp"></jsp:include>
	<main id="registration"> <a href="index"><img
		src="resources/images/logoWhite.svg" alt="logo"></a> <a href="index">Fast
		Flight</a>
	<h1><m:message locale="${language}" key="email.type"/> </h1>
	<form action="forgotPassword" method="post">
		<fieldset>
			<p style="color: black">${message }</p>
			<label for="name"><m:message locale="${language}" key="email"/> </label><input type="email" name="email"
				value="${email }"> 
		</fieldset>

		<button type="submit"><m:message locale="${language}" key="email.send"/></button>
	</form>

	</main>
	<footer>
		 <span><m:message locale="${language}" key="footer.right"/></span>
	</footer>
</body>
</html>