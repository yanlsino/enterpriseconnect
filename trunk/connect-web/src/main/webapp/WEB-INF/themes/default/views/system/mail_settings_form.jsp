<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<form:form id="mail-settings-form${id}" cssClass="mail-settings-form"
		action="${base}/process/system/mail_settings" commandName="mailSettings">
				<div>
					<label for="host">主机</label>
					<br/>
					<form:input path="host"/>
				</div>
				<div>
					<label for="port">端口</label>
					<br/>
					<form:input path="port"/>
				</div>
				<div>
					<label for="username">Email</label>
					<br/>
					<form:input path="username"/>
				</div>
				<div>
					<label for="password">密码</label>
					<br/>
					<form:input path="password"/>
				</div>
				<div>
					<label for="enabled">启用</label>
					<br/>
					<form:checkbox path="enabled"/>
				</div>
				<div>
					<button type="submit">提交</button>
					<form:hidden path="id"/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#mail-settings-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form) {
		},
		success: function(mailSettings){
			window.location.href='?mailSettingsId='+mailSettings.id;
		}
	});
});
</script>