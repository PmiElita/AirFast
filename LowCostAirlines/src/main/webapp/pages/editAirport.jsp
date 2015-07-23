<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <%@ taglib prefix="m" uri="../WEB-INF/showMessage.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>AirFast</title>
<link rel="stylesheet" href="resources/css/edit.css">
		<script src= "resources/js/edit.js"></script>
<script src="resources/js/jquery-1.11.3.min.js"></script>
</head>
<body>
	<jsp:include page="headers/adminHeader.jsp"></jsp:include>
	<main id="registration"> <a href="index"><img
		src="resources/images/logoWhite.svg" alt="logo"></a> <a
		href="index">Fast Flight</a>
	<h1><m:message locale="${language}" key="admin.editairport"/></h1>
	
         
          <div class ="edit">
             <p>${message}</p>
                 <label for="to"><m:message locale="${language}" key="airport"/>:</label>
           <select name="airport" id="_airport" >
				<c:forEach items="${airports }" var="airport">
				<c:choose>
                         <c:when test="${airport.id != selectedAirport.id}">
                        <option value="${airport.id}">${airport.name}</option>
                         </c:when>
                        <c:otherwise>
                        <option value="${selectedAirport.id}" selected>${selectedAirport.name}</option>
                        </c:otherwise>
                        </c:choose>		
				</c:forEach>
			</select>
		
             	<button id="edit" onclick="editAirport()"><m:message locale="${language}" key="edit"/></button>
</div>
<div id="editForm" style="display: none">
<form action="editAirport" method="post"  >
		<fieldset>
				 <select name="city" id="_city" >
				<c:forEach items="${cities }" var="city" >
				<c:choose>
                         <c:when test="${city.id != selectedCity.id}">
                        <option value="${city.id}">${city.name}</option>
                         </c:when>
                        <c:otherwise>
                        <option value="${selectedCity.id}" selected>${selectedCity.name}</option>
                        </c:otherwise>
                        </c:choose>		
				</c:forEach>
			</select>
			<input type="text" name="airportName" id="airportName" required pattern ="[^><]*" > 
            <input type="text" name="airportId" id="airportId" hidden>
		</fieldset>
		<button type="submit" name="save" id="_save" ><m:message locale="${language}" key="save"/></button>
	</form>
	</div>
	<form action="editAirport" method="post">
		<fieldset>
		<label for="to"><m:message locale="${language}" key="city"/>:</label>
				 <select name="city"  >
				<c:forEach items="${cities }" var="city">
				<c:choose>
                         <c:when test="${city.id != selectedCity.id}">
                        <option value="${city.id}">${city.name}</option>
                         </c:when>
                        <c:otherwise>
                        <option value="${selectedCity.id}" selected>${selectedCity.name}</option>
                        </c:otherwise>
                        </c:choose>		
				</c:forEach>
			</select>
			<label for="to"><m:message locale="${language}" key="airport.name"/>:</label>
			<input type="text" name="airportName" id="airportName" pattern ="[^><]*"  required> 

		</fieldset>
		<button type="submit" name="add"><m:message locale="${language}" key="airport.add"/></button>
	</form>
	</main>
	<footer>
	 <span><m:message locale="${language}" key="footer.right"/></span>
	</footer>
</body>
</html>