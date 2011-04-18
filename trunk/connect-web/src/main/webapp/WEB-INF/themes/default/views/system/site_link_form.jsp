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
		<form:form id="site-link-form" cssClass="site-link-form" 
			action="${base}/process/system/site_link" commandName="siteLink">
			<div>
				<label>显示名:</label>
				<form:input path="text" cssClass="{validate:{required:true, messages:{required:'显示名不能我为空！'}}}"/>
			</div>
			<div>
				<label>分类编码:</label>
				<form:input path="code" cssClass="{validate:{required:true, messages:{required:'分类编码不能为空！'}}}"/>
			</div>
			<div>
				<label>URL:</label>
				<form:input path="href" cssClass="{validate:{required:true, messages:{required:'网站链接不能为空！'}}}"/>
			</div>
			<div>
				<input type="submit" value=" 提交 "/>
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
		},
		success: function(siteLink){
			window.location.href='?siteId=${param.siteId}&linkId='+siteLink.id;
		}
	});
});
</script>