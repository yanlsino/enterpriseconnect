<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
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
		<form:form id="question-form${id}" name="question-form${id}" cssClass="question-form"
			action="${base}/process/knowledge/question" commandName="question">
			<div class="formmgr-row">
				<label for="title" class="title">标题 <span class="required">*</span></label>
				<br/>
				<form:input path="title" cssClass="title"/>
			</div>
			<div class="formmgr-row">
				<label for="content" class="title">内容 <span class="required">*</span></label>
				<br/>
				<form:textarea path="content" cssClass="text"/>
			</div>
			<div>
				<button type="submit" class="button">
					<span id="status1${id}">
						提交
					</span>
					<span id="status2${id}" style="display: none">
						<img src="${base}/static/images/loading.gif"/>正在处理...
					</span>
				</button>
				<form:hidden path="id"/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="projectId"/>
			</div>
		</form:form>
	</div>
</div>


<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var questionForm = Y.one('#question-form${id}');
	questionForm.on('submit', function(e){
		// validation
		var title = Y.one('#question-form${id} #title').get('value');
		var content = Y.one('#question-form${id} #content').get('value');
		//
		if(title.trim()!='' && content.trim()!='') {
			Y.one('#status1${id}').hide();
			Y.one('#status2${id}').show();
			//
			Y.on('io:complete', function(id, o){
				try {
					var question = Y.JSON.parse(o.responseText);
					setTimeout('window.location.href="?questionId='+question.id+'"', 500);
				} catch(e) {
					// TODO alert message username or password invalid
				}
			});
			Y.io(questionForm.get('action'), {
				method: 'POST',
				form: {
					id: questionForm
				}
			});
		}
		e.halt();
	});
});
</script>
<%-- 
<script type="text/javascript">
$(document).ready(function(){
	KE.show({
		id : 'content',
		resizeMode : 1,
		allowPreviewEmoticons : false,
		allowUpload : false,
		items : [
		'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
		'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
		'insertunorderedlist']
	});
	$('#question-form').validate({
		submitHandler: function(form) {
			$('#${id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(question){
					setTimeout('window.location.href="?questionId='+question.id+'"', 500);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>
--%>