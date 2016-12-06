<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><spring:message code="titre.nouvelleFormation"/></title>
		<style type="text/css">
			.errorsStyle{
				color: red;
			}
		</style>
		</head>
	<body>
		
		<h3>
			<c:choose>
				<c:when test="${formationForm.id != null}">
					<spring:message code="titre.modifierFormation"/> :
				</c:when>
				<c:otherwise>
					<spring:message code="titre.nouvelleFormation"/> :
				</c:otherwise>
			</c:choose>
		</h3>
		
		<form:form method="post" modelAttribute="formationForm" action="enregistrerFormation">
			<form:hidden path="id"/>
			<table border="0">
                 <tr>
                     <td><spring:message code="nouvelleFormation.theme.libelle"/></td>
                     <td>
                     	<form:input path="theme" type="text"/>
                     	<br/><form:errors path="theme" class="errorsStyle" />
                     </td>
                 </tr>
                 <tr>
                     <td><spring:message code="nouvelleFormation.prix.libelle"/></td>
                     <td>
                     	<form:input path="prix" type="number"/>
                     	<br/><form:errors path="prix" class="errorsStyle"/>
                     </td>
                 </tr>
                 <tr>
                     <td><spring:message code="nouvelleFormation.dateDebut.libelle"/></td>
                     <td>
                     	<form:input path="dateDebut" type="date"/>
                     	<br/><form:errors path="dateDebut" class="errorsStyle" data-date-format="dd/MM/yyyy"/>
                     </td>
                 </tr>
                 
                 <tr>
                     <td><spring:message code="nouvelleFormation.type.libelle"/></td>
                     <td>
                     	<form:select path="type">
                     		<form:option value="" label="--- Choisir ---" />
                     		<form:option value="EN CLASSE" label="En classe" />
                     		<form:option value="EN LIGNE" label="En ligne" />
                     		<%-- <form:options items="${typeList}" itemLabel="description"/> --%>
                     	</form:select>
                     	<br/><form:errors path="type" class="errorsStyle"/>
                     </td>
                 </tr>
                 <tr>
                     <td></td>
                     <td>
                     	<br/><input type="submit" value="Enregistrer"/>
                     </td>
                 </tr>
	            </tbody>
			</table>
		</form:form>
	</body>
</html>