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
					<form:select path="categoryId" id="select-category${id}" items="${categories}" itemLabel="label" itemValue="id"/>
				</div>
				<div>
					<label for="roleId">角色名称:</label>
					<br/>
					<form:select path="roleId" id="select-role${id}" items="${roles}" itemLabel="name" itemValue="id"/>
				</div>
				<c:choose>
					<c:when test="${param.multiple}">
					<div>
						<label for="resourceId">资源名称:</label>
						<br/>
						<select id="select1${id}" multiple="multiple">
						<c:forEach var="resource" items="${resources}">
							<option value="${resource.id}">${resource.name}</option>
						</c:forEach>
						</select>
						<a id="addAction${id}" href="#">&gt;&gt;Add</a>
						|
						<a id="removeAction${id}" href="#">Remove&lt;&lt;</a>
						<select id="select2${id}" multiple="multiple">
						<c:forEach var="p" items="${permissions}">
							<c:if test="${p.role.id eq permission.role.id}">
							<option value="${p.resource.id}">${p.resource.name}</option>
							</c:if>
						</c:forEach>
						</select>
						<form:hidden path="resourceId" id="resourceId${id}"/>
					</div> 					
					</c:when>
					<c:otherwise>
					<div>
						<label for="resourceId">资源名称:</label>
						<form:select path="resourceId" items="${resources}" itemLabel="name" itemValue="id"/>
					</div>
					</c:otherwise>
				</c:choose>
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
						for(i in formData) {
							alert(formData[i].value);
						}
					},
					success: function(permission){}
				});
			});
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