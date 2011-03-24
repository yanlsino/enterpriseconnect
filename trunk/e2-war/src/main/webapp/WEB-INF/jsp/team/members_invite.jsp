<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form id="inviteForm" action="${base}/process/team/members/invite">
			<div>
				<label>自动提示:</label>
				<input id="query"> 
			</div>
			<div>
				<label for="emails">Emails:</label>
				<textarea id="emails" name="emails" class="{validate:{required:true, messages:{required:'Email地址不能为空！'}}}"></textarea>
			</div>
			<div>
				<input type="submit" value=" 发送 "/>
				<input type="reset" value=" 重置 "/>
				<input type="hidden" name="uniqueId" value="${project.uniqueId}">
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#inviteForm').validate({
		submitHandler: function(form) {
			var settings = $('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				clearForm:true,
				success:function(data){
					if(data=='success') {
						$('#${fragmentConfig.id}').unblock();
					}
				}
			});
			return false;
		},
		meta: "validate"
	});
	$('#query').autocomplete({ 
		serviceUrl:'${base}/process/system/users/auto',
	    minChars:2, 
	    delimiter: /(,|;)\s*/, // regex or character
	    maxHeight:400,
	    width:300,
	    zIndex: 9999,
	    deferRequestBy: 0, //miliseconds
	    params: { uniqueId:"${project.uniqueId}" }, //aditional parameters
	    noCache: false, //default is false, set to true to disable caching
	    // callback function:
	    onSelect: function(value, data){
	    	var emails = $('#emails').val();
	    	emails += value + '\r\n';
	    	$('#emails').val(emails);
	    }
	});
});
</script>