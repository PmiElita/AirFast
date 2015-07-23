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
        <form action="newPassword" method="post">
            <fieldset>
                <label for="name"><m:message locale="${language}" key="password.new"/>:</label><input type="password" name="password" required>
                <input type="text" name="code" value="${code }" hidden>
            </fieldset>
            <button type="submit"><m:message locale="${language}" key="save"/></button>
        </form>
    </main>
     <footer>
         <span><m:message locale="${language}" key="footer.right"/></span>
     </footer>
</body>
</html>