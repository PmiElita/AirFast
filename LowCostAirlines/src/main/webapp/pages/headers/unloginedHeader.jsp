  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="m" uri="../../WEB-INF/showMessage.tld"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
		<a  href="index"><img src="resources/images/logo.svg" alt="logo"></a> <span>Fast
			Flight</span>
			
		<nav>
			<a class="but" href="registration"><m:message locale="${language }" key="header.registration"/></a> <a class="but" href="login"><m:message locale="${language }" key="header.login"/></a>

		</nav>
		<c:choose>
                         <c:when test="${language == 'en'}">
                           <p>en<p>
                         </c:when>
                        <c:otherwise>  <p>укр<p>
                        </c:otherwise>
                        </c:choose>
                        <c:choose>
                         <c:when test="${language == 'en'}">
                              <a href="changeLanguage">укр</a>
                         </c:when>
                        <c:otherwise>   <a href="changeLanguage">en</a>
                        </c:otherwise>
                        </c:choose>
     
	</header>