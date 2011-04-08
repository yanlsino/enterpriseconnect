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

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="permissionForm" action="${base}/process/system/permission" commandName="permission">
			<fieldset>
				<div>
					<label for="categoryId">分类名称:</label>
					<form:select path="categoryId" items="${categories}" itemLabel="label" itemValue="id"/>
				</div>
				<div>
					<label for="roleId">角色名称:</label>
					<form:select path="roleId" items="${roles}" itemLabel="name" itemValue="id"/>
				</div>
				<c:choose>
					<c:when test="${param.multiple}">
					<div>
						<label for="resourceId">资源名称:</label>
						<select id="select1" multiple="multiple">
						<c:forEach var="resource" items="${resources}">
							<option value="${resource.id}">${resource.name}</option>
						</c:forEach>
						</select>
						<button id="addAction">Add&gt;&gt;</button>
						<button id="removeAction">Remove&lt;&lt;</button>
						<select id="select2" multiple="multiple"></select>
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
					<input type="submit" value=" 提交 "/>
					<input type="reset" value=" 重置 "/>
					<form:hidden path="id"/>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
<c:choose>
	<c:when test="${param.multiple}">
	<script type="text/javascript">
	$(document).ready(function(){
		$('select#categoryId').change(function(){
			window.location.href='?siteId=${param.siteId}&multiple=${param.multiple}&categoryId='+$(this).val();
		});
		$('#permissionForm').validate({
			submitHandler: function(form) {
				var categoryId = $('#categoryId').val();
				var roleId = $('#roleId').val();
				$('#select2 option:selected').each(function(i){
					var params = {
						categoryId : categoryId,
						roleId	   : roleId,
						resourceId : $(this).val()
					};
					$.ajax({
						async: false,
	     			    type: 'POST',
						url: '${base}/process/system/permission',
						data: params,
						dataType: 'json',
						success: function(jsonObj){}
					});
				});
				return false;
			},
			meta: "validate"
		});
		$('#addAction').click(function(){
			return !$('#select1 option:selected').remove().appendTo('#select2');
		});
		$('#removeAction').click(function(){
			return !$('#select2 option:selected').remove().appendTo('#select1');
		});
	});
	</script>
	</c:when>
	<c:otherwise>
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
	</c:otherwise>
</c:choose>