<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		<form id="logo-form${id}" class="logo-form"
			action="${base}/process/commons/attachment" method="post">
			<div>
			<c:choose>
				<c:when test="${not empty profile.logo}">
				<img class="thumbnail" src="${base}/logo/download/${profile.logo.id}/75x75"/>
				</c:when>
				<c:otherwise>
				<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${profile.project.category.code}.png"/>
				</c:otherwise>
			</c:choose>
			</div>
			<div>
				<input type="file" id="select-file${id}" name="file">
				<input type="hidden" name="forward" value="/profile/logo"/>
				<input type="hidden" name="profileId" value="${profile.id}"/>
			</div>
		</form>

		<form:form id="profile-form${id}" cssClass="profile-form"
			action="${base}/process/profile/profile" commandName="profile">
			<div>
				<label for="title"><fmt:message key="profile.profile_form.title"/> <span class="required">*</span></label>
				<br/>
				<form:input path="title"/>
			</div>
			<div>
				<label for="shortDescription"><fmt:message key="profile.profile_form.shortDescription"/> <span class="required">*</span></label>
				<br/>
				<form:textarea path="shortDescription" cssClass="shortDescription "/>
			</div>
			<div>
				<label for="description"><fmt:message key="profile.profile_form.description"/></label>
				<form:textarea path="description" id="editor${id}" cssClass="description"/>
			</div>
			<div id="profile-attributes${id}" class="profile-attributes">
			<c:forEach var="entry" items="${profile.attributesMap}">
				<div>
					<input name="labels" value="${entry.key}"/>
					<input name="values" value="${entry.value}"/>
				</div>
			</c:forEach>
			</div>
			<div>
				<button type="submit" class="button">
					<fmt:message key="profile.profile_form.submit"/>
				</button>
				<form:hidden path="id"/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="projectId"/>
				<form:hidden path="logoId"/>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#select-file${id}').change(function(){
		$('#logo-form${id}').ajaxSubmit({
			beforeSubmit: function(formData, $form){
				$form.find('input[type=file]').busy({
					img: '${base}/static/images/loading.gif'
				});
			},
			success: function(profile){
				window.location.reload();
			}
		});
	});

	$('#profile-form${id}').ajaxForm({
		dataType: 'json',
		beforeSubmit: function(formData, $form) {
			var title = $.trim(formData[0].value);
			var shortDescription = $.trim(formData[1].value);
			if(title=='' || shortDescription=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(profile){
			setTimeout(function(){
				window.location.href='?profileId=' + profile.id;
			}, 500);
		}
	});
	$('#editor${id}').htmlarea(settings.simple);
});
</script>