<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<ul>
	<c:if test="${fragmentConfig.preferences['showHome']}">
		<li>
			<a href="${base}/" title='<fmt:message key="system.main_menu.home"/>'>
				<span><fmt:message key="system.main_menu.home" /></span>
			</a>
		</li>
	</c:if>
	<c:if test="${fragmentConfig.preferences['showMe'] and not empty user}">
		<li>
			<a href="${base}/${user.project.uniqueId}/profile" title='<fmt:message key="system.main_menu.me"/>'>
				<span><fmt:message key="system.main_menu.me" /></span>
			</a>
		</li>
	</c:if>

	<c:forEach var="category" items="${categories}">
	<c:if test="${!category.sensitive or category.sensitive and not empty user}">
		<li>
			<a href="${base}/${category.code}" title='${category.label}"'>
				<span>${category.label}</span>
			</a>
		</li>
	</c:if>
	</c:forEach>
</ul>