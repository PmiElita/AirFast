<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" uri="../WEB-INF/user_flights.tld"%>
<%@ taglib prefix="m" uri="../WEB-INF/showMessage.tld"%>
<!DOCTYPE html>
<html lang="${language }">
<head>
<meta charset="UTF-8">
<title>AirFast</title>
<link rel="stylesheet" href="resources/css/style.css">

	<script src= "resources/js/selectCity.js"></script>
<script src="resources/js/jquery-1.11.3.min.js"></script>
	
</head>
<body>
	<jsp:include page="headers/unloginedHeader.jsp"></jsp:include>
	<main>
	<h1> <m:message locale="${language }" key="index.Title"/> </h1>
	<form action="index" method="post">
		<fieldset class="top">
			<label for="from"><m:message locale="${language }" key="flight.from"/></label> 
			<select name="airportFrom" id="_airportFrom">
				<c:forEach items="${airportsFrom }" var="airport">
				<c:choose>
                         <c:when test="${airport.id != selectedAirportFrom.id}">
                         <option value="${airport.id}">${airport.name}</option> 
                         </c:when>
                        <c:otherwise><option value="${selectedAirportFrom.id}" selected>${selectedAirportFrom.name}</option>
                        </c:otherwise>
                        </c:choose>
				</c:forEach>
				
			</select>
			 <select name="cityFrom" id="_cityFrom" onchange="loadAirportFrom(this)">
				
				<c:forEach items="${citiesFrom }" var="city">
				<c:choose>
                         <c:when test="${city.id != selectedCityFrom.id}">
                        <option value="${city.id}">${city.name}</option>
                         </c:when>
                        <c:otherwise>
                        <option value="${selectedCityFrom.id}" selected>${selectedCityFrom.name}</option>
                        </c:otherwise>
                        </c:choose>
	
				</c:forEach>
			</select> 
			<select name="countryFrom" id="_countryFrom" onchange="loadCityFrom(this)">
				<c:forEach items="${countriesFrom }" var="country">
				<c:choose>
                         <c:when test="${country.id != selectedCountryFrom.id}">
                        <option value="${country.id}">${country.name}</option>
                         </c:when>
                        <c:otherwise>
                        <option value="${selectedCountryFrom.id}" selected>${selectedCountryFrom.name}</option>
                        </c:otherwise>
                        </c:choose>		
				</c:forEach>
			</select>


		</fieldset>
		<fieldset class="bottom">
			<label for="to"><m:message locale="${language }" key="flight.to"/></label> 
				<select name="airportTo" id="_airportTo" >
				<c:forEach items="${airportsTo }" var="airport">
				<c:choose>
                         <c:when test="${airport.id != selectedAirportTo.id}">
                         <option value="${airport.id}">${airport.name}</option> 
                         </c:when>
                        <c:otherwise><option value="${selectedAirportTo.id}" selected>${selectedAirportTo.name}</option>
                        </c:otherwise>
                        </c:choose>
				</c:forEach>
				
			</select>
			 <select name="cityTo" id="_cityTo" onchange="loadAirportTo(this)">
				
				<c:forEach items="${citiesTo }" var="city">
				<c:choose>
                         <c:when test="${city.id != selectedCityTo.id}">
                        <option value="${city.id}">${city.name}</option>
                         </c:when>
                        <c:otherwise>
                        <option value="${selectedCityTo.id}" selected>${selectedCityTo.name}</option>
                        </c:otherwise>
                        </c:choose>
	
				</c:forEach>
			</select> 
			<select name="countryTo" id="_countryTo"  onchange="loadCityTo(this)">
				<c:forEach items="${countriesTo }" var="country">
				<c:choose>
                         <c:when test="${country.id != selectedCountryTo.id}">
                        <option value="${country.id}">${country.name}</option>
                         </c:when>
                        <c:otherwise>
                        <option value="${selectedCountryTo.id}" selected>${selectedCountryTo.name}</option>
                        </c:otherwise>
                        </c:choose>		
				</c:forEach>
			</select>
		</fieldset>
		<fieldset class="right">
			<input type="date" name="date" value="${date }" required>
		</fieldset>
		<button id="findFlight" type="submit"><m:message locale="${language }" key="flight.search"/></button>
	</form>
	</main>

	<section>
		<my:flights flights="${flights }" locale="${language }" />

	</section>
	<input id="citiesId" value="${cities}" type="hidden" />
	<footer>
		<a href="adminLogin" style="color: black"><m:message locale="${language }" key="index.admin.login"/></a> <span><m:message locale="${language }" key="footer.right"/></span>
	</footer>
<script type="text/javascript"></script>
</body>
</html>