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
		<div class="topic">
			<div class="span-3 author">
				<a href="${base}/${topic.enteredBy.project.uniqueId}/profile">
				<c:choose>
					<c:when test="${not empty topic.enteredBy.project.profile.logo}">
					<img class="thumbnail" src="${base}/logo/download/${topic.enteredBy.project.profile.logo.id}/75x75"/>
					</c:when>
					<c:otherwise>
					<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${topic.enteredBy.project.category.code}.png"/>
					</c:otherwise>
				</c:choose>
				</a>
				<div>
					<a href="${base}/${topic.enteredBy.project.uniqueId}/profile">
					${topic.enteredBy.nickname}
					</a>
				</div>
				<div>
					<span>
						<fmt:message key="discussion.replies_list.registerDate"/><fmt:formatDate value="${topic.enteredBy.entered}" pattern="yyyy/M/d"/>
					</span>
				</div>
			</div>
			<div class="topic-head">
				<span class="top right"><fmt:message key="discussion.replies_list.postAt"/> <fmt:formatDate value="${topic.entered}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
				<h4>${topic.subject}</h4>
			</div>
			<div class="topic-body">
				${topic.content}
			</div>
			<br class="clear"/>
		</div>
		<c:if test="${not empty page.result}">
		<ul class="replies-list">
		<c:forEach var="reply" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last and page.totalPages eq 1}">class="last"</c:if>>
				<div class="reply">
					<div class="span-3 author">
						<a href="${base}/${reply.enteredBy.project.uniqueId}/profile">
						<c:choose>
							<c:when test="${not empty reply.enteredBy.project.profile.logo}">
							<img class="thumbnail" src="${base}/logo/download/${reply.enteredBy.project.profile.logo.id}/75x75"/>
							</c:when>
							<c:otherwise>
							<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${reply.enteredBy.project.category.code}.png"/>
							</c:otherwise>
						</c:choose>
						</a>
						<div>
							<a href="#">${reply.enteredBy.nickname}</a>
						</div>
						<div>
							<span><fmt:message key="discussion.replies_list.registerDate"/><fmt:formatDate value="${reply.enteredBy.entered}" pattern="yyyy/M/d"/></span>
						</div>
					</div>
					<div class="reply-head">
						<span class="top right"><fmt:message key="discussion.replies_list.postAt"/><fmt:formatDate value="${reply.entered}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						<h4>${reply.subject}</h4>
					</div>
					<div class="reply-body">
						<c:if test="${not empty reply.quote}">
						<div class="quote">
						<div class="quote-head">${reply.quote.subject}</div>
						<div class="quote-body">${reply.quote.content}</div>
						</div>
						</c:if>
						${reply.content}
					</div>
					<div class="reply-foot">
						<span class="top right">
							<a id="${reply.id}" class="quoteAction" href="#reply"><fmt:message key="discussion.replies_list.quote"/></a>
						</span>
					</div>
					<div class="clear"></div>
				</div>
			</li>
		</c:forEach>
		</ul>
		<br/>
		<div id="pagination${id}" class="right"></div>
		<br/>
		</c:if>
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

<script type="text/javascript">
$(document).ready(function(){
	$('.quoteAction').click(function(){
		var quoteId = $(this).attr('id');
		$('#quoteId').val(quoteId);
	});
});
</script>