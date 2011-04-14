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
			<div id="profile-basic">
				<c:choose>
					<c:when test="${not empty profile.logo}">
					<img class="top right thumbnail" src="${base}/logo/download/${profile.logo.id}/75x75"/>
					</c:when>
					<c:otherwise>
					<img class="top right thumbnail" src="${base}/themes/${theme.name}/stock/${profile.project.category.code}.png"/>
					</c:otherwise>
				</c:choose>
				<h3>${profile.title}</h3>
				<dl>
					<dd>
					<c:choose>
						<c:when test="${empty profile.shortDescription}">
							<fmt:message key="profile.profile.shortDescription"/>
						</c:when>
						<c:otherwise>
							${profile.shortDescription}
						</c:otherwise>
					</c:choose>
					<u:security code="profile-profile-edit" project="${profile.project}">
						(<a href="${base}/${project.uniqueId}/profile/form?profileId=${profile.id}"><fmt:message key="profile.profile.edit"/></a>)
					</u:security>
					</dd>
					<c:if test="${not empty profile.description}">
					<dd>${profile.description}<dd>
					</c:if>
				</dl>
			</div>
			<c:if test="${not empty customAttributes}">
			<div id="profile-attributes">
				<dl>
					<c:forEach var="customAttribute" items="${customAttributes}" varStatus="status">
					<dd <c:if test="${status.last}">class="last"</c:if>>
					<c:choose>
						<c:when test="${customAttribute.type eq 'link'}">
						<span class="title"><fmt:message key="profile.profile.${customAttribute.name}"/></span>
							<a href="${customAttribute.value}" title="<fmt:message key="profile.profile.${customAttribute.name}"/>" target="_blank">
								${customAttribute.value}
							</a>
						</c:when>
						<c:otherwise>
							<span class="title"><fmt:message key="profile.profile.${customAttribute.name}"/></span>
							${customAttribute.value}
						</c:otherwise>
					</c:choose>
					</dd>
					</c:forEach>
				</dl>
			</div>
			</c:if>
	</div>
</div>