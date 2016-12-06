<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="titre.error"/></title>
		</head>
	<body>
		<h1 style="color: red;">Oops! Une erreur s'est produite</h1>
		<c:if test="${not empty errCode}">
			<h3><spring:message code="${errCode}"/></h3>
		</c:if>
	</body>
</html>