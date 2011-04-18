<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="project-form${id}" cssClass="project-form" 
			action="${base}/process/system/project" commandName="project">
				<div>
					<label for="title">名称: <span class="required">*</span></label>
					<br/>
					<form:input path="title" id="title${id}" cssClass="text"/>
				</div>
				<div>
					<label for="uniqueId">唯一编码: <span class="required">*</span>([a-z] - _)</label>
					<br/>
					<form:input path="uniqueId" id="uniqueId${id}" cssClass="text"/>
				</div>
				<div>
					<button type="submit" class="button">
					<span id="status1${id}">
						提交
					</span>
					<span id="status2${id}" style="display: none">
						<img src="${base}/static/images/loading.gif"/>正在处理...
					</span>
				</button>
					<form:hidden path="id"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="categoryId"/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('##project-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form) {
			var title = $.trim(formData[0].value);
			var uniqueId = $.trim(formData[1].value);
			if(title=='' || uniqueId=='' ||
					uniqueId.match(/^[\w_-]+$/)==null) {
				return false;
			}
		},
		success: function(project){
			window.location.href='${base}/'+project.uniqueId+'/profile';
		}
	});
});
</script>