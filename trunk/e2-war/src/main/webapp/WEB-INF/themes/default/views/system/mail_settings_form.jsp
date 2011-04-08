<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="mailSettingsForm" cssClass="ajaxSubmit" action="${base}/process/system/mail_settings" 
		commandName="mailSettings">
			<fieldset>
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
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#mailSettingsForm').validate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(mailSettings){
					window.location.href="?mailSettingsId="+mailSettings.id;
				}
			});	
			return false;
		},
		meta: "validate"
	});
});
</script>