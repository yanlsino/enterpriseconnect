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
			<br class="clear"/>
			<div id="pagination${id}" class="right"></div>
			</c:otherwise>
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