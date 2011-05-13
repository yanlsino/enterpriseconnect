<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<a name="reply"></a>
		<div class="span-3 author">
				<a href="${base}/${user.project.uniqueId}/profile">
				<c:choose>
					<c:when test="${not empty user.project.profile.logo}">
					<img class="thumbnail" src="${base}/logo/download/${user.project.profile.logo.id}/75x75"/>
					</c:when>
					<c:otherwise>
					<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${user.project.category.code}.png"/>
					</c:otherwise>
				</c:choose>
				</a>
				<div>
					<a href="${base}/${user.project.uniqueId}/profile">${user.project.title}</a>
				</div>
				<div>
					<span><fmt:message key="discussion.reply_form.registerDate"/><fmt:formatDate value="${user.entered}" pattern="yyyy/M/d"/></span>
				</div>
		</div>
		<form:form id="reply-form${id}" action="${base}/process/discussion/reply" 
			commandName="reply" cssClass="reply-form">
				<div>
					<label for="subject" class="title"><fmt:message key="discussion.reply_form.subject"/>${topic.subject}</label>
					<br/>
					<form:input path="subject" cssClass="title {validate:{required:true, messages:{required:'回复主题不能为空！'}}}" value="RE:${reply.topic.subject}"/>
				</div>
				<div>
					<label><fmt:message key="discussion.reply_form.content"/></label>
					<br/>
					<form:textarea path="content" cssClass="{validate:{required:true, messages:{required:'回复内容不能为空！'}}}"/>
				</div>
				<div>
					<button type="submit" class="button">
						<span id="status1${id}">
							<fmt:message key="discussion.reply_form.submit"/>
						</span>
						<span id="status2${id}" style="display: none">
							<img src="${base}/static/images/loading.gif"/>正在处理...
						</span>
					</button>
					<form:hidden path="id"/>
					<form:hidden path="topicId"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="quoteId"/>
				</div>
		</form:form>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#reply-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form){
			var subject = $.trim(formData[0].value);
			var content = $.trim(formData[1].value);
			if(subject=='' || content=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(reply){
			setTimeout(function(){
				window.location.reload();
			}, 500);
		}
	});
});
</script>