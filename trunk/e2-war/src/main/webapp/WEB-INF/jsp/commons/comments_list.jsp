<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<c:if test="${fn:length(comments) gt 0}">
		<ul class="comments-list">
		<c:forEach var="comment" items="${comments}" varStatus="status">
			<li>
				<div class="author">
					<a href="${base}/profile/${comment.enteredBy.project.uniqueId}" title="${comment.enteredBy.project.title}">
					<c:choose>
						<c:when test="${not empty comment.enteredBy.project.profile.logo}">
						<img class="thumbnail" src="${base}/logo/download/${comment.enteredBy.project.profile.logo.id}/40x40"/>
						</c:when>
						<c:otherwise>
						<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${comment.enteredBy.project.category.code}.png"/>
						</c:otherwise>
					</c:choose>
					</a>
				</div>
				<div class="content">
					<div>
						<span><a href="${base}/profile/${comment.enteredBy.project.uniqueId}" title="${comment.enteredBy.project.title}">
						${comment.enteredBy.project.title}
						</a></span>
						<span>
							<fmt:message key="commons.comments_list.commentAt"/>
							<fmt:formatDate value="${comment.entered}" pattern="yyyy-MM-dd HH:mm"/>
						</span>
					</div>
					${comment.content}
				</div>
			</li>
		</c:forEach>
		</ul>
		</c:if>
		<c:if test="${not empty user}">
		<form:form class="commentForm" action="${base}/process/commons/comment" commandName="comment">
			<div>
				<label><fmt:message key="commons.comments_list.comment"/></label>
				<form:textarea path="content" cssClass="required"/>
			</div>
			<div>
				<input class="button" type="submit" value='<fmt:message key="commons.comments_list.submit"/>'/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="linkedId"/>
				<form:hidden path="entity"/>
				<input type="hidden" name="uniqueId" value="${project.uniqueId}"/>
			</div>
		</form:form>
		</c:if>
	</div>
</div>

<script type="text/javascript">
(function(){
	$('.commentForm').submit(function(){
		var content = $(this).find('#content').val();
		if($.trim(content)!='') {
			$(this).ajaxSubmit({
				success:function(comment){
					window.location.reload();
				}
			});	
		} else {
			$.prompt('评论内容不能为空！');			
		}
		return false;
	});
})();
</script>