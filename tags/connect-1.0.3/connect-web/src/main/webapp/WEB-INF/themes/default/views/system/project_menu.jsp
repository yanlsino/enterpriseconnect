<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensourceforce.org/tags" prefix="osf"%>

<c:if test="${not empty features}">
<ul>
	<c:forEach var="feature" items="${features}">
	<li <c:if test="${feature.code eq featureCode}">class="actived"</c:if>>
		<a href="${base}/${project.uniqueId}/${feature.code}" title="${feature.label}">
			<span>${feature.label}</span>
		</a>
	</li>
	</c:forEach>
</ul>
</c:if>
