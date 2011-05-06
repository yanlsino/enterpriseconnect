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
					<label for="categoryId">分类</label>
					<form:select path="categoryId" items="${categories}" itemLabel="label" itemValue="id"/>
				</div>
				<div>
					<label for="name">角色名 <span class="required">*</span></label>
					<br/>
					<form:input path="name"/>
				</div>
				<div>
					<label for="code">编码 <span class="required">*</span></label>
					<br/>
					<form:input path="code"/>
				</div>
				<div>
					<label for="level">级别 <span class="required">*</span></label>
					<br/>
					<form:input path="level"/>
				</div>
				<div>
					<label for="description">描述</label>
					<br/>
					<form:textarea path="description"/>
				</div>
				<div>
					<label for="enabled">启用</label>
					<form:checkbox path="enabled"/>
				</div>
				<div>
					<button type="submit" class="button">提交</button>
					<form:hidden path="id"/>
				</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#role-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form) {
			var name = $.trim(formData[0].value);
			var code = $.trim(formData[1].value);
			var level = $.trim(formData[2].value);
			if(name=='' || code=='' ||level=='') {
				return false;
			}
		},
		success: function(role){
			window.location.href='?roleId='+role.id+'&siteId=${param.siteId}';
		}
	});
});
</script>