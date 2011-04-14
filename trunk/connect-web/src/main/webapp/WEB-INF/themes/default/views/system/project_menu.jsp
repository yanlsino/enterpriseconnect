<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty features}">
<ul>
	<c:forEach var="feature" items="${features}">
	<c:if test="${feature.show}">
	<li <c:if test="${feature.code eq featureCode}">class="actived"</c:if>>
		<a href="${base}/${project.uniqueId}/${feature.code}" title="${feature.label}">
			<span>${feature.label}</span>
		</a>
	</li>
	</c:if>
	</c:forEach>
</ul>
</c:if>
