<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
<!--
select[multiple] {
	height: 200px;
	width: 100px;
}
-->
</style>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>
	</c:if>
	<div class="body">
		<form:form id="permission-form${id}" cssClass="permission-form"
			action="${base}/process/system/permission" commandName="permission">
				<div>
					<label for="categoryId">分类名称:</label>
					<br/>
					<form:input path="category.label" readonly="true"/>
					<form:hidden path="categoryId"/>
				</div>
				<div>
					<label for="resourceId">资源名称:</label>
					<br/>
					<form:input path="resource.name" readonly="true"/>
					<form:hidden path="resourceId"/>
				</div>
				<div>
					<label for="roleId">角色名称:</label>
					<br/>
					<form:select path="roleId" id="select-role${id}">
						<form:option value=""/>
						<form:options items="${roles}" itemLabel="name" itemValue="id"/>
					</form:select>
				</div>
				<div>
					<label for="enabled">状态</label>
					<br/>
					<form:checkbox path="enabled"/>
				</div>
				<div>
					<button id="submit${id}" type="submit" class="button">提交</button>
					<button type="reset">重置</button>
					<form:hidden path="id"/>
				</div>
		</form:form>
	</div>
</div>

<c:choose>
	<c:when test="${param.multiple}">
	<script type="text/javascript">
	$(document).ready(function(){
		$('#select-category${id}').change(function(){
			window.location.href='?siteId=${param.siteId}&multiple=${param.multiple}&categoryId='+$(this).val();
		});
		$('#select-role${id}').change(function(){
			window.location.href='?siteId=${param.siteId}&multiple=${param.multiple}&categoryId=${param.categoryId}&roleId='+$(this).val();
		});
		//
		$('#addAction${id}').click(function(){
			return !$('#select1${id} option:selected').remove().appendTo('#select2${id}');
		});
		$('#removeAction${id}').click(function(){
			return !$('#select2${id} option:selected').remove().appendTo('#select1${id}');
		});
		//
		$('#permission-form${id}').submit(function(){
			var form = this;
			$('#select2${id} option').each(function(){
				var resourceId = $(this).val();
				$(form).ajaxSubmit({
					dataType: 'json',
					clearForm: true,
					forceSync: true,
					beforeSerialize: function($form, options){
						$('#resourceId${id}').val(resourceId);
					},
					beforeSubmit: function(formData, $form) {
					},
					success: function(permission){}
				});
			});
			window.location.reload();
			return false;
		});
	});
	</script>
	</c:when>
	<c:otherwise>
	<script type="text/javascript">
	$(document).ready(function(){
		$('#permission-form${id}').ajaxForm({
			dataType: 'json',
			clearForm: true,
			beforeSubmit: function(formData, $form) {
			},
			success: function(permission){
				window.location.href='?permissionId='+permission.id+'&siteId=${param.siteId}&categoryId=${param.categoryId}';
			}
		});
	});
	</script>
	</c:otherwise>
</c:choose>