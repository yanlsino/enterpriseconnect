<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<link rel="stylesheet" type="text/css" href="${base}/themes/${theme.name}/css/registration.css"/>

<div id="regbody">

	<div class="regwrapper">

		<div id="regmain" class="join-openSrcForce cold cookied no-profile">

			<div id="register-global-error">
				<div class="regalert attention">
					<p>
						<strong>嗯...看来您已经有了一个${site.title}帐户</strong>，
						<a href="${base}/login">马上登录</a>。
					</p>
				</div>
			</div>

			<div id="${fragmentConfig.id}" class="regcontent">
				<div class="introduction">
					<h1>
						${fragmentConfig.title}
					</h1>
				</div>

				<div class="register-container">
					<form:form id="registerForm" action="${base}/process/system/register" commandName="user" cssClass="standard-form sided">
						<ul>
							<li>
								<label for="username-coldRegistrationForm">
									<fmt:message key="system.register_form.username" />
									<span class="required"> * </span>
								</label>
								<div class="fieldgroup">
									<c:set var="usernameRequired">
										<fmt:message key='system.register_form.usernameRequired' />
									</c:set>
									<c:set var="invalidEmail">
										<fmt:message key='system.register_form.invalidEmail' />
									</c:set>
									<form:input path="username" id="username-coldRegistrationForm" cssClass="{validate:{required:true, email:true, messages:{required:'${usernameRequired}', email:'${invalidEmail}'}}}" />
								</div>
							</li>

							<li>
								<label for="password-coldRegistrationForm">
									<fmt:message key="system.register_form.password" />
									<span class="required"> * </span>
								</label>
								<div class="fieldgroup">
									<c:set var="passwordRequired">
										<fmt:message key='system.register_form.passwordRequired' />
									</c:set>
									<form:password path="password" id="password-coldRegistrationForm" cssClass="{validate:{required:true, messages:{required:'${passwordRequired}'}}}" />
								</div>
							</li>

							<li>
								<label for="rePassword-coldRegistrationForm">
									<fmt:message key="system.register_form.confirmPassword" />
									<span class="required"> * </span>
								</label>
								<div class="fieldgroup">
									<c:set var="confirmPasswordRequired">
										<fmt:message key='system.register_form.confirmPasswordRequired' />
									</c:set>
									<form:password path="rePassword" id="rePassword-coldRegistrationForm" cssClass="{validate:{required:true, messages:{required:'${confirmPasswordRequired}'}}}" />
								</div>
							</li>
							<li>
								<label for="nickname-coldRegistrationForm">
									<fmt:message key="system.register_form.nickname" />
									<span class="required"> * </span>
								</label>
								<div class="fieldgroup">
									<c:set var="nicknameRequired">
										<fmt:message key='system.register_form.nicknameRequired' />
									</c:set>
									<form:input path="nickname" id="nickname-coldRegistrationForm" cssClass="{validate:{required:true, messages:{required:'${nicknameRequired}'}}}" />

								</div>

							</li>
						</ul>

						<p class="actions">
							<button class="btn-reg" type="submit" id="btn-submit">
								<div>
									加入 ${site.title}
								</div>
							</button>
							<button class="btn-reg" type="reset" id="btn-reset">
								<div>
									重置
								</div>
							</button>
							<form:hidden path="id" />
						</p>

						<p id="register-custom-nav" class="key">
							已经注册？
							<a href="${base}/login">登录</a>
						</p>


					</form:form>
				</div>
			</div>
			
			<div id="aboutextra">
				<div class="about-module mod-util">
					<div class="aboutheader">
						<h3>
							${site.title}给您提供服务...
						</h3>
					</div>
					<div class="aboutcontent">
						<ul>
							<li>
								建立您的专业网上个人资料
							</li>
							<li>
								更好的与同事、朋友保持联系
							</li>
							<li>
								找专家，想法和机会，更快的了解和掌握新的开源产品
							</li>
						</ul>
					</div>
				</div>
			</div>

		</div>

		<p id="legend">
			欢迎加入${site.title}
		</p>

		<script type="text/javascript">
$(document).ready(function(){
	$('#registerForm').validate({
		   submitHandler: function(form) {
			// check email or uniqueId
			var url = '${base}/check/system/user/exist?username='+$('#username-coldRegistrationForm').val();
			var flag = true;
			$.ajax({
				url:url, 
				async:false,
				success:function(exist){
					if(exist) {
						$.blockUI({ 
				            message: '该用户已经存在！',
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

