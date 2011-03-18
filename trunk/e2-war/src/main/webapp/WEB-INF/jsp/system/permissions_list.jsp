<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<table class="tableList">
			<thead>
				<tr>
					<th class="first">ID</th>
					<th>名称</th>
					<th>编码</th>
					<th>默认角色</th>
					<th>分类</th>
					<th class="last">状态</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="permission" items="${page.result}" varStatus="status">
				<tr class="<c:if test='${status.last}'>last</c:if> <c:if test='${status.count%2==0}'>alt</c:if>">
					<td class="first"><a href="${base}/system/permissions/permission/form?permissionId=${permission.id}&siteId=${param.siteId}&categoryId=${param.categoryId}">${permission.id}</a></td>
					<td>${permission.resource.name}</td>
					<td>${permission.resource.code}</td>
					<td>${permission.role.name}(${permission.role.code})</td>
					<td>${permission.category.label}</td>
					<td class="last">
					<c:choose>
					<c:when test="${permission.enabled}">启用</c:when>
						<c:otherwise>禁用</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<c:if test="${page.totalPages > 1}">
		<e2:pagination link="?pageNo=" page="${page}"/>
		</c:if>
	</div>
</div>