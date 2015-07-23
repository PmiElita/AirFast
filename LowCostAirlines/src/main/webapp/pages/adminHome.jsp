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
	<jsp:include page="headers/adminHeader.jsp"></jsp:include>

    <main id="registration">
       <h1> <m:message locale="${language}" key="admin.administrator"/></h1>
         <div id="findFlight">
           <a href="createFlight"> <button id = "findFlight" ><m:message locale="${language}" key="admin.createflight"/></button></a>
           <a href="myFlights"> <button id = "myFlights"><m:message locale="${language}" key="admin.myflights"/></button></a>
           <a href="editCountry"><button id = "editCountry"><m:message locale="${language}" key="admin.editcountry"/></button></a>
           <a href="editCity"><button id = "editCity"><m:message locale="${language}" key="admin.editcity"/></button></a>
           <a href="editAirport"><button id = "editAirport"><m:message locale="${language}" key="admin.editairport"/></button></a>
           </div>
    </main>
</body>
</html>