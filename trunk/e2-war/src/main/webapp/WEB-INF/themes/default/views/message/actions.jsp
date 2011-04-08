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
		<ul class="actions">
		<e2:security code="message-message-view">
			<li><a href="${base}/${project.uniqueId}/message/inbox">
				<fmt:message key="message.actions.inbox"/>
			</a></li>
			<li class="last"><a href="${base}/${project.uniqueId}/message/sentbox">
				<fmt:message key="message.actions.sentbox"/>
			</a></li>
		</e2:security>
		</ul>
	</div>
</div>