<%@ page pageEncoding="UTF-8" %>
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
					<th>分类</th>
					<th class="last">状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="template" items="${page.result}" varStatus="status">
					<tr class="<c:if test='${status.last}'>last</c:if> <c:if test='${status.count%2==0}'>alt</c:if>">
						<td class="first"><a href="${base}/system/templates/template/form?templateId=${template.id}&siteId=${param.siteId}&categoryId=${param.categoryId}">${template.id}</a></td>
						<td>${template.name}</td>
						<td>${template.code}</td>
						<td>${template.category.label}</td>
						<td class="last">
						<c:choose>
							<c:when test="${template.enabled}">启用</c:when>
							<c:otherwise>禁用</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>	