<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<div class="topic">
			<div class="topic-author">
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
						<fmt:message key="discussion.replies_list.registerDate"/><fmt:formatDate value="${topic.enteredBy.entered}" pattern="yyyy-MM-dd"/>
					</span>
				</div>
			</div>
			<div class="topic-head">
				<span class="topic-date"><fmt:message key="discussion.replies_list.postAt"/> <fmt:formatDate value="${topic.entered}" pattern="yyyy-MM-dd HH:mm:ss"/></span>				
			</div>
			<div class="topic-body">
				${topic.content}
			</div>
			<div class="topic-foot">
				<div class="float-right">
					<!-- 
					<a class="quoteAction" href="#reply"><fmt:message key="discussion.replies_list.quote"/></a>
					-->
				</div>
			</div>
			<div class="clear"></div>
		</div>
		
		<ul class="replies-list">
		<c:forEach var="reply" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last and page.totalPages eq 1}">class="last"</c:if>>
				<div class="reply">
					<div class="reply-author">
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
							<span><fmt:message key="discussion.replies_list.registerDate"/><fmt:formatDate value="${reply.enteredBy.entered}" pattern="yyyy-MM-dd"/></span>
						</div>
					</div>
					<div class="reply-head">
						<span class="topic-date"><fmt:message key="discussion.replies_list.postAt"/><fmt:formatDate value="${reply.entered}" pattern="yyyy-MM-dd HH:mm:ss"/></span>				
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
						<span class="float-right">
							<a id="${reply.id}" class="quoteAction" href="#reply"><fmt:message key="discussion.replies_list.quote"/></a>
						</span>
					</div>
					<div class="clear"></div>
				</div>
			</li>
		</c:forEach>
		</ul>
		<c:if test="${page.totalPages > 1}">
		<e2:pagination link="?pageNo=" page="${page}"/>
		</c:if>
	</div>
</div>

<script type="text/javascript">
(function(){
	$('.quoteAction').click(function(){
		var quoteId = $(this).attr('id');
		$('#quoteId').val(quoteId);
	});
})();
</script>