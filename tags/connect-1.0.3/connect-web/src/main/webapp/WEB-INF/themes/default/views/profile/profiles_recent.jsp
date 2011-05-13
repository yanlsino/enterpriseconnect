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
		<c:forEach var="profile" items="${page.result}" varStatus="status">
			<li class="profile <c:if test="${status.last}">last</c:if>">
				<a href="${base}/${profile.project.uniqueId}/profile">
				<c:choose>
					<c:when test="${not empty profile.logo}">
					<img class="top left thumbnail" src="${base}/logo/download/${profile.logo.id}/40x40"/>
					</c:when>
					<c:otherwise>
					<img class="top left thumbnail" src="${base}/themes/${theme.name}/stock/${profile.project.category.code}.png" with="40" height="40"/>
					</c:otherwise>
				</c:choose>
				</a>
				<div class="profile-body">
					<a href="${base}/${profile.project.uniqueId}/profile">${profile.title}</a>
					<p>${profile.shortDescription}</p>
				</div>
				<div class="clear"></div>
			</li>
		</c:forEach>
		</ul>
	</div>
</div>