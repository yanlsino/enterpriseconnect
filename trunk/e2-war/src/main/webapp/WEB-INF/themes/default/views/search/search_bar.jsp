<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="search-bar">
	<ul>
		<li <c:if test="${empty param.categoryId}">class="current first"</c:if>>
			<a href="${base}/search?keywords=${searchBean.keywords}">
				<span><fmt:message key="search.search_bar.allCategories"><fmt:param value="${totalCount}"/></fmt:message></span>
			</a>
		</li>
		<c:forEach var="category" items="${categories}" varStatus="status">
		<li <c:if test="${param.categoryId eq category.id}">class="current"</c:if>>
			<a href="${base}/search?keywords=${searchBean.keywords}&categoryId=${category.id}">
				<span>${category.label}(${category.count})</span>
			</a> 
		</li>		
		</c:forEach>
	</ul>
	<div class="clear"></div>
</div>