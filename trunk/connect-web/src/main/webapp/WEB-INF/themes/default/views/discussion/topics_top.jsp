<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<ul class="topics-list">
		<c:forEach var="statistic" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<span class="title">
					<a href="${base}/${statistic.linkedEntity.forum.project.uniqueId}/discussion/topic/${statistic.linkedEntity.id}">
						${statistic.linkedEntity.subject}
					</a>
				</span>
				<span class="author">
					<fmt:message key="discussion.topics_top.author"/> <a href="${base}/${statistic.linkedEntity.enteredBy.project.uniqueId}/profile">
						${statistic.linkedEntity.enteredBy.nickname}
					</a>
				</span>
			</li>
		</c:forEach>
		</ul>
	</div>
</div>