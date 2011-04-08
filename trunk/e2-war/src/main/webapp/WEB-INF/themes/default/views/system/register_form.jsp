<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment">
	<div class="body">
		<form:form id="register-form" action="${base}/process/system/register" commandName="user" cssClass="standard-form sided">
			<fieldset>
				<legend>${fragmentConfig.title}</legend>
				<p>
					<label for="username" class="title"><fmt:message key="system.register_form.username" /><span class="required"> * </span></label>
					<br/>
					<c:set var="usernameRequired"><fmt:message key='system.register_form.usernameRequired' /></c:set>
					<c:set var="invalidEmail"><fmt:message key='system.register_form.invalidEmail' /></c:set>
					<form:input path="username" cssClass="text {validate:{required:true, email:true, messages:{required:'${usernameRequired}', email:'${invalidEmail}'}}}" />
				</p>
				<p>
					<label for="password" class="title"><fmt:message key="system.register_form.password" /><span class="required"> * </span></label>
					<br/>
					<c:set var="passwordRequired"><fmt:message key='system.register_form.passwordRequired' /></c:set>
					<form:password path="password" cssClass="text {validate:{required:true, messages:{required:'${passwordRequired}'}}}" />
				</p>
				<p>
				<label for="rePassword" class="title"><fmt:message key="system.register_form.confirmPassword" /><span class="required"> * </span></label>
				<br/>
				<c:set var="confirmPasswordRequired"><fmt:message key='system.register_form.confirmPasswordRequired' /></c:set>
				<form:password path="rePassword" cssClass="text {validate:{required:true, messages:{required:'${confirmPasswordRequired}'}}}" />
				</p>
				<p>
					<label for="nickname" class="title"><fmt:message key="system.register_form.nickname" /><span class="required"> * </span></label>
					<br/>
					<c:set var="nicknameRequired"><fmt:message key='system.register_form.nicknameRequired' /></c:set>
					<form:input path="nickname" cssClass="text {validate:{required:true, messages:{required:'${nicknameRequired}'}}}" />
				</p>
				<p>
					<button class="button" type="submit">加入 ${site.title}</button>
					<button class="button" type="reset">重置</button>
					<form:hidden path="id" />
					<br class="clear"/>
				</p>
				<p>
					忘记密码？<a href="#">帮助</a>
					已经注册？<a href="${base}/login">登录</a>
				</p>
			</fieldset>
		</form:form>	
	</div>
</div>


<script type="text/javascript">
$(document).ready(function(){
	$('#register-form').validate({
		   submitHandler: function(form) {
			// check email or uniqueId
			var url = '${base}/check/system/user/exist?username='+$('#username').val();
			var flag = true;
			$.ajax({
				url:url, 
				async:false,
				success:function(exist){
					if(exist) {
						$.blockUI({ 
				            message: '<div class="notice">该用户已经存在！</div>',
				            timeout: 1500
				        });
						flag = false;
					}
				}
			});
			if(flag) {
				$(form).ajaxSubmit({
					dataType:'json',
					clearForm:true,
					success:function(user){
						$.blockUI({ 
				            message: '注册邮件已经发送到您的邮箱，请登录您的邮箱查看！',
				            timeout: 1500,
				            onUnblock: function(){window.location.href = '${base}';}
				        });
					}
				});
			}
		},
		meta: "validate"
	});	
});
</script>
</div>
</div>

