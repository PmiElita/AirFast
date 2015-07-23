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
	<h1><m:message locale="${language}" key="admin.editcountry"/></h1>
	
         
          <div class ="edit">
            <p>${message}</p>
                 <label for="to"><m:message locale="${language}" key="country"/>:</label>
           <select name="country" id="_country" >
				<c:forEach items="${countries }" var="country">
				<c:choose>
                         <c:when test="${country.id != selectedCountry.id}">
                        <option value="${country.id}">${country.name}</option>
                         </c:when>
                        <c:otherwise>
                        <option value="${selectedCountry.id}" selected>${selectedCountry.name}</option>
                        </c:otherwise>
                        </c:choose>		
				</c:forEach>
			</select>
		
             	<button id="edit" onclick="editCountry()"><m:message locale="${language}" key="edit"/></button>
</div>
<div id="editForm" style="display: none">
<form action="editCountry" method="post">
		<fieldset>
			<input type="text" name="countryName" id="countryName" pattern ="[^><]*" required> 
            <input type="text" name="countryId" id="countryId" hidden>
		</fieldset>
		<button type="submit" name="save" id="_save" ><m:message locale="${language}" key="save"/></button>
	</form>
	</div>
	<form action="editCountry" method="post">
		<fieldset> 
		<label for="to"><m:message locale="${language}" key="country.name"/>:</label>
			<input type="text" name="countryName" id="countryName" pattern ="[^><]*" required> 

		</fieldset>
		<button type="submit" name="add"><m:message locale="${language}" key="country.add"/></button>
	</form>
	</main>
	<footer>
	 <span><m:message locale="${language}" key="footer.right"/></span>
	</footer>
</body>
</html>