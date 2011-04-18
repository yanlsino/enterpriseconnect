<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<div class="body">
		<form id="login-form${id}" class="login-form" 
			action="${base}/j_security_check" method="POST"> 
			<fieldset>
				<legend>${title}</legend>
				<div>
            		<label class="title"><fmt:message key="system.login_form.username"/><span class="required">*</span></label>
            		<br/>
            		<input id="username${id}" name="j_username" class="text formmgr-field yiv-required"/>
              	</div>
				<div>
            		<label for="j_password" class="title"><fmt:message key="system.login_form.password"/><span class="required">*</span></label>
            		<br/>
              		<input type="password" id="password${id}" name="j_password" class="text formmgr-field yiv-required"/>
				</div>
				<div>					
            		<label for="rememberMe" class="title"><fmt:message key="system.login_form.rememberMe"/></label>
	             	<input type="checkbox" name="rememberMe" value="true" checked="checked"/>
	            </div>
	            <div>
        			<button id="submitButton" type="submit" class="button positive">
    					<img src="${base}/themes/${theme.name}/css/plugins/buttons/icons/tick.png" alt=""/>
    					<fmt:message key="system.login_form.submit"/>
  					</button>
  					<button type="reset" class="button negative">
					    <img src="${base}/themes/${theme.name}/css/plugins/buttons/icons/cross.png" alt=""/>
					    <fmt:message key="system.login_form.reset"/>
					</button>
          		</div>
        	</fieldset>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#login-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData){
			// validate 
			var username = formData[0].value;
			var password = formData[1].value;
			if($.trim(username)=='' || $.trim(password)=='' ||
					username.match(/@.+\..+/)==null) {
				return false;
			}
		},
		success: function(user){
			setTimeout(function(){
				if('${param.popup}' == 'true') {
					window.location.reload();					
				} else {
					window.location.href='${base}/' + user.uniqueId + '/profile';
				}
			}, 0);
		}
	});
});
</script>