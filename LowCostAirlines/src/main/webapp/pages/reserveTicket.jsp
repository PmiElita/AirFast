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
    <link href='http://fonts.googleapis.com/css?family=Dosis:200,400,700' rel='stylesheet' type='text/css'>
</head>
<body>
     <jsp:include page="headers/loginedHeader.jsp"></jsp:include>
     <main>
        <h1><m:message locale="${language}" key="index.Title"/></h1>
         </main>
     <section>
        <div class = "flight" id="flight1">
            <div class="leftFounded">
                <p><m:message locale="${language}" key="flight.from"/>
                    <span class="foundedFromCountry">${adressFrom}</span>
                </p>
                <p><m:message locale="${language}" key="flight.to"/> 
                    <span class="foundedFromCountry">${adressTo }</span>

                </p>
                <p><m:message locale="${language}" key="ticket.baggage"/>:
                <span>${ticket.bagageQuantity }</span>
                </p>
                <p><m:message locale="${language}" key="ticket.primeregistration"/>:
                <span>
                 <c:choose>
                           <c:when test="${ticket.isPrimeRegistration}"><m:message locale="${language}" key="yes"/></c:when> 
                           <c:otherwise><m:message locale="${language}" key="no"/></c:otherwise>
                </c:choose>
                </span>
                </p>
                <p><m:message locale="${language}" key="ticket.primeboarding"/>:
                <span>
                 <c:choose>
                           <c:when test="${ticket.isPrimeBoarding}"><m:message locale="${language}" key="yes"/></c:when> 
                           <c:otherwise><m:message locale="${language}" key="no"/></c:otherwise>
                </c:choose>
                </span>
                </p>
                
                <p><m:message locale="${language}" key="ticket.creationdate"/>:
                <span>${date }</span>
                </p>
            </div>
            <div id="rightFounded">
                  <p class="date"> ${dateFrom }</p>
               <p class="date"> ${dateTo }</p>
                <p class="_price"><m:message locale="${language}" key="ticket.price"/>:<span class="price">${ticket.price }</span><m:message locale="${language}" key="uah"/></p>
               
            </div>
            <div id="bottomFounded">
            <form action="reserveTicket" method="post">
              <button name="Reserve" value="buy"><m:message locale="${language}" key="ticket.reserve"/></button>
                <button class="cancel" name="cancel" value="cancel"><m:message locale="${language}" key="ticket.cancel"/></button>
                </form>
            </div>
        </div>
        
     </section>
     <footer>
         <span><m:message locale="${language}" key="footer.right"/></span>
     </footer>
</body>
</html>