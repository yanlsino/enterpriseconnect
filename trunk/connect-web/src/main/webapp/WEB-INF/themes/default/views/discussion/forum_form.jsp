<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<set var="id" value="${fragmentConfig.id}"/>
<set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="forum-form${id}" action="${base}/process/discussion/forum" 
			commandName="forum" cssClass="forum-form">
				<div>
					<label for="name" class="title"><fmt:message key="discussion.forum_form.name"/></label>
					<br/>
					<form:input path="name" cssClass="text {validate:{required:true, messages:{required:'版块名不能为空！'}}}"/>
				</div>
				<div>
					<label for="discription" class="title"><fmt:message key="discussion.forum_form.description"/></label>
					<br/>
					<form:textarea path="description"/>
				</div>
				<div>
					<label for="level" class="title"><fmt:message key="discussion.forum_form.level"/></label>
					<br/>
					<form:input path="level" size="2"/>
				</div>
				<div>
					<button type="submit" class="button">
						<fmt:message key="discussion.forum_form.submit"/>
					</button>
					<form:hidden path="id"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="projectId"/>
					<c:if test="${not empty forum.entered}">
					<input type="hidden" name="entered" value='<fmt:formatDate value="${forum.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/> 
					</c:if>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#forum-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form){
			var name = $.trim(formData[0].value);
			if(name=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(forum){
			setTimeout(function(){
				window.location.href='?forumId=' + forum.id;				
			}, 500);
		}
	});
});
</script>