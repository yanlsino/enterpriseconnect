<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="questionForm" action="${base}/process/knowledge/question" commandName="question">
			<div>
				<label for="title">标题</label>
				<form:input path="title" cssClass="{validate:{required:true, messages:{required:'标题不能为空！'}}}"/>
			</div>
			<div>
				<label for="content">内容</label>
				<form:textarea path="content" cssClass="{validate:{required:true, messages:{required:'内容不能为空！'}}}"/>
			</div>
			<div>
				<input type="submit" value=" 提交 "/>
				<form:hidden path="id"/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="projectId"/>
			</div>
		</form:form>
	</div>
</div>

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
	$('#questionForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
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