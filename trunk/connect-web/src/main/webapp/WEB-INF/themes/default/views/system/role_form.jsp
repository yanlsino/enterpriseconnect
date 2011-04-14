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
		<form:form id="role-form${id}" cssClass="role-form" 
			action="${base}/process/system/role" commandName="role">
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
		</form:form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var roleForm = Y.one('#role-form${id}');
	roleForm.on('submit', function(e){
		Y.io.header('Content-Type', 'application/json');
		Y.on('io:complete', function(id, o){
			try {
				var role = Y.JSON.parse(o.responseText);
				window.location.href='?roleId='+role.id+'&siteId=${param.siteId}';
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(roleForm.get('action'), {
			method: 'POST',
			form: {
				id: roleForm
			}
		});
		e.halt();
	});
});
</script>

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