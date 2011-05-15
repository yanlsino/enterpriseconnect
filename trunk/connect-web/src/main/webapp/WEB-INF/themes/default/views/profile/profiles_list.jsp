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
		<div class="notice">当前无内容可显示！</div>
		</c:when>
		<c:otherwise>
		<ul class="profiles-list">
		<c:forEach var="profile" items="${page.result}" varStatus="status">
			<li class="profile <c:if test="${status.last}">last</c:if>">
				<a href="${base}/${profile.project.uniqueId}/profile">
				<c:choose>
					<c:when test="${not empty profile.logo}">
					<img class="top left thumbnail" src="${base}/logo/download/${profile.logo.id}/40x40"/>
					</c:when>
					<c:otherwise>
					<img class="top left thumbnail" src="${base}/themes/${theme.name}/stock/${profile.project.category.code}.png" with="40" height="40"/>
					</c:otherwise>
				</c:choose>
				</a>
				<div class="profile-body">
					<div class="profile-content">
						<ul class="top right">
							<u:entity name="Profile" user="${user}" project="${profile.project}" exist="false">
							<li><a class="concernAction" href="${base}/process/commons/link?fromId=${user.project.id}&toId=${profile.id}&entity=Profile">
								<fmt:message key="profile.profiles_list.addConcern"/>
							</a></li>
							</u:entity>
							<u:security code="message-message-add" userRequired="true">
							<li><a id="${profile.project.uniqueId}" class="leaveMessageAction" href="${base}/app/message/form?popup=true&fromId=${user.project.id}&toId=${profile.project.id}"">
								<fmt:message key="profile.profiles_list.sendMessage"/>
							</a></li>
							</u:security>
						</ul>
						<a href="${base}/${profile.project.uniqueId}/profile">${profile.title}</a>
						<p>${profile.shortDescription}</p>
					</div>
				</div>
				<div class="clear"></div>
			</li>
		</c:forEach>
		</ul>
		<br/>
		<div id="pagination${id}" class="right"></div>
		<br/>
		</c:otherwise>
	</c:choose>
	</div>
</div>

<c:if test="${page.totalPages gt 1}">
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
</c:if>