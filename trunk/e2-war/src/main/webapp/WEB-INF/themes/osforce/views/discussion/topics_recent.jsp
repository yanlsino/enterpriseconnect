<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="topics-list">
		<c:forEach var="topic" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<span class="views-counter"><fmt:message key="discussion.topics_recent.topicViews"><fmt:param value="${topic.views}"/></fmt:message></span>
				<span class="title">
					<a href="${base}/${topic.forum.project.uniqueId}/discussion/topic/${topic.id}">
						${topic.subject}
					</a>
				</span>
				<span class="author">
					<fmt:message key="discussion.topics_recent.author"/> <a href="${base}/${topic.enteredBy.project.uniqueId}/profile">
						${topic.enteredBy.nickname}
						</a>
					<fmt:message key="discussion.topics_recent.postAt"/> <fmt:formatDate value="${topic.entered}" pattern="yyyy-MM-dd HH:mm"/>
				</span>
			</li>
		</c:forEach>
		</ul>
	</div>
</div>