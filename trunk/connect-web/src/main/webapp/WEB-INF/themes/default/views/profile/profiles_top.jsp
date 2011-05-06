<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<ul class="profiles-recent">
		<c:forEach var="statistic" items="${page.result}" varStatus="status">
			<li class="profile <c:if test="${status.last}">last</c:if>">
				<a href="${base}/${statistic.linkedEntity.project.uniqueId}/profile">
				<c:choose>
					<c:when test="${not empty statistic.linkedEntity.logo}">
					<img class="top left thumbnail" src="${base}/logo/download/${statistic.linkedEntity.logo.id}/40x40"/>
					</c:when>
					<c:otherwise>
					<img class="top left thumbnail" src="${base}/themes/${theme.name}/stock/${statistic.linkedEntity.project.category.code}.png" with="40" height="40"/>
					</c:otherwise>
				</c:choose>
				</a>
				<div class="profile-body">
					<a href="${base}/${statistic.linkedEntity.project.uniqueId}/profile">${statistic.linkedEntity.title}</a>
					<p>${statistic.linkedEntity.shortDescription}</p>
				</div>
				<div class="clear"></div>
			</li>
		</c:forEach>
		</ul>
	</div>
</div>