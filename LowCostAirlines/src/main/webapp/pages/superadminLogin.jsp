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
    <main id = "registration">
      <a href = "index"><img src="resources/images/logoWhite.svg" alt="logo"></a>
      <a href="index">Fast Flight</a>
       <h1><m:message locale="${language}" key="admin.administrator"/></h1>
        <form action="superadmin" method="post">
            <fieldset>
            <p style="color: white">${message }</p>
                <label for="name"><m:message locale="${language}" key="email"/>:</label><input type="text" name="email" value="${email }">
                <label for="name"><m:message locale="${language}" key="password"/>:</label><input type="password" name="password">
            </fieldset>
            <a href="forgotPassword" style="font-size: 15px"><m:message locale="${language}" key="forgotpassword"/></a>
            <button type="submit"><m:message locale="${language}" key="signin"/></button>
        </form>
    </main>
     <footer>
         <span><m:message locale="${language}" key="footer.right"/></span>
     </footer>
</body>
</html>