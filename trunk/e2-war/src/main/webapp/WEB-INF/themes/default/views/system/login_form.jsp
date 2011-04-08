<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<div class="body">
		<form id="login-form${id}" class="login-form" name="login-form${id}" 
			action="${base}/j_security_check" method="POST"> 
			<fieldset>
				<legend>${title}</legend>
				<div class="formmgr-row">
            		<label class="title"><fmt:message key="system.login_form.username"/><span class="required">*</span></label>
            		<p class="formmgr-message-text"></p>
            		<input id="username" name="j_username" class="text formmgr-field yiv-required"/>
              	</div>
				<div class="formmgr-row">
            		<label for="j_password" class="title"><fmt:message key="system.login_form.password"/><span class="required">*</span></label>
            		<p class="formmgr-message-text"></p> 
              		<input type="password" id="password" name="j_password" class="text formmgr-field yiv-required"/>
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
YUI().use('gallery-formmgr', 'io-form', 'json', function(Y){
	var formManager = new Y.FormManager('login-form${id}');
	//
	formManager.prepareForm();
	formManager.initFocus();
	//
	formManager.setErrorMessages('username', {
		required: '请输入用户名！（Email）',
		regex:    '请输入有效的 Email 地址！'
	});
	formManager.setRegex('username', /@.+\..+/);
	//
	formManager.setErrorMessages('password', {
		required: '密码不能为空！'
	});
	//
	formManager.setErrorMessages('username', {
		required: '请输入用户名！（Email）',
		regex:    '请输入有效的 Email 地址！'
	});
	var loginForm = Y.one('#login-form${id}');
	loginForm.on('submit', function(e){
		formManager.validateForm();
		if(!formManager.hasErrors()) {
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
});
</script>



<!-- 
<script type="text/javascript" src="${base}/static/js/inputex/js/inputex-loader.js"></script>
<script type="text/javascript">
function init() {
	new inputEx.Form( {  
        fields: [
			{type:'email', label: 'Email', name: 'j_username', required: true},
            {type:'password', label: '密码', name: 'j_password', required: true} 
        ],  
        buttons: [
        	{type: 'submit', value: '登录'},
        	{type: 'reset', value: '重置'}
        ],    
        parentEl: 'container1'  
    });
}
		
		
		var loader = new YAHOO.util.YUILoader({ 
		    require: ["inputex-emailfield", "inputex-passwordfield", "inputex-form"], 
		    loadOptional: true, 
		        //base: "../lib/yui/", // remove the comment if you want to load YUI locally 
		    onSuccess: init 
		}); 
		 
		/**
		 * Important: this functions declares all inputEx Modules to YUI
		 */ 
		YAHOO.addInputExModules(loader, '${base}/static/js/inputex/'); 
		loader.insert(); 
</script>
 -->
<!-- 
<script type="text/javascript">
$(document).ready(function(){
	$('#loginForm').validate({
		submitHandler: function(form) {
			// check email or uniqueId
			var url = '${base}/check/system/user/exist?username='+$('#username').val();
			var flag = true;
			$.ajax({
				url:url, 
				async:false,
				success:function(exist){
					if(!exist) {
						$.blockUI({ 
				            message: '<div class="error">用户 '+$('#username').val()+' 不存在!</div>',
				            timeout: 1500
				        });
						flag = false;
					}
				}
			});
			if(flag) {
				$(form).ajaxSubmit({
					dataType:'json',
					success:function(user){
						if('${param.popup}' == 'true') {
							setTimeout('window.location.reload()', 0);
						} else {
							setTimeout('window.location.href="${base}/'+user.uniqueId+'/profile"', 0);
						}
					}
				});
			}
		},
		meta: "validate"
	});
});
</script>
 -->