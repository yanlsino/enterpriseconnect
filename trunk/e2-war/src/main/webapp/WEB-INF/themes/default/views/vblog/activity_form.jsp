<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.opensourceforce.org/tags" prefix="osf"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
			<c:when test="${not empty user.project.profile.logo}">
			<img id="activity-form-logo${id}" class="activity-form-logo top left thumbnail" src="${base}/logo/download/${user.project.profile.logo.id}/60x60"/>
			</c:when>
			<c:otherwise>
			<img id="activity-form-logo${id}" class="activity-form-logo top left thumbnail" src="${base}/themes/${theme.name}/stock/${user.project.category.code}.png"/>
			</c:otherwise>
		</c:choose>
		<form:form id="activity-form${id}" class="activity-form" 
			action="${base}/process/vblog/activity" commandName="activity">
			<div>
				<form:textarea path="description" id="description${id}"/>
			</div>
			<div>
				<span class="top right">最多<strong class="charsCounter">140</strong>个字</span>
				<button id="${id}submitButton" type="submit" class="button">
					<span id="status1${id}">
						<fmt:message key="vblog.activity_form.submit"/>
					</span>
					<span id="status2${id}" style="display: none">
						<img src="${base}/static/images/loading.gif"/>正在处理...
					</span>
				</button>
			</div>
			<form:hidden path="type"/>
			<form:hidden path="projectId"/>
			<form:hidden path="enteredId"/>
		</form:form>
		<br class="clear"/>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
    var description = Y.one('#description${id}');
	var activityForm = Y.one('#activity-form${id}');
	activityForm.on('submit', function(e){
		// validate 
		if(description.get('value')=='') {
			// TODO alert description can not be empty
			return e.halt();
		}
		//
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
    	//description.set('disabled', 'disabled');
    	//
    	Y.on('io:complete', function(id, o){
			try {
				var activity = Y.JSON.parse(o.responseText);
				setTimeout('window.location.reload()', 500);
				description.set('value','');
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(activityForm.get('action'), {
			method: 'POST',
			form: {
				id: activityForm
			}
		});
		e.halt();
	});
});
</script>

<!-- 
<script type="text/javascript">
$(document).ready(function(){
	$('#activity-form .submit').click(function(){
		$('#activity-form').submit();
	});
	$('#activity-form').submit(function(){
		var content = $(this).find('textarea').val();
		if($.trim(content)!='') {
			$('#${fragmentConfig.id}').block({ 
				message: '<div class="notice">正在处理...</div>',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(this).ajaxSubmit({
				dataType:'json',
				clearForm: true,
				success:function(comment){
					setTimeout('window.location.reload()', 500);
				}
			});
		} else {
			$('#${fragmentConfig.id}').block({ 
	            message: '<div class="error">内容不能为空！</div>',
	            overlayCSS: { backgroundColor: '#EEE' },
	            timeout: 1500
	        });
		}	
		return false;
	});
});
</script>
-->