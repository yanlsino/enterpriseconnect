<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="permissionForm" action="${base}/process/system/permission" commandName="permission">
			<fieldset>
				<div>
					<label for="categoryId">分类名称:</label>
					<form:select path="categoryId" items="${categories}" itemLabel="label" itemValue="id"/>
				</div>
				<div>
					<label for="resourceId">资源名称:</label>
					<form:select path="resourceId" items="${resources}" itemLabel="name" itemValue="id"/>
				</div>
				<div>
					<label for="roleId">角色名称:</label>
					<form:select path="roleId" items="${roles}" itemLabel="name" itemValue="id"/>
				</div>
				<div>
					<label for="enabled">启用:</label>
					<form:checkbox path="enabled" value="true"/>
				</div>
				<div>
					<input type="submit" value=" 提交 "/>
					<input type="reset" value=" 重置 "/>
					<form:hidden path="id"/>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('select#categoryId').change(function(){
		window.location.href='?siteId=${param.siteId}&categoryId='+$(this).val();
	});
	$('#permissionForm').validate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(permission){
					window.location.href="?permissionId="+permission.id+"&siteId=${param.siteId}";
				}
			});	
			return false;
		},
		meta: "validate"
	});
});
</script>