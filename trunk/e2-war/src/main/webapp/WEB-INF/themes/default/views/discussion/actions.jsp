<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<ul class="actions">
		<e2:security code="discussion-forum-add">
			<li><a href="${base}/${project.uniqueId}/discussion/forum/form">
				<fmt:message key="discussion.actions.newForum"/>		
			</a></li>
		</e2:security>
		<e2:security code="discussion-topic-add">
			<li class="last"> <a href="${base}/${project.uniqueId}/discussion/topic/form">
				<fmt:message key="discussion.actions.newTopic"/>
			</a></li>
		</e2:security>
		</ul>
	</div>
</div>