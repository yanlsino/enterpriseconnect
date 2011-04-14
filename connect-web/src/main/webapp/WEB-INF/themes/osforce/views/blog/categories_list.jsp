<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul>
			<c:forEach var="category" items="${categories}" varStatus="status">
			<li>
				<a href="${base}/${project.uniqueId}/blog/category/${category.id}">${category.label}</a>
				<e2:security code="blog-category-edit">
				(<a href="${base}/${project.uniqueId}/blog/category/form?categoryId=${category.id}">
					<fmt:message key="blog.categories_list.update"/>
				</a>)
				</e2:security>
			</li>	
			</c:forEach>
		</ul>
	</div>
</div>