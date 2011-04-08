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
	<c:choose>
		<c:when test="${empty page.result}">
		<div class="notice">当前无答案可显示！</div>	
		</c:when>
		<c:otherwise>
		<ul class="answers-list">
		<c:forEach var="answer" items="${page.result}" varStatus="status">
			<li>
				<p>${answer.content}</p>
				<p>
					<fmt:formatDate value="${answer.entered}" pattern="yyyy/d/m"/>
					|
					<a href="${base}/${answer.enteredBy.project.uniqueId}/profile">${answer.enteredBy.nickname}</a>
				</p>
			</li>
		</c:forEach>
		</ul>
		</c:otherwise>	
	</c:choose>
	</div>
</div>