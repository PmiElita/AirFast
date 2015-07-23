<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib prefix="m" uri="../WEB-INF/showMessage.tld"%>
    <%@ taglib prefix="my" uri="../WEB-INF/showAdmins.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AirFast</title>
    <link rel="stylesheet" href="resources/css/admins.css">

</head>
<body>
     <jsp:include page="headers/superadminHeader.jsp"></jsp:include>
 
     <section>
     <my:showAdmins admins="${admins }" locale="${language }"/>
       
     </section>
     <footer>
      <span><m:message locale="${language}" key="footer.right"/></span>
     </footer>
</body>
</html>