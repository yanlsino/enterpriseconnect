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
	<c:choose>
		<c:when test="${empty page.result}">
		<div class="notice">
			<fmt:message key="message.messages_list.noMessageToDisplay"/>
		</div>
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
					<u:security code="message-reply-add">
					<c:if test="${param.box eq 'inbox'}">
					<a href="${base}/${project.uniqueId}/message/${param.box}/form?messageId=${message.id}&toId=${message.from.id}">
					<fmt:message key="message.messages_list.reply"/>
					</a>
					</c:if>
					</u:security>
					<!--<a href="#">删除</a>-->
				</div>
				<div class="content">
					${message.shortContent}
				</div>
			</li>
		</c:forEach>
		</ul>
		<div id="pagination${id}" class="right"></div>
	</c:choose>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#pagination${id}").pagination(${page.totalCount}, {
        items_per_page: ${page.pageSize},
        current_page: ${page.pageNo}-1,
        callback: function(pageNo, container){
            if((pageNo+1)!=${page.pageNo}) {
				document.location.href='?pageNo=' + (pageNo+1);
            }
            return false;
        }
	});
});
</script>