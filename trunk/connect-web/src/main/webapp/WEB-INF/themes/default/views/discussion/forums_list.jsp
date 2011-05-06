<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
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
		<c:when test="${fn:length(forums) eq 0}">
			<div class="notice"><fmt:message key="discussion.forums_list.noForumToDisplay"/></div>
		</c:when>
		<c:otherwise>
		<ul class="forums-list">
			<c:forEach var="forum" items="${forums}" varStatus="status">
			<li class="forum">
				<div class="forum-head">
					<span class="top right">
						<u:security code="discussion-topic-add">
							<a href="${base}/${project.uniqueId}/discussion/topic/form?forumId=${forum.id}"><fmt:message key="discussion.forums_list.newTopic"/></a>
						</u:security>
						<u:security code="discussion-forum-edit">
							<a href="${base}/${project.uniqueId}/discussion/forum/form?forumId=${forum.id}"><fmt:message key="discussion.forums_list.update"/></a>
						</u:security>
					</span>
					<h3><a href="${base}/${project.uniqueId}/discussion/forum/${forum.id}">${forum.name}</a></h3>
				</div>
				<div class="forum-body">
					<ul class="topics-list">
					<c:forEach var="topic" items="${forum.topics}" varStatus="status">
						<li <c:if test="${status.last}">class="last"</c:if>>
							<span class="top right"><fmt:message key="discussion.forums_list.topicViews"><fmt:param value="${topic.views}"/></fmt:message></span>
							<a href="${base}/${forum.project.uniqueId}/discussion/topic/${topic.id}">
								${topic.subject}
							</a>
							<fmt:message key="discussion.forums_list.author"/> 
							<a href="${base}/${topic.enteredBy.project.uniqueId}/profile">
								${topic.enteredBy.nickname}
							</a>
							<fmt:message key="discussion.forums_list.postAt"/> 
							<fmt:formatDate value="${topic.entered}" pattern="yyyy-MM-dd HH:mm"/>
						</li>
					</c:forEach>
					</ul>
				</div>
			</li>	
			</c:forEach>
		</ul>
		</c:otherwise>
	</c:choose>
	</div>
</div>