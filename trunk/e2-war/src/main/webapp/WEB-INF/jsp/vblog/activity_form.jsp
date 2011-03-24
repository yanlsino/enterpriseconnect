<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="activityForm" action="${base}/process/vblog/activity" commandName="activity">
			<div>
				<c:choose>
				<c:when test="${not empty user.project.profile.logo}">
				<img class="thumbnail" src="${base}/logo/download/${user.project.profile.logo.id}/60x60"/>
				</c:when>
				<c:otherwise>
				<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${user.project.category.code}.png"/>
				</c:otherwise>
			</c:choose>
				<form:textarea path="description" cssClass="vblog"/>
			</div>
			<div class="last">
				<div class="sync-targets float-right">
					<!--
					<img id="sinaImg" src="${base}/themes/${theme.name}/icons/sina_16x16.png">
					<input id="sina" type="checkbox" name="syncTarget" value="sina">
					<img id="doubanImg" src="${base}/themes/${theme}/icons/douban_16x16.png">
					<input id="douban" type="checkbox" name="syncTarget" value="douban">
					<img id="linkedInImg" src="${base}/themes/${theme.name}/icons/linkedin_16x16.png">
					<input id="linkedIn" type="checkbox" name="syncTarget" value="linkedIn">
					-->
					最多<span class="charsCounter">140</span>个字
				</div>
				<input class="button" type="submit" value='<fmt:message key="vblog.activity_form.submit"/>'/>
			</div>
			<form:hidden path="type"/>
			<form:hidden path="projectId"/>
			<form:hidden path="enteredId"/>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#activityForm .submit').click(function(){
		$('#activityForm').submit();
	});
	$('#activityForm').submit(function(){
		var content = $(this).find('textarea.vblog').val();
		if($.trim(content)!='') {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(this).ajaxSubmit({
				dataType:'json',
				clearForm: true,
				success:function(comment){
					setTimeout('window.location.reload()', 500);
				}
			});
		} else {
			$('#${fragmentConfig.id}').block({ 
	            message: '<h3>内容不能为空！</h3>',
	            overlayCSS: { backgroundColor: '#EEE' },
	            timeout: 1500
	        });
		}	
		return false;
	});
	//$('#activityForm').messageBoxHandler({
		//submitBtn:'#send',
		//leftCharsCounterClass:'.charsCounter'
	//});
	<%--
	$('textarea.vblog').markItUp(myVBlogSettings);
	--%>
});
</script>
