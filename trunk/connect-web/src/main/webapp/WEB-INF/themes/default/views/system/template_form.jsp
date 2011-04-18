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
		<form:form id="template-form${id}" cssClass="template-form" 
			action="${base}/process/system/template" commandName="template">
				<div>
					<label for="name">名称:</label>
					<form:input path="name"/>
				</div>
				<div>
					<label for="code">编码:</label>
					<form:input path="code"/>
				</div>
				<div>
					<label for="content">内容:</label>
					<form:textarea path="content"/>
				</div>
				<div>
					<label for="category">分类:</label>
					<form:select path="categoryId" items="${categories}" itemLabel="label" itemValue="id"/>
				</div>
				<div>
					<label for="enabled">启用:</label>
					<form:checkbox path="enabled"/>
				</div>
				<div>
					<input type="submit" value=" 提交 "/>
					<form:hidden path="id"/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#template-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form) {
		},
		success: function(template){
			window.location.href='?templateId='+template.id+'&siteId=${param.siteId}';
		}
	});
});
</script>