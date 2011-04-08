<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<ul class="comments-list">
	<c:forEach var="comment" items="${comments}" varStatus="status">
	<li>
		<a href="${base}/${comment.enteredBy.project.uniqueId}/profile">
		<c:choose>
			<c:when test="${not empty comment.enteredBy.project.profile.logo}">
				<img class="thumbnail" src="${base}/logo/download/${comment.enteredBy.project.profile.logo.id}/35x35"/>
			</c:when>
			<c:otherwise>
				<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${comment.enteredBy.project.category.code}.png" width="35" height="35"/>
			</c:otherwise>
		</c:choose>
	</a>
	<div class="comment-content">${comment.content}</div>
	<span class="float-right"><e2:prettyTime date="${comment.entered}"/></span>
	<div class="clear"></div>
	</li>
	</c:forEach>
</ul>