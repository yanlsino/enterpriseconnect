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
		<ul class="project-actions">
		<c:if test="${empty member}">
			<li><a class="addMemberAction" href="${base}/process/team/request?projectId=${project.id}">
			<c:choose>
				<c:when test="${project.category.code eq 'people'}"><fmt:message key="profile.actions.addAsFriend"/></c:when>
				<c:otherwise><fmt:message key="profile.actions.addAsMember"/></c:otherwise>
			</c:choose>
			</a></li>
		</c:if>
		<u:entity name="Profile" user="${user}" project="${project}" exist="false">
		<li><a class="concernAction" href="${base}/process/commons/link?fromId=${user.project.id}&toId=${project.profile.id}&entity=Profile">
			<fmt:message key="profile.profiles_list.addConcern"/>
		</a></li>
		</u:entity>
		<c:if test="${not empty user}">
			<li class="last"><a class="leaveMessageAction" href="${base}/app/message/form?popup=true&fromId=${user.project.id}&toId=${project.id}">
			<c:choose>
				<c:when test="${project.category.code eq 'people'}"><fmt:message key="profile.actions.giveMeMessage"/></c:when>
				<c:otherwise><fmt:message key="profile.actions.giveUsMessage"/></c:otherwise>
			</c:choose>
			</a></li>
		</c:if>
		</ul>
	</div>
</div>