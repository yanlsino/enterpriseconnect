<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="page-title"><h1>${fragmentConfig.title}</h1></div>

<div class="login">   
      
      <div id="cookieDisabled">登录前确认您的浏览器已经开启cookies和JavaScript</div>
      <script type="text/javascript">
        if (navigator.cookieEnabled == true) {
          LI.hide('cookieDisabled');
        }
      </script>
    
  
    
  
      <form id="loginForm" action="${base}/j_security_check" method="POST"> 
      
  
      <ul>
          <li>		
            <label for="j_username"><fmt:message key="system.login_form.username"/><span class="required">*</span></label>
            <div class="fieldgroup">
              <input id="username" name="j_username" tabindex="1" class="{validate:{required:true, email:true, messages:{required:'请填写你的用户名！', email:'请填写有效的email地址！'}}}"/>
              
            </div>
          </li>
          <li>         
					
            <label for="j_password"><fmt:message key="system.login_form.password"/><span class="required">*</span></label>
            <div class="fieldgroup">
              <input type="password" name="j_password" tabindex="2" class="{validate:{required:true, messages:{required:'请填写你的密码！'}}}"/>
            </div>
          </li>
          
           <li>         
					
            <label for="rememberMe"><fmt:message key="system.login_form.rememberMe"/></label>
            <div class="fieldgroup_L">
             	<input type="checkbox" name="rememberMe" value="true" checked/>
            </div>
          </li>   
          
          
        <li class="button">
        	<input type="submit" class="btn-primary" tabindex="3" value='<fmt:message key="system.login_form.submit"/>'/>&nbsp;&nbsp;
			<input type="reset" class="btn-primary" value="<fmt:message key="system.login_form.reset"/>"/>
          <span> 或  <a href="${base}/register" class="nav-link">加入开源力量</a></span>
        </li>
      </ul>
</form>
    
  
</div>



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
				            message: '<h3>用户 '+$('#username').val()+' 不存在!</h3>',
				            timeout: 1500
				        });
						flag = false;
					}
				}
			});
			if(flag) {
				form.submit();
			}
		},
		meta: "validate"
	});
});
</script>