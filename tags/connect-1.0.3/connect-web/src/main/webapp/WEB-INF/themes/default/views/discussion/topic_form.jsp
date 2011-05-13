<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<set var="id" value="${fragmentConfig.id}"/>
<set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
	<c:choose>
		<c:when test="${empty forums}">
			<div class="notice">当前无话题版块，因此无法发表话题.</div>
		</c:when>
		<c:otherwise>
		<form:form id="topic-form${id}" action="${base}/process/discussion/topic" 
			commandName="topic" cssClass="topic-form">
				<div>
					<label for="forumId" class="title"><fmt:message key="discussion.topic_form.forum"/></label>
					<br/>
					<form:select path="forumId" items="${forums}" itemLabel="name" itemValue="id"/>
				</div>
				<div>
					<label for="subject" class="title"><fmt:message key="discussion.topic_form.subject"/></label>
					<br/>
					<form:input path="subject" cssClass="title"/>		
				</div>
				<div>
					<label for="content" class="title"><fmt:message key="discussion.topic_form.content"/></label>
					<br/>
					<form:textarea path="content" id="editor${id}" cssClass="text"/>
				</div>
				<div>
					<button type="submit" class="button">
						<fmt:message key="discussion.topic_form.submit"/>
					</button>
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
		</form:form>
		</c:otherwise>
	</c:choose>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#topic-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form){
			var subject = $.trim(formData[1].value);
			var content = $.trim(formData[2].value);
			if(subject=='' || content=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(topic){
			setTimeout(function(){
				window.location.href='?topicId=' + topic.id;				
			}, 500);
		}
	});
	$('#editor${id}').htmlarea(settings.simple);
});
</script>