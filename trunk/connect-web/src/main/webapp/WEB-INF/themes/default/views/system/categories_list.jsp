<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<c:if test="${not empty parent}">
			<a href="${base}/system/categories?siteId=${param.siteId}&parentId=${parent.parentId}">上一级</a>				
		</c:if>
		<c:choose>
			<c:when test="${empty page.result}">当前无菜单项可显示！</c:when>
			<c:otherwise>
				<table class="tableList">
					<thead>
						<tr>
							<th class="first">ID</th>
							<th>显示名</th>
							<th>编码</th>
							<th>排序值</th>
							<th>站点</th>
							<th>状态</th>
							<th class="last">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="category" items="${page.result}" varStatus="status">
							<tr class="<c:if test='${status.last}'>last</c:if> <c:if test='${status.count%2==0}'>alt</c:if>">
								<td class="first"><a href="${base}/system/categories/category/form?siteId=${param.siteId}&categoryId=${category.id}">${category.id}</a></td>
								<td><a href="${base}/system/categories?siteId=${param.siteId}&parentId=${category.id}">${category.label}</a></td>
								<td>${category.code}</td>
								<td>${category.level}</td>
								<td><a href="${base}/system/categories?siteId=${category.site.id}">${category.site.title}</a></td>
								<td>
								<c:choose>
									<c:when test="${category.enabled}">启用</c:when>
									<c:otherwise>禁用</c:otherwise>
								</c:choose>
								</td>
								<td class="last"><a href="${base}/system/categories/category/form?siteId=${param.siteId}&parentId=${category.id}">添加子分类</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>	
			</c:otherwise>
		</c:choose>
	</div>
</div>