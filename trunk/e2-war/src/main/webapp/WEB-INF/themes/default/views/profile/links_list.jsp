<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
	<ul class="concerns-list">
	<c:forEach var="link" items="${page.result}" varStatus="status">
	<c:if test="${mode eq 'link-from'}">
		<c:set var="project" value="${link.linkedEntity.project}"/>
	</c:if>
	<c:if test="${mode eq 'link-to'}">
		<c:set var="project" value="${link.from}"/>
	</c:if>
		<li>
			<a href="${base}/${project.uniqueId}/profile" title="${project.title}">
			<c:choose>
				<c:when test="${not empty project.profile.logo}">
				<img class="thumbnail" src="${base}/logo/download/${project.profile.logo.id}/35x35"/>
				</c:when>
				<c:otherwise>
				<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${project.category.code}.png" width="35" height="35"/>
				</c:otherwise>
			</c:choose>
			</a>
			<div class="clear"></div>
		</li>
	</c:forEach>
	</ul>
	<div class="clear"></div>
	</div>
</div>