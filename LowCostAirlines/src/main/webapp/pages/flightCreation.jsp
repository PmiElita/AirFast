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

</head>
<body>
	<jsp:include page="headers/adminHeader.jsp"></jsp:include>
	<main id="registration"> <a href="index"><img
		src="resources/images/logoWhite.svg" alt="logo"></a> <a
		href="index">Fast Flight</a>
	<h1><m:message locale="${language}" key="flight.create"/></h1>
	<form action="createFlight" method="post">
             <fieldset class = "top">
             <p>${message }</p>
                 <label for="from"><m:message locale="${language}" key="flight.from"/></label>
                 
                 <select name="airportFrom" id="_airport">
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
             </fieldset>
             <fieldset class = "bottom">
                 <label for="to"><m:message locale="${language}" key="flight.to"/></label>
           
                		<select name="airportTo" id="_airport">
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
             </fieldset>
             <fieldset class="right">
             <label for="to"><m:message locale="${language}" key="flight.date.from"/></label>
                 <input type="date" name="fromDate" value="${dateFrom }">
                 <input type="time" name="fromTime" value="${timeFrom }">
                 </fieldset>
                 <fieldset>
                 <label for="to"><m:message locale="${language}" key="flight.date.to"/></label>
                 <input type="date" name="toDate" value="${dateTo }">
                    <input type="time" name="toTime" value="${timeTo}">
             </fieldset>

		<fieldset>
			<label for="name"><m:message locale="${language}" key="flight.minprice"/></label><input type="text" name="minPrice" value="${flight.minPrice }"  pattern ="[0-9]*.[0-9]*" required> 
			<label for="name"><m:message locale="${language}" key="flight.maxprice"/></label><input type="text" name="maxPrice" value="${flight.maxPrice }" pattern ="[0-9]*.[0-9]*"   required> 
			<label for="name"><m:message locale="${language}" key="flight.primeprice"/></label><input type="text" name="primePrice" value="${flight.primePrice }" pattern ="[0-9]*.[0-9]*"  required> 
			</fieldset>
			<fieldset>
			<label for="name"><m:message locale="${language}" key="flight.ishot"/></label><input style="margin-left:20px" type="checkbox" name="isHot"  <c:if test="${flight.isHot }"> checked=true </c:if> >
			<label ><m:message locale="${language}" key="flight.hotprice"/></label> <input type="text" name="hotPrice" value="${flight.hotPrice } " pattern ="[0-9]*.[0-9]*" > 
			</fieldset>
			<fieldset>
			<label for="name"><m:message locale="${language}" key="flight.seats"/></label><input type="text" name="seats" value="${flight.seats }" pattern ="[0-9]*"  required> 
		</fieldset>
		<button type="submit"><m:message locale="${language}" key="save"/></button>
	</form>
	</main>
	<footer>
	 <span><m:message locale="${language}" key="footer.right"/></span>
	</footer>
</body>
</html>