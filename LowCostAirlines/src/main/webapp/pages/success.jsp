<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="m" uri="../WEB-INF/showMessage.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>AirFast</title>
<link rel="stylesheet" href="resources/css/reg.css">
<link href='http://fonts.googleapis.com/css?family=Dosis:200,400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
<jsp:include page="headers/unloginedHeader.jsp"></jsp:include>
	<main id="registration"> <a href="index"><img
		src="resources/images/logoWhite.svg" alt="logo"></a> <a href="index">Home page</a>
	<h1>${message} </h1>
	

	</main>
	<footer>
		     <span><m:message locale="${language}" key="footer.right"/></span>
	</footer>
</body>
</html>