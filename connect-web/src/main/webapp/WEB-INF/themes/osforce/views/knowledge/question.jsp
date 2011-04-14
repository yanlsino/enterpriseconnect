<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<div class="question">
			<div class="title">${question.title}</div>
			<div class="content">${question.content}</div>
			<div class="foot">
			<fmt:formatDate value="${question.entered}" pattern="yyyy/d/m"/>
			|
			<a href="${base}/${question.enteredBy.project.uniqueId}/profile">${question.enteredBy.nickname}</a>
			</div>
		</div>
	</div>
</div>