<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<c:if test="${fn:length(comments) gt 0}">
		<ul class="comments-list">
		<c:forEach var="comment" items="${comments}" varStatus="status">
			<li>
				<span class="span-2">
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
				</span>
				<span class="span6">
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
				</span>
				<br class="clear"/>
			</li>
		</c:forEach>
		</ul>
		</c:if>
		<form:form id="comment-form${id}" class="comment-form" 
			action="${base}/process/commons/comment" commandName="comment">
		<c:choose>
			<c:when test="${empty user}">添加评论，请先<a href="#" class="loginAction">登录</a></c:when>
			<c:otherwise>
			<div>
				<label><fmt:message key="commons.comments_list.comment"/></label>
				<br/>
				<form:textarea path="content" cssClass="text"/>
			</div>
			<div>
				<button type="submit" class="button">
					<span id="status1${id}">
						<fmt:message key="commons.comments_list.submit"/>
					</span>
					<span id="status2${id}" style="display: none">
						<img src="${base}/static/images/loading.gif"/>正在处理...
					</span>
				</button>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="linkedId"/>
				<form:hidden path="entity"/>
				<input type="hidden" name="uniqueId" value="${project.uniqueId}"/>
			</div>
			</c:otherwise>
		</c:choose>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var commentForm = Y.one('#comment-form${id}');
	commentForm.on('submit', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
		//
		Y.on('io:complete', function(id, o){
			try {
				var comment = Y.JSON.parse(o.responseText);
				setTimeout('window.location.reload()', 500);
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(commentForm.get('action'), {
			method: 'POST',
			form: {
				id: commentForm
			}
		});
		e.halt();
	});
});

(function(){
	$('.commentForm').submit(function(){
		var content = $(this).find('#content').val();
		if($.trim(content)!='') {
			$(this).ajaxSubmit({
				dataType:'json',
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