<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul class="comments-list">
	<c:forEach var="comment" items="${comments}" varStatus="status">
	<li>
		<a href="${base}/${comment.enteredBy.project.uniqueId}/profile">
		<c:choose>
			<c:when test="${not empty comment.enteredBy.project.profile.logo}">
				<img class="top left thumbnail" src="${base}/logo/download/${comment.enteredBy.project.profile.logo.id}/35x35"/>
			</c:when>
			<c:otherwise>
				<img class="top left thumbnail" src="${base}/themes/${theme.name}/stock/${comment.enteredBy.project.category.code}.png" width="35" height="35"/>
			</c:otherwise>
		</c:choose>
	</a>
	<p class="comment-content">${comment.content}</p>
	<span class="top right"><u:prettyTime date="${comment.entered}"/></span>
	<div class="clear"></div>
	</li>
	</c:forEach>
</ul>