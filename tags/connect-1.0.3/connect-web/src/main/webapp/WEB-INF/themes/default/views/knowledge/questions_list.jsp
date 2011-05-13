<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
	<c:choose>
		<c:when test="${empty page.result}">
			<div class="notice">当前无问题可显示！</div>
		</c:when>
		<c:otherwise>
		<ul class="questions-list">
		<c:forEach var="question" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<span class="top right">${fn:length(question.answers)}答/${question.views}阅</span>
				<a href="${base}/${project.uniqueId}/knowledge/question/${question.id}">${question.title}</a>
			</li>
		</c:forEach>
		</ul>
		</c:otherwise>
	</c:choose>
	</div>
</div>