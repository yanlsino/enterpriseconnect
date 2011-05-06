<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<form:form id="site-link-form${id}" cssClass="site-link-form"
			action="${base}/process/system/site_link" commandName="siteLink">
			<div>
				<label>显示名 <span class="required">*</span></label>
				<br/>
				<form:input path="text" cssClass="text"/>
			</div>
			<div>
				<label>分类编码 <span class="required">*</span></label>
				<br/>
				<form:input path="code" cssClass="text"/>
			</div>
			<div>
				<label>URL <span class="required">*</span></label>
				<br/>
				<form:input path="href" cssClass="text"/>
			</div>
			<div>
				<button type="submit" class="button">提交</button>
				<form:hidden path="id"/>
				<form:hidden path="siteId"/>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#site-link-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form) {
			for(i in formData) {
				if($.trim(formData[i].value)=='') {
					return false;
				}
			}
		},
		success: function(siteLink){
			window.location.href='?siteId=${param.siteId}&linkId='+siteLink.id;
		}
	});
});
</script>