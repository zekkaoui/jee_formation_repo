<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="titre.formation"/> : ${nomFormation}</title>
		</head>
	<body>
		Titre : ${nomFormation}, du framework ${nomFramework}<br/>
		Libelle :  <spring:message code="libelle.formation" arguments="${nomFormation}, ${nomFramework}" />
	</body>
</html>