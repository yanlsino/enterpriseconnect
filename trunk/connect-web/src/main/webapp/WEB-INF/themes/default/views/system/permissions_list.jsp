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
		<table class="permissions-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>名称</th>
					<th>编码</th>
					<th>默认角色</th>
					<th>
						<select id="select-category${id}">
						<c:forEach var="category" items="${categories}">
							<option value="${category.id}" <c:if test="${category.id eq param.categoryId}">selected="selected"</c:if>>${category.label}</option>
						</c:forEach>
						</select>
					</th>
					<th class="last">状态</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="permission" items="${permissions}" varStatus="status">
				<tr class="<c:if test='${status.last}'>last</c:if> <c:if test='${status.count%2==0}'>alt</c:if>">
					<td><a href="${base}/system/permissions/permission/form?permissionId=${permission.id}&siteId=${param.siteId}&categoryId=${permission.category.id}">${permission.id}</a></td>
					<td>${permission.resource.name}</td>
					<td>${permission.resource.code}</td>
					<td>${permission.role.name}(${permission.role.code})</td>
					<td>${permission.category.label}</td>
					<td>
					<c:choose>
					<c:when test="${permission.enabled}">启用</c:when>
						<c:otherwise>禁用</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
$('#select-category${id}').change(function(){
	window.location.href='?siteId=${param.siteId}&categoryId=' + $(this).val();
});
</script>