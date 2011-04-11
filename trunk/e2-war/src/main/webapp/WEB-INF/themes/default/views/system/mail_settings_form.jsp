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
					<label for="host">主机:</label>
					<form:input path="host"/>
				</div>
				<div>
					<label for="port">端口</label>
					<form:input path="port"/>
				</div>
				<div>
					<label for="username">Email</label>
					<form:input path="username"/>
				</div>
				<div>
					<label for="password">密码</label>
					<form:password path="password"/>
				</div>
				<div>
					<label for="enabled">启用</label>
					<form:checkbox path="enabled"/>
				</div>
				<div>
					<input type="submit" value=" 提交 "/>
					<form:hidden path="id"/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var mailSettingsForm = Y.one('#mail-settings-form${id}');
	mailSettingsForm.on('submit', function(e){
		Y.on('io:complete', function(id, o){
			try {
				var mailSettings = Y.JSON.parse(o.responseText);
				window.location.href='?mailSettingsId='+mailSettings.id;
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(mailSettingsForm.get('action'), {
			method: 'POST',
			form: {
				id: mailSettingsForm
			}
		});
		e.halt();
	});
});
</script>