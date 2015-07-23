<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="my" uri="../WEB-INF/myTickets.tld"%>
     <%@ taglib prefix="m" uri="../WEB-INF/showMessage.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AirFast</title>
    <link rel="stylesheet" href="resources/css/ticket.css">
   
</head>
<body>
     <jsp:include page="headers/loginedHeader.jsp"></jsp:include>
     <main>
        <h1><m:message locale="${language}" key="index.Title"/></h1>
         </main>
     <section>
     <my:tickets tickets="${tickets }" locale="${language }"/>
       
     </section>
     <footer>

         <span><m:message locale="${language}" key="footer.right"/></span>
     </footer>
</body>
</html>