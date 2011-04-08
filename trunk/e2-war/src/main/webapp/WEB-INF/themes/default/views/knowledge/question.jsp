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
		<div class="question">
			<h4>${question.title}</h4>
			<p>${question.content}</p>
			<p>
			<fmt:formatDate value="${question.entered}" pattern="yyyy/d/m"/>
			|
			<a href="${base}/${question.enteredBy.project.uniqueId}/profile">${question.enteredBy.nickname}</a>
			</p>
		</div>
	</div>
</div>