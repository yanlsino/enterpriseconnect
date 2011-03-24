<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="posts-recent">
			<c:forEach var="statistic" items="${page.result}" varStatus="status">
				<li <c:if test="${status.last}">class="last"</c:if>>
				<span class="title">
					<a href="${base}/${statistic.linkedEntity.project.uniqueId}/blog/post/${statistic.linkedEntity.id}">${statistic.linkedEntity.title}</a>
				</span>
				<span class="author">
					<span><fmt:message key="blog.posts_recent.author"/><span> 
					<a href="${base}/${statistic.linkedEntity.project.uniqueId}/profile">${statistic.linkedEntity.enteredBy.nickname}</a>
					<span><fmt:message key="blog.posts_recent.postAt"/><span><fmt:formatDate value="${statistic.linkedEntity.entered}" pattern="yyyy-MM-dd HH:mm"/>
				</span>
			</li>
			</c:forEach>
		</ul>
	</div>
</div>