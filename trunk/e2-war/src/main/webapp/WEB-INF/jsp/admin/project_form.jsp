<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="projectForm" action="${base}/process/admin/project" commandName="project">
			<fieldset>
				<div>
					<label for="title">名称:</label>
					<form:input path="title" cssClass="{validate:{required:true, messages:{required:'名称不能为空！'}}}"/>
				</div>
				<div>
					<label for="uniqueId">唯一编码:([a-z] - _)</label>
					<form:input path="uniqueId" cssClass="{validate:{required:true, messages:{required:'唯一编码不能为空！'}}}"/>
				</div>
				<div>
					<label for="category">分类:</label>
					<form:input path="category.label" readonly="true"/>
				</div>
				<c:if test="${not empty subCategories1}">
				<div>
					<label for="subCategory1">子分类:</label>
					<form:select path="subCategoryId1">
						<form:option value="" label="无"/>
						<form:options items="${subCategories1}" itemLabel="label" itemValue="id"/>
					</form:select>
				</div>
				</c:if>
				<div>
					<input type="submit" value=" 提交 "/>
					<form:hidden path="id"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="categoryId"/>
					<input type="hidden" name="entered" value='<fmt:formatDate value="${project.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#projectForm').validate({
		submitHandler: function(form) {
			//
			var uniqueId = $('#uniqueId').val();
			if(uniqueId.match(/^([\w-_]+)$/)==null) {
				$.prompt('唯一编码格式不对，唯一编码由字符 [a-z] - _ 组成！');
				return false;
			}
			//
			var url = '${base}/check/system/project/exist?uniqueId='+$('#uniqueId').val();
			$.ajax({
				url:url, 
				async:false,
				success:function(exist){
					if(exist) {
						$.prompt('唯一编码已经存在！');
						return false;
					}
				}
			});
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				success:function(project){
					setTimeout('window.location.href="${base}/'+project.uniqueId+'/admin/project/form"', 500);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>