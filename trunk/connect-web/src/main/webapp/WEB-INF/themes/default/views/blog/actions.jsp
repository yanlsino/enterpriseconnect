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
		<ul class="actions">
		<u:security code="blog-post-add" project="${project}" userRequired="true">
			<li><a href="${base}/${project.uniqueId}/blog/post/form">
				<fmt:message key="blog.actions.newPost"/>
			</a></li>
		</u:security>
		<u:security code="blog-category-add" project="${project}" userRequired="true">
			<li class="last"><a href="${base}/${project.uniqueId}/blog/category/form">
				<fmt:message key="blog.actions.newCategory"/>
			</a></li>
		</u:security>
		</ul>
	</div>
</div>