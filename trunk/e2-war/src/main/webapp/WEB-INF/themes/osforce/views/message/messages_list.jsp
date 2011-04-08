<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
	<c:choose>
		<c:when test="${empty page.result}">
			当前无留言可显示!
			<fmt:message key="message.messages_list.noMessageToDisplay"/>
		</c:when>
		<c:otherwise>
		<ul class="messages-list">
		<c:forEach var="message" items="${page.result}" varStatus="status">
			<li>
				<c:choose>
					<c:when test="${not empty message.enteredBy.project.profile.logo}">
					<img class="thumbnail" src="${base}/logo/download/${message.enteredBy.project.profile.logo.id}/40x40"/>
					</c:when>
					<c:otherwise>
					<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${message.enteredBy.project.category.code}.png" with="40" height="40"/>
					</c:otherwise>
				</c:choose>
				<div class="subject">
					<a href="${base}/${project.uniqueId}/message/${param.box}/${message.id}">${message.subject}</a>
					<%--
					<span>接收于${message.prettyEntered} (${message.entered?string('yyyy-MM-dd HH:mm')})</span>
					 --%>
					<e2:security code="message-reply-add">
					<c:if test="${param.box eq 'inbox'}">
					<a href="${base}/${project.uniqueId}/message/${param.box}/form?messageId=${message.id}&toId=${message.from.id}">
					<fmt:message key="message.messages_list.reply"/>
					</a>
					</c:if>
					</e2:security>
					<!--<a href="#">删除</a>-->
				</div>
				<div class="content">
					${message.shortContent}
				</div>
			</li>
		</c:forEach>
		</ul>
		<c:if test="${page.totalPages > 1}">
		<e2:pagination link="?pageNo=" page="${page}"/>
		</c:if>
		</c:otherwise>
	</c:choose>
	</div>
</div>