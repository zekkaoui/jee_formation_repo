<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="titre.listformations"/></title>
		<style type="text/css">
			.successMSG{
				padding:4px;
				margin:10px 0px;
				background-color: green;
				color: white;
				font-weight: bold;
			}
		</style>
		</head>
	<body>
		
		<h3><spring:message code="titre.listformations"/> :</h3>
		
		
		<c:url value="/logout" var="logoutUrl" />

		<!-- csrt for log out-->
		<form action="${logoutUrl}" method="post" id="logoutForm">
		  <input type="hidden"
			name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		</form>
	
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
	
		<sec:authorize access="isAuthenticated()">
			<h2>
				Welcome : <sec:authentication property="principal.username"/> | 
				<a href="javascript:formSubmit()"> Logout</a>
			</h2>
		</sec:authorize>
	
		<!-- Messag de succès -->
		<c:if test="${not empty msg}">
			<div><span class="successMSG"><spring:message code="${msg}"/></span></div>
			<br/>
		</c:if>
		
		<div style="margin: 4px;">
			<c:url value="/formation/nouvelleFormation" var="urlCreation" />
			<a href="${urlCreation}"><spring:message code="ajout.ajouter.libelle"/></a>
		</div>
		<table border="1">
			<thead>
                <tr>
                    <th><spring:message code="colonne.identifiant"/></th>
                    <th><spring:message code="colonne.thematique"/></th>
                    <th><spring:message code="colonne.date"/></th>
                    <th><spring:message code="colonne.prix"/></th>
                    <th><spring:message code="colonne.type"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${formations}" var="formation">
                    <tr>
                        <td><c:out value="${formation.id}"/></td>
                        <td><c:out value="${formation.theme}"/></td>
                        <td><fmt:formatDate type="both" value="${formation.dateDebut}" 
                        pattern="dd/MM/yyyy hh:mm:ss"/></td>
                        <td><fmt:formatNumber type="currency" value="${formation.prix}" currencySymbol=" "/></td>
                        <td><c:out value="${formation.type}"/></td>
                        <td>
                            <c:url value="/formation/modifierFormation" var="url">
                                <c:param name="idFormation" value="${formation.id}"/>
                            </c:url>
                            <a href="${url}">
                                <spring:message code="modification.modifier.libelle" />
                            </a>
                        </td>
                        <td>
                            <c:url value="/formation/supprimerFormation" var="url">
                                <c:param name="idFormation" value="${formation.id}"/>
                            </c:url>
                            <a href="${url}" onclick="return confirm('<spring:message code="suppression.supprimer.confirmation" />');">
                                <spring:message code="suppression.supprimer.libelle" />
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
		</table>
	</body>
</html>