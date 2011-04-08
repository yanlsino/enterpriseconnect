<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
	<c:choose>
		<c:when test="${empty page.result}">
		当前无答案可显示！		
		</c:when>
		<c:otherwise>
		<ul class="answers-list">
		<c:forEach var="answer" items="${page.result}" varStatus="status">
			<li>
				<div class="answer">
					<div class="content">${answer.content}</div>
					<div class="foot">
					<fmt:formatDate value="${answer.entered}" pattern="yyyy/d/m"/>
					|
					<a href="${base}/${answer.enteredBy.project.uniqueId}/profile">${answer.enteredBy.nickname}</a>
					</div>
				</div>
			</li>
		</c:forEach>
		</ul>
		</c:otherwise>	
	</c:choose>
	</div>
</div>