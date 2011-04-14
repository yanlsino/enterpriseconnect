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
YUI().use('io-form', 'json', function(Y){
	var loginForm = Y.one('#login-form${id}');
	loginForm.on('submit', function(e){
		if(validateForm()) {
			Y.io.header('Content-Type', 'application/json');
			Y.on('io:complete', function(id, o){
				try {
					var user = Y.JSON.parse(o.responseText);
					if('${param.popup}' == 'true') {
						setTimeout('window.location.reload()', 0);
					} else {
						setTimeout('window.location.href="${base}/'+user.uniqueId+'/profile"', 0);
					}
				} catch(e) {
					// TODO alert message username or password invalid
				}
			});
			Y.io(loginForm.get('action'), {
				method: 'POST',
				form: {
					id: loginForm
				}
			});
		}
		e.halt();
	});
	//
	function validateForm() {
		var username = Y.one('#username${id}').get('value');
		var password = Y.one('#password${id}').get('value');
		if(Y.Lang.trim(username)=='' || Y.Lang.trim(password)=='') {
			return false;
		}
		// regex
		if(!username.match(/@.+\..+/)) {
			return false;
		}
		return true;
	}
});
</script>