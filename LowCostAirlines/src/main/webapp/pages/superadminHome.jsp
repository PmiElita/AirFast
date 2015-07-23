<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="m" uri="../WEB-INF/showMessage.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AirFast</title>
    <link rel="stylesheet" href="resources/css/myPage.css">

</head>
<body>
	<jsp:include page="headers/superadminHeader.jsp"></jsp:include>

    <main id="registration">
       <h1><m:message locale="${language}" key="admin.administrator"/></h1>
         <div id="findFlight">
           <a href="adminRegistration"> <button  ><m:message locale="${language}" key="superadmin.register.admin"/></button></a>
           <a href="showAdmins"> <button  ><m:message locale="${language}" key="superadmin.administrators"/></button></a>
           </div>
    </main>
        <footer>
         <span><m:message locale="${language}" key="footer.right"/></span>
     </footer>
</body>
</html>