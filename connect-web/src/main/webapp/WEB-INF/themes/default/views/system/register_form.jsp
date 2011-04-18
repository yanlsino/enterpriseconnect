<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<div class="body">
		<form:form id="register-form${id}"  cssClass="register-form"
			action="${base}/process/system/register" commandName="user">
			<fieldset>
				<legend>${title}</legend>
				<div>
					<label for="username" class="title"><fmt:message key="system.register_form.username" /><span class="required"> * </span></label>
					<br/>
					<form:input path="username" id="username${id}" cssClass="text" />
				</div>
				<div>
					<label for="password" class="title"><fmt:message key="system.register_form.password" /><span class="required"> * </span></label>
					<br/>
					<form:password path="password" id="password${id}" cssClass="text" />
				</div>
				<div>
					<label for="rePassword" class="title"><fmt:message key="system.register_form.confirmPassword" /><span class="required"> * </span></label>
					<br/>
					<form:password path="rePassword" id="rePassword${id}" cssClass="text" />
				</div>
				<div>
					<label for="nickname" class="title"><fmt:message key="system.register_form.nickname" /><span class="required"> * </span></label>
					<br/>
					<form:input path="nickname" id="nickname${id}" cssClass="text" />
				</div>
				<div>
					<button class="button" type="submit">
						<span id="status1${id}">
							加入 ${site.title}
						</span>
						<span id="status2${id}" style="display: none">
							<img src="${base}/static/images/loading.gif"/>正在处理...
						</span>
					</button>
					<button class="button" type="reset">重置</button>
					<form:hidden path="id" />
					<br class="clear"/>
				</div>
				<div>
					忘记密码？<a href="#">帮助</a>
					已经注册？<a href="${base}/login">登录</a>
				</div>
			</fieldset>
		</form:form>	
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#register-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData){
			var username = formData[0].value;
			var password1 = formData[1].value;
			var password2 = formData[2].value;
			var nickname = formData[3].value;
			if($.trim(username)=='' || $.trim(password1)=='' || 
					$.trim(password2)=='' || $.trim(nickname)=='' ||
					password1!=password2 || username.match(/@.+\..+/)==null) {
				return false;
			}
		},
		success: function(user){
			setTimeout(function(){
				window.location.href='${base}/' + user.uniqueId + '/profile';
			}, 0);
		}
	})
});
</script>