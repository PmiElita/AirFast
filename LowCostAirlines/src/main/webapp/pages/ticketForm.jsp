<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
              <%@ taglib prefix="m" uri="../WEB-INF/showMessage.tld"%>
      	<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>AirFast</title>
<link rel="stylesheet" href="resources/css/ticket.css">

		<script src= "resources/js/rePrice.js"></script>
<script src="resources/js/jquery-1.11.3.min.js"></script>
</head>
<body>
	<jsp:include page="headers/loginedHeader.jsp"></jsp:include>
	<main id="registration"> 
	<h1><m:message locale="${language}" key="index.Title"/></h1>
	<section>
        <div class = "flight" id="flight1">
            <div class="leftFounded">
                <p><m:message locale="${language}" key="flight.from"/> 
                    <span class="foundedFromCountry">${adressFrom}</span>
                </p>
                <p><m:message locale="${language}" key="flight.to"/> 
                    <span class="foundedFromCountry">${adressTo }</span>

                </p>
                </div>
               
                           <div id="rightFounded">
                <p class="date">  ${dateFrom }</p>
               <p class="date"> ${dateTo }</p>  
                <p class="_price"><m:message locale="${language}" key="ticket.price"/>:<span class="price" id="_price" >${price}</span>uah</p>          
            </div>
            <form>
            <input type= text id="_inPrice" value ="${price }" hidden>
            <input type= text id="_inPrime" value ="${prime }" hidden>
            </form>
            <form class ="leftFounded" action="ticketAction" method="get" >
            <input type="text" value="${flightId }" name="flightId" hidden>
            <span><m:message locale="${language}" key="ticket.additions"/></span>
		<fieldset>
			<label for="name"><m:message locale="${language}" key="ticket.baggage"/>:</label><input type="text" name="baggage" pattern ="[0-9]*" required value="0" onchange="rePriceBaggage(this)"> 
			</fieldset>
			<fieldset>
			<label for="name"><m:message locale="${language}" key="ticket.primeregistration"/>:</label><input id="primeReg" style="margin-left:20px" type="checkbox" name="primeRegistration" onchange="primeChange(this)">
			<label for="name"><m:message locale="${language}" key="ticket.primeboarding"/>:</label><input style="margin-left:20px" type="checkbox" name="primeBoarding" onchange="primeChange(this)">
		
			</fieldset>
			<button type="submit" name="reserve" value="Reserve"><m:message locale="${language}" key="ticket.reserve"/></button>
		<button type="submit" name="buy" value="Buy"><m:message locale="${language}" key="ticket.buy"/></button>
		

	</form>
             </div>
             
      </section>
	
	</main>
	<footer>
	      <span><m:message locale="${language}" key="footer.right"/></span>
	</footer>
</body>
</html>