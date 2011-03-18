<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="posts-recent">
			<c:forEach var="post" items="${page.result}" varStatus="status">
				<li <c:if test="${status.last}">class="last"</c:if>>
				<span class="views-counter"><fmt:message key="blog.posts_recent.postViews"><fmt:param value="${post.views}"/></fmt:message></span>
				<span class="title">
					<a href="${base}/${post.enteredBy.project.uniqueId}/blog/post/${post.id}">${post.title}</a>
				</span>
				<span class="author">
					<span><fmt:message key="blog.posts_recent.author"/></span> 
					<a href="${base}/${post.enteredBy.project.uniqueId}/profile">${post.enteredBy.nickname}</a>
					<span><fmt:message key="blog.posts_recent.postAt"/></span><fmt:formatDate value="${post.entered}" pattern="yyyy-MM-dd HH:mm"/>
				</span>
			</li>
			</c:forEach>
		</ul>
	</div>
</div>