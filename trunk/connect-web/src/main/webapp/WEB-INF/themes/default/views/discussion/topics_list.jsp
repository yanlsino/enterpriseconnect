<%@page pageEncoding="UTF-8" %>
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
		<div class="notice">当前无话题可显示！</div>
		</c:when>
		<c:otherwise>
		<ul class="topics-list">
		<c:forEach var="topic" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<span class="top right"><fmt:message key="discussion.topics_list.topicViews"><fmt:param value="${topic.views}"/></fmt:message></span>
				<a href="${base}/${topic.forum.project.uniqueId}/discussion/topic/${topic.id}">${topic.subject}</a>
				<fmt:message key="discussion.topics_list.author"/>
				<a href="${base}/${topic.enteredBy.project.uniqueId}/profile">${topic.enteredBy.nickname}</a>
				<fmt:message key="discussion.topics_list.postAt"/>
				<fmt:formatDate value="${topic.entered}" pattern="yyyy-MM-dd HH:mm"/>
			</li>
		</c:forEach>
		</ul>
		<br/>
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