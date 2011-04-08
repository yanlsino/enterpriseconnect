<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
	<c:choose>
		<c:when test="${project.category.code eq 'people'}">
			<div>
				<span class="label"><fmt:message key="profile.profile_info.registerDate"/></span>
				<fmt:formatDate value="${project.entered}" pattern="yyyy-MM-dd HH:mm"/>	
			</div>
			<div>
				<span class="label"><fmt:message key="profile.profile_info.lastLogin"/></span>
				<fmt:formatDate value="${project.enteredBy.lastLogin}" pattern="yyyy-MM-dd HH:mm"/>	
			</div>		
		</c:when>
		<c:otherwise>
			<div>
				<span class="label"><fmt:message key="profile.profile_info.enteredDate"/></span>
				<fmt:formatDate value="${project.entered}" pattern="yyyy-MM-dd HH:mm"/>	
			</div>
			<div>
				<span class="label"><fmt:message key="profile.profile_info.modifiedDate"/></span>
				<fmt:formatDate value="${project.modified}" pattern="yyyy-MM-dd HH:mm"/>	
			</div>
		</c:otherwise>
	</c:choose>
	</div>
</div>