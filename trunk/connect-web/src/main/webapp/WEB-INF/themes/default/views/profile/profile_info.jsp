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
	<ul>
	<c:choose>
		<c:when test="${project.category.code eq 'people'}">
			<li>
				<span class="label"><fmt:message key="profile.profile_info.registerDate"/></span>
				<fmt:formatDate value="${project.entered}" pattern="yyyy-MM-dd HH:mm"/>	
			</li>
			<li>
				<span class="label"><fmt:message key="profile.profile_info.lastLogin"/></span>
				<fmt:formatDate value="${project.enteredBy.lastLogin}" pattern="yyyy-MM-dd HH:mm"/>	
			</li>		
		</c:when>
		<c:otherwise>
			<li>
				<span class="label"><fmt:message key="profile.profile_info.enteredDate"/></span>
				<fmt:formatDate value="${project.entered}" pattern="yyyy-MM-dd HH:mm"/>	
			</li>
			<li>
				<span class="label"><fmt:message key="profile.profile_info.modifiedDate"/></span>
				<fmt:formatDate value="${project.modified}" pattern="yyyy-MM-dd HH:mm"/>	
			</li>
		</c:otherwise>
	</c:choose>
		</ul>
	</div>
</div>