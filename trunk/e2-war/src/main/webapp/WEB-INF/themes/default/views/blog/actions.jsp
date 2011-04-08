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
		<e2:security code="blog-post-add">
			<li><a href="${base}/${project.uniqueId}/blog/post/form">
				<fmt:message key="blog.actions.newPost"/>
			</a></li>
		</e2:security>
		<e2:security code="blog-category-add">
			<li class="last"><a href="${base}/${project.uniqueId}/blog/category/form">
				<fmt:message key="blog.actions.newCategory"/>
			</a></li>
		</e2:security>
		</ul>
	</div>
</div>