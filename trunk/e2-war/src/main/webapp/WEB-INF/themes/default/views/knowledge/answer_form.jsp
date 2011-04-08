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
	<c:choose>
		<c:when test="${empty user}">
			<div class="notice">回答问题，请先 <a href="#" class="loginAction">登录</a></div>
		</c:when>
		<c:otherwise>
		<form:form id="answer-form${id}" action="${base}/process/knowledge/answer" 
			commandName="answer" cssClass="answer-form">
			<div>
				<form:textarea path="content" id="content${id}" cssClass="{validate:{required:true, messages:{required:'内容不能为空！'}}}"/>
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
				<form:hidden path="questionId"/>
				<br class="clear"/>
			</div>
		</form:form>
		</c:otherwise>
	</c:choose>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var answerForm = Y.one('#answer-form${id}');
	answerForm.on('submit', function(e){
		var content = Y.one('#content${id}').get('value'); 
		if(content.trim()!='') {		
			Y.one('#status1${id}').hide();
			Y.one('#status2${id}').show();
			//
			Y.on('io:complete', function(id, o){
				try {
					var answer = Y.JSON.parse(o.responseText);
					setTimeout('window.location.href="?answerId='+answer.id+'"', 500);
					Y.one('#content${id}').set('value', '');
				} catch(e) {
					// TODO alert message username or password invalid
				}
			});
			Y.io(answerForm.get('action'), {
				method: 'POST',
				form: {
					id: answerForm
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
	$('#answer-form').validate({
		submitHandler: function(form) {
			$('#${id}').block({ 
				message: '<div class="notice">正在处理...</div>',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(answer){
					setTimeout('window.location.reload()', 500);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>
 --%>