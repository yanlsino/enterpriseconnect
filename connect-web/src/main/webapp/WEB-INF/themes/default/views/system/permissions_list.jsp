<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="id" value="${fragmentConfig.id}"/>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>
	</c:if>
	<div class="body">
		<div>
			<span>当前分类：</span>
			<select id="select-category${id}">
				<c:forEach var="category" items="${categories}">
				<option value="${category.id}" <c:if test="${category.id eq param.categoryId}">selected="selected"</c:if>>${category.label}</option>
				</c:forEach>
			</select>
		</div>
		<table class="permissions-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>名称</th>
					<th>编码</th>
					<th>默认角色</th>
					<th class="last">状态</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="resource" items="${resources}" varStatus="status">
				<c:set var="permission" value="${resourceMap[resource]}"/>
				<tr class="<c:if test='${status.last}'>last</c:if>">
					<td><a href="${base}/system/permissions/permission/form?permissionId=${permission.id}&siteId=${param.siteId}&categoryId=${categoryId}">${resource.id}</a></td>
					<td>${resource.name}</td>
					<td>${resource.code}</td>
					<td>
						<form class="permissionForm" method="post"
							action="${base}/process/system/permission" >
							<select name="roleId">
								<option value=""></option>
								<c:forEach var="role" items="${roles}">
								<option value="${role.id}" <c:if test="${not empty resourceMap[resource] and resourceMap[resource].role.id eq role.id}">selected="selected"</c:if>>${role.name}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="resourceId" value="${resource.id}"/>
							<input type="hidden" name="categoryId" value="${categoryId}"/>
							<input type="hidden" name="id" value="${permission.id}"/>
						</form>
					</td>
					<td>
					<c:choose>
					<c:when test="${permission.enabled}">启用</c:when>
						<c:otherwise>禁用</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			<c:if test="${not empty resources}">
			<tfoot>
				<tr>
					<td colspan="5">
						<button id="submit${id}" type="submit" class="button">提交</button>
					</td>
				</tr>
			</tfoot>
			</c:if>
		</table>
	</div>
</div>

<script type="text/javascript">
$('#select-category${id}').change(function(){
	window.location.href='?siteId=${param.siteId}&categoryId=' + $(this).val();
});
$('#submit${id}').click(function(){
	$(this).busy({
		img: '${base}/static/images/loading.gif'
	});
	$('.permissionForm').each(function(){
		$(this).ajaxSubmit({
			dataType: 'json',
			forceSync: true,
			beforeSubmit: function(formData, $form){
				var roleId = $.trim(formData[0].value);
				if(roleId=='') {
					return false;
				}
			},
			success: function(){}
		});
	});
	setTimeout(function(){
		window.location.reload();
	}, 500);
});
</script>