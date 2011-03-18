<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="projectCategoryForm" action="${base}/process/system/category" 
			commandName="category">
			<fieldset>
				<div>
					<label for="label">显示名:</label>
					<form:input path="label" cssClass="{validate:{required:true, messages:{required:'显示名不能为空！'}}}"/>
				</div>
				<div>
					<label for="code">编码:</label>
					<form:input path="code" cssClass="{validate:{required:true, messages:{required:'编码不能为空！'}}}"/>
				</div>
				<div>
					<label for="level">排序值:</label>
					<form:input path="level"/>
				</div>
				<c:if test="${not empty categories}">
				<div>
					<label for="parentId">父分类:</label>
					<form:select path="parentId" items="${categories}" itemLabel="label" itemValue="id"/>
				</div>
				</c:if>
				<div>
					<label for="enabled">启用:</label>
					<form:checkbox path="enabled" value="true"/>
				</div>
				<div>
					<input type="submit" value=" 提交 "/>
					<form:hidden path="id"/>
					<form:hidden path="siteId"/>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#projectCategoryForm').validate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				success:function(category){
					window.location.href="?categoryId="+category.id+"&siteId="+category.siteId;
				}
			});	
			return false;
		},
		meta: "validate"
	});
});
</script>