<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<a name="reply"></a>
		<form:form id="replyForm" action="${base}/process/discussion/reply" commandName="reply">
			<div class="reply-author">
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
					<span><fmt:message key="discussion.reply_form.registerDate"/><fmt:formatDate value="${user.entered}" pattern="yyyy-MM-dd"/></span>
				</div>
			</div>
			<fieldset>
				<div>
					<label><fmt:message key="discussion.reply_form.subject"/>${topic.subject}</label>
					<form:input path="subject" value="RE:${reply.topic.subject}"/>
				</div>
				<div>
					<label><fmt:message key="discussion.reply_form.content"/></label>
					<form:textarea path="content" cssClass="{validate:{required:true, messages:{required:'回复内容不能为空！'}}}"/>
				</div>
				<div>
					<input class="button" type="submit" value='<fmt:message key="discussion.reply_form.submit"/>'>
					<form:hidden path="id"/>
					<form:hidden path="topicId"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="quoteId"/>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#replyForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				clearForm:true,
				success:function(reply){
					setTimeout('window.location.reload()', 500);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>