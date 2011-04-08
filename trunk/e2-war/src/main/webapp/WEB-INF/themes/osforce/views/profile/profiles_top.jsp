<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="projects-recent">
		<c:forEach var="statistic" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<a href="${base}/${statistic.linkedEntity.project.uniqueId}/profile">
				<c:choose>
					<c:when test="${not empty statistic.linkedEntity.logo}">
					<img class="thumbnail" src="${base}/logo/download/${statistic.linkedEntity.logo.id}/40x40"/>
					</c:when>
					<c:otherwise>
					<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${statistic.linkedEntity.project.category.code}.png" with="40" height="40"/>
					</c:otherwise>
				</c:choose>
				</a>
				<div class="project-body">
					<a href="${base}/${statistic.linkedEntity.project.uniqueId}/profile">${statistic.linkedEntity.title}</a>
					<p>${statistic.linkedEntity.shortDescription}</p>
				</div>
				<div class="clear"></div>
			</li>
		</c:forEach>
		</ul>
	</div>
</div>