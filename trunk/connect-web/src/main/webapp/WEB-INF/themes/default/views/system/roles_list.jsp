<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<table class="tableList">
			<thead>
				<tr>
					<th class="first">ID</th>
					<th>角色名</th>
					<th>编码</th>
					<th>分类</th>
					<th>级别</th>
					<th>站点</th>
					<th class="last">状态</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="role" items="${page.result}" varStatus="status">
				<tr class="<c:if test='${status.last}'>last</c:if> <c:if test='${status.count%2==0}'>alt</c:if>">
					<td class="first"><a href="${base}/system/roles/role/form?roleId=${role.id}&siteId=${param.siteId}&categoryId=${param.categoryId}">${role.id}</a></td>
					<td>${role.name}</td>
					<td>${role.code}</td>
					<td>${role.category.label}</td>
					<td>${role.level}</td>
					<td>${role.category.site.domain}</td>
					<td class="last">
					<c:choose>
						<c:when test="${role.enabled}">激活</c:when>
						<c:otherwise>未激活</c:otherwise>		
					</c:choose>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>	
	</div>
</div>