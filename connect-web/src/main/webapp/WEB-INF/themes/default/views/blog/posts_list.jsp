<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
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
				<div class="post-actions">
				<u:security code="blog-post-view">
					<a href="${base}/${project.uniqueId}/blog/post/${post.id}" class="readmore"><fmt:message key="blog.posts_list.readMore"><fmt:param value="${post.views}"/></fmt:message></a>
				</u:security>
				<u:security code="blog-post-edit" userRequired="true">
					<a href="${base}/${project.uniqueId}/blog/post/form?postId=${post.id}"><fmt:message key="blog.posts_list.edit"/></a>
				</u:security>
				<u:security code="blog-comment-add" userRequired="true">
					<a href="${base}/${project.uniqueId}/blog/post/${post.id}#comments-list" class="comments"><fmt:message key="blog.posts_list.comment"><fmt:param value="${post.commentNumber}"/></fmt:message></a>
				</u:security>
					<span class="date"><fmt:formatDate value="${post.entered}" pattern="yyyy-MM-dd"/></span>
				</div>
			</li>
			</c:forEach>
		</ul>
		<br/>
		<div id="pagination${id}" class="right"></div>
		</c:otherwise>
	</c:choose>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#pagination${id}").pagination(${page.totalCount}, {
        items_per_page: ${page.pageSize},
        current_page: ${page.pageNo}-1,
        callback: function(pageNo, container){
            if((pageNo+1)!=${page.pageNo}) {
				document.location.href='?pageNo=' + (pageNo+1);
            }
            return false;
        }
	});
});
</script>