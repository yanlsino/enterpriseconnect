<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<ul class="projects-list">
		<c:forEach var="project" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<a href="${base}/${project.uniqueId}/profile">
					<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${project.category.code}.png" with="40" height="40"/>
				</a>
				<div class="profile-body">
					<div class="profile-content">
						<ul class="float-right">
							<u:entity name="Link" user="${user}" project="${project}" exist="false">
							<li><a class="concernAction" href="${base}/process/commons/link?fromId=${user.project.id}&toId=${project.id}&entity=Profile">添加关注</a></li>
							</u:entity>
							<u:security code="message-message-add" userRequired="true">
							<li><a id="${project.uniqueId}" class="leaveMessageAction" href="${base}/app/message/form?popup=true&fromId=${user.project.id}&toId=${project.id}">发送消息</a></li>
							</u:security>
						</ul>
						<a href="${base}/${project.uniqueId}/profile">${project.title}</a>
						<p>${project.profile.shortDescription}</p>
					</div>
				</div>
				<div class="clear"></div>
			</li>
		</c:forEach>
		</ul>
		<c:if test="${page.totalPages > 1}">
		<u:pagination link="?pageNo=" page="${page}"/>
		</c:if>
	</div>
</div>