<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
           <%@ taglib prefix="m" uri="../WEB-INF/showMessage.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>AirFast</title>
<link rel="stylesheet" href="resources/css/reg.css">

</head>
<body>
	<jsp:include page="headers/loginedHeader.jsp"></jsp:include>
	<main id="registration"> <a href="index"><img
		src="resources/images/logoWhite.svg" alt="logo"></a> <a
		href="index">Fast Flight</a>
	<form action="editProfile" method="post">
		<fieldset>
		<p style="color:black">${message }</p>
			<label for="name"><m:message locale="${language}" key="registration.firstname"/>:</label><input type="text" name="firstName" value="${firstName}" pattern ="[^><]*"  required> 
			<label for="name"><m:message locale="${language}" key="registration.lastname"/>:</label><input type="text" name="lastName" value="${lastName}" pattern ="[^><]*" required> 
			</fieldset>
			<fieldset>
			
			<label for="name"><m:message locale="${language}" key="email"/>:</label><input type="text" name="email" value="${email}" required> 
			</fieldset>
		<button type="submit"><m:message locale="${language}" key="save"/></button>
	</form>
	</main>
	<footer>
		 <span><m:message locale="${language}" key="footer.right"/></span>
	</footer>
</body>
</html>