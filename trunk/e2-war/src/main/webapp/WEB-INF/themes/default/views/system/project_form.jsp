<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="projectForm" action="${base}/process/system/project" commandName="project">
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
					<input type="submit" value=" 提交 "/>
					<form:hidden path="id"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="categoryId"/>
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
			form.submit();
			return true;
		},
		meta: "validate"
	});
});
</script>