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
		<form:form id="project-form${id}" action="${base}/process/admin/project" 
			commandName="project" cssClass="project-form">
				<div>
					<label for="title" class="title">名称: <span class="required">*</span></label>
					<br/>
					<form:input path="title" cssClass="text {validate:{required:true, messages:{required:'名称不能为空！'}}}"/>
				</div>
				<div>
					<label for="uniqueId" class="title">唯一编码:([a-z] - _) <span class="required">*</span></label>
					<br/>
					<form:input path="uniqueId" cssClass="text {validate:{required:true, messages:{required:'唯一编码不能为空！'}}}"/>
				</div>
				<div>
					<label for="category" class="title">分类:</label>
					<br/>
					<form:input path="category.label" readonly="true" cssClass="text"/>
				</div>
				<c:if test="${not empty subCategories1}">
				<div>
					<label for="subCategory1" class="title">子分类:</label>
					<br/>
					<form:select path="subCategoryId1">
						<form:option value="" label="无"/>
						<form:options items="${subCategories1}" itemLabel="label" itemValue="id"/>
					</form:select>
				</div>
				</c:if>
				<div>
					<button type="submit" class="button">提交</button>
					<form:hidden path="id"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="categoryId"/>
					<input type="hidden" name="entered" value='<fmt:formatDate value="${project.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#project-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData){
			var title = $.trim(formData[0].value);
			var uniqueId = $.trim(formData.[1].value);
			if(title=='' || uniqueId=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(project){
			setTimeout(function(){
				window.location.reload();
			}, 500);
		}
	});
});
</script>