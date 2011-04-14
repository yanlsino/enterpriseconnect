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
		<c:when test="${fn:length(forums) eq 0}">
			<fmt:message key="discussion.forums_list.noForumToDisplay"/>
		</c:when>
		<c:otherwise>
		<ul class="forums-list">
			<c:forEach var="forum" items="${forums}" varStatus="status">
			<li>
				<div class="forum-head">
					<h3>
						<span class="float-right">
							<e2:security code="discussion-topic-add">
							<a href="${base}/${project.uniqueId}/discussion/topic/form?forumId=${forum.id}"><fmt:message key="discussion.forums_list.newTopic"/></a>
							</e2:security>
							<e2:security code="discussion-forum-edit">
							<a class="button" href="${base}/${project.uniqueId}/discussion/forum/form?forumId=${forum.id}"><fmt:message key="discussion.forums_list.update"/></a>
							</e2:security>
						</span>
						<a href="${base}/${project.uniqueId}/discussion/forum/${forum.id}">${forum.name}</a>
					</h3>
					<div class="clear"></div>
				</div>
				<div class="forum-body">
					<ul class="topics-list">
					<c:forEach var="topic" items="${forum.topics}" varStatus="status">
						<li <c:if test="${status.last}">class="last"</c:if>>
							<span class="views-counter"><fmt:message key="discussion.forums_list.topicViews"><fmt:param value="${topic.views}"/></fmt:message></span>
							<span class="title">
								<a href="${base}/${forum.project.uniqueId}/discussion/topic/${topic.id}">
									${topic.subject}
								</a>
							</span>
							<span class="author">
								<fmt:message key="discussion.forums_list.author"/> <a href="${base}/${topic.enteredBy.project.uniqueId}/profile">
									${topic.enteredBy.nickname}
									</a>
								<fmt:message key="discussion.forums_list.postAt"/> <fmt:formatDate value="${topic.entered}" pattern="yyyy-MM-dd HH:mm"/>
							</span>
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