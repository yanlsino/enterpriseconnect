<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<div id="profile">
			<div id="profile-photo">
			<c:choose>
				<c:when test="${not empty profile.logo}">
				<img class="thumbnail" src="${base}/logo/download/${profile.logo.id}/75x75"/>
				</c:when>
				<c:otherwise>
				<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${profile.project.category.code}.png"/>
				</c:otherwise>
			</c:choose>
			</div>
			<div id="profile-basic">
				<h2>${profile.title}</h2>
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
					<e2:security code="profile-profile-edit">
						(<a href="${base}/${project.uniqueId}/profile/form?profileId=${profile.id}"><fmt:message key="profile.profile.edit"/></a>)
					</e2:security>
					</dd>
					<c:if test="${not empty profile.description}">
					<dd>
					<e2:wikiRender text="${profile.description}"/>
					<dd>
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
</div>