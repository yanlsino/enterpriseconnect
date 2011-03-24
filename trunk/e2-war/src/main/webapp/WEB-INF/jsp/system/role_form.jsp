<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="roleForm" action="${base}/process/system/role" commandName="role">
			<fieldset>
				<div>
					<label for="name">角色名:</label>
					<form:input path="name"/>
				</div>
				<div>
					<label for="code">编码:</label>
					<form:input path="code"/>
				</div>
				<div>
					<label for="level">级别:</label>
					<form:input path="level"/>
				</div>
				<div>
					<label for="description">描述:</label>
					<form:textarea path="description"/>
				</div>
				<div>
					<label for="enabled">启用:</label>
					<form:checkbox path="enabled"/>
				</div>
				<div>
					<label for="categoryId">分类:</label>
					<form:select path="categoryId" items="${categories}" itemLabel="label" itemValue="id"/>
				</div>
				<div>
					<input type="submit" value=" 提交 "/>
					<form:hidden path="id"/>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#roleForm').validate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(role){
					window.location.href="?roleId="+role.id+"&siteId=${param.siteId}";
				}
			});	
			return false;
		},
		meta: "validate"
	});
});
</script>