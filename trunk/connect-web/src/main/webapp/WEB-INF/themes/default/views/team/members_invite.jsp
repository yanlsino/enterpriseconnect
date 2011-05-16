<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>
	</c:if>
	<div class="body">
		<form id="inviteForm${id}" action="${base}/process/team/members/invite">
			<div>
				<label>自动提示:</label>
				<br/>
				<input id="query${id}">
			</div>
			<div>
				<label for="emails">Emails:</label>
				<br/>
				<textarea id="emails" name="emails" class="{validate:{required:true, messages:{required:'Email地址不能为空！'}}}"></textarea>
			</div>
			<div>
				<button type="submit" class="button">发送</button>
				<button type="reset">重置</button>
				<input type="hidden" name="uniqueId" value="${project.uniqueId}">
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#inviteForm${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form) {
			var emails = formData[1].value;
			if($.trim(emails)=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(members){
			setTimeout(function(){
				window.location.reload();
			}, 500);
		}
	});
	$('#query${id}').autocomplete({
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
	    	$('#query${id}').val('');
	    }
	});
});
</script>