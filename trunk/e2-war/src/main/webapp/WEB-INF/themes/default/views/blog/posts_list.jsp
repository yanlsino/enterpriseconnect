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
	<c:choose>
		<c:when test="${empty page.result}">
			<fmt:message key="blog.posts_list.noPostToDisplay"/>
		</c:when>
		<c:otherwise>
		<ul class="posts-list">
			<c:forEach var="post" items="${page.result}" varStatus="status">
			<li>
				<h1>
					<a href="${base}/${project.uniqueId}/blog/post/${post.id}">${post.title}</a>
				</h1>
				<div>${post.shortContent}</div>
				<div class="post-footer align-right">
				<e2:security code="blog-post-view">
					<a href="${base}/${project.uniqueId}/blog/post/${post.id}" class="readmore"><fmt:message key="blog.posts_list.readMore"><fmt:param value="${post.views}"/></fmt:message></a>
				</e2:security>
				<e2:security code="blog-post-edit" userRequired="true">
					<a href="${base}/${project.uniqueId}/blog/post/form?postId=${post.id}"><fmt:message key="blog.posts_list.edit"/></a>
				</e2:security>
				<e2:security code="blog-comment-add" userRequired="true">
					<a href="${base}/${project.uniqueId}/blog/post/${post.id}#comments-list" class="comments"><fmt:message key="blog.posts_list.comment"><fmt:param value="${post.commentNumber}"/></fmt:message></a>
				</e2:security>
					<span class="date"><fmt:formatDate value="${post.entered}" pattern="yyyy-MM-dd"/></span>	
				</div>
			</li>
			</c:forEach>
		</ul>		
		</c:otherwise>
	</c:choose>
	</div>
</div>