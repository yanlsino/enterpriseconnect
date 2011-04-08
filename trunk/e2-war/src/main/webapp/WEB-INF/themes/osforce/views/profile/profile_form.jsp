<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="profileForm" action="${base}/process/profile/profile" commandName="profile">
			<fieldset>
				<div id="profile-photo">
				<c:choose>
					<c:when test="${not empty profile.logo}">
					<img class="thumbnail" src="${base}/logo/download/${profile.logo.id}/75x75"/>
					</c:when>
					<c:otherwise>
					<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${profile.project.category.code}.png"/>
					</c:otherwise>
				</c:choose>
					<div class="clear"></div>
					<a id="thumbnailEdit" href="#"><fmt:message key="profile.profile_form.edit"/></a>
				</div>
				<div>
					<label for="title"><fmt:message key="profile.profile_form.title"/></label>
					<form:input path="title" cssClass="{validate:{required:true, messages:{required:'标题不能为空！'}}}"/>
				</div>
				<div>
					<label for="shortDescription"><fmt:message key="profile.profile_form.shortDescription"/></label>
					<form:textarea path="shortDescription" cssClass="{validate:{required:true, messages:{required:'简介不能为空！'}}}"/>
				</div>
				<div>
					<label for="description"><fmt:message key="profile.profile_form.description"/></label>
					<form:textarea path="description"/>
				</div>
			</fieldset>
			<fieldset>
				<c:forEach var="customAttribute" items="${customAttributes}">
				<div>
					<label for="${customAttribute.name}"><fmt:message key="profile.profile_form.${customAttribute.name}"/></label>
					<input name="${customAttribute.name}" value="${customAttribute.value}">
				</div>
				</c:forEach>
			</fieldset>
			<div>
				<input type="submit" value='<fmt:message key="profile.profile_form.submit"/>'>
				<form:hidden path="id"/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="projectId"/>
				<form:hidden path="logoId"/>
				<form:hidden path="attributesTemplateCode"/>
			</div>
		</form:form>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#thumbnailEdit').click(function(){
		$.fn.nyroModalManual({
			bgColor: '#DDD',
			url:'${base}/app/attachment/form?popup=true&profileId=${profile.id}&forward=/profile/logo'
		});
		return false;
	})
	
	$('#profileForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(profile){
					setTimeout('window.location.href="?profileId='+profile.id+'"', 500);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>