<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="my" uri="../WEB-INF/showMyFlights.tld"%>
     <%@ taglib prefix="m" uri="../WEB-INF/showMessage.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AirFast</title>
    <link rel="stylesheet" href="resources/css/flight.css">
</head>
<body>
     <jsp:include page="headers/adminHeader.jsp"></jsp:include>
     <main>
        <h1><m:message locale="${language}" key="index.Title"/></h1>
         </main>
     <section>
     <my:flights flights="${flights }" locale="${language }"/>
       
     </section>
     </main>
     <footer>

          <span><m:message locale="${language}" key="footer.right"/></span>
     </footer>
</body>
</html>