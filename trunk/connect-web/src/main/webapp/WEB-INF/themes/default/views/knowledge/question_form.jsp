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
		<form:form id="question-form${id}" cssClass="question-form"
			action="${base}/process/knowledge/question" commandName="question">
			<div class="formmgr-row">
				<label for="title" class="title">标题 <span class="required">*</span></label>
				<br/>
				<form:input path="title" id="title${id}" cssClass="title"/>
			</div>
			<div class="formmgr-row">
				<label for="content" class="title">内容 <span class="required">*</span></label>
				<br/>
				<form:textarea path="content" id="editor${id}" cssClass="text"/>
			</div>
			<div>
				<button type="submit" class="button">
					提交
				</button>
				<form:hidden path="id"/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="projectId"/>
				<input type="hidden" name="entered" value='<fmt:formatDate value="${question.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#question-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form){
			var title = $.trim(formData[0].value);
			var content = $.trim(formData[1].value);
			if(title=='' || content=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(question){
			setTimeout(function(){
				window.location.href='?questionId=' + question.id;
			}, 500);
		}
	});
	$('#editor${id}').htmlarea(settings.simple);
});
</script>