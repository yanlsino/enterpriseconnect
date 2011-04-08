<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
	<c:choose>
		<c:when test="${empty forums}">
			当前无话题版块，因此无法发表话题.
		</c:when>
		<c:otherwise>
		<form:form id="topicForm" action="${base}/process/discussion/topic" commandName="topic">
			<fieldset>
				<div>
					<label for="forumId"><fmt:message key="discussion.topic_form.forum"/></label>
					<form:select path="forumId" items="${forums}" itemLabel="name" itemValue="id"/>
				</div>
				<div>
					<label for="subject"><fmt:message key="discussion.topic_form.subject"/></label>
					<form:input path="subject" cssClass="{validate:{required:true, messages:{required:'主题不能为空！'}}}"/>		
				</div>
				<div>
					<label for="content"><fmt:message key="discussion.topic_form.content"/></label>
					<form:textarea path="content" cssClass="{validate:{required:true, messages:{required:'内容不能为空！'}}}"/>
				</div>
				<div>
					<input type="submit" value='<fmt:message key="discussion.topic_form.submit"/>'/>
					<form:hidden path="id"/>
					<form:hidden path="categoryId"/>
					<form:hidden path="forumId"/>
					<form:hidden path="answerId"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<c:if test="${not empty topic.entered}">
					<input type="hidden" name="entered" value='<fmt:formatDate value="${topic.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
					</c:if>
				</div>
			</fieldset>
		</form:form>
		</c:otherwise>
	</c:choose>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	KE.show({
		id : 'content',
	});
	
	$('#topicForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(topic) {
					setTimeout('window.location.href="?topicId='+topic.id+'"', 1000);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>