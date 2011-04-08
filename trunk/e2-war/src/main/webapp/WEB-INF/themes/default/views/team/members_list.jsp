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
	<c:choose>
		<c:when test="${empty page.result and empty needApprove and empty waitApprove}">
			<c:choose>
				<c:when test="${project.category.code eq 'people'}"><fmt:message key="team.members_list.noFriendToDisplay"/></c:when>
				<c:otherwise><fmt:message key="team.members_list.noMemberToDisplay"/></c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
		<c:if test="${not empty needApprove}">
		<div><fmt:message key="team.members_list.needApprove"/></div>
		<ul>
		<c:forEach var="member" items="${needApprove}">
			<li>
				<a href="${base}/${member.user.project.uniqueId}/profile">${member.user.nickname}</a>
			</li>
		</c:forEach>
		</ul>
		</c:if>
		<c:choose>
			<c:when test="${empty page.result}">
			<div class="notice">当前无内容可显示！</div>
			</c:when>
			<c:otherwise>
			<ul class="members-list">
			<c:forEach var="member" items="${page.result}" varStatus="status">
				<li <c:if test="status.last">class="last"</c:if>>
					<a href="${base}/${member.user.project.uniqueId}/profile">
					<c:choose>
						<c:when test="${not empty member.user.project.profile.logo}">
						<img class="thumbnail" src="${base}/logo/download/${member.user.project.profile.logo.id}/50x50"/>
						</c:when>
						<c:otherwise>
						<img class="thumbnail"src="${base}/themes/${theme.name}/stock/${member.user.project.category.code}.png" with="50" height="50"/>
						</c:otherwise>
					</c:choose>
					</a>
					<div class="desc">
						<a target="_blank" href="${base}/${member.user.project.uniqueId}/profile">
							${member.user.project.title}
						</a>
						<div><fmt:message key="team.members_list.registerDate"/><fmt:formatDate value="${member.user.entered}" pattern="yyyy-MM-dd"/></div>
						<div><fmt:message key="team.members_list.lastLogin"/><fmt:formatDate value="${member.user.lastLogin}" pattern="yyyy-MM-dd HH:mm"/></div>
						<div class="clear"></div>
					</div>
				</li>
			</c:forEach>
			</ul>
			<c:if test="${page.totalPages > 1}">
			<e2:pagination link="?pageNo=" page="${page}"/>
			</c:if>
				</c:otherwise>
			</c:choose>
			</c:otherwise> 
		</c:choose>
		<br class="clear"/>
	</div>
</div>