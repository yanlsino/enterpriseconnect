<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
	<c:choose>
		<c:when test="${empty page.result}">
			当前无问题可显示！
		</c:when>
		<c:otherwise>
		<ul class="questions-list">
		<c:forEach var="question" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<span class="float-right">${fn:length(question.answers)}答/${question.views}阅</span>
				<a href="${base}/${project.uniqueId}/knowledge/question/${question.id}">${question.title}</a>
			</li>
		</c:forEach>
		</ul>
		</c:otherwise>
	</c:choose>
	</div>
</div>