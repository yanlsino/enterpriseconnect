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
					<th>标题</th>
					<th>首页</th>
					<th>主题</th>
					<th>状态</th>
					<!--
					<th class="last">操作</th>
					 -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="site" items="${page.result}" varStatus="status">
					<tr class="<c:if test='${status.last}'>last</c:if> <c:if test='${status.count%2==0}'>alt</c:if>">
						<td class="first"><a href="${base}/system/sites/site/form?siteId=${site.id}">${site.id}</a></td>
						<td><a href="${base}/system/sites?siteId=${site.id}">${site.title}</a></td>
						<td><a href="${site.homeURL}" target="_blank">${site.homeURL}</a></td>
						<td>
							<c:if test="${not empty site.theme}">${site.theme.name}</c:if>
							<c:if test="${empty site.theme}">default</c:if>
						</td>
						<td class="last">
						<c:choose>
							<c:when test="${site.enabled}">启用</c:when>
							<c:otherwise>禁用</c:otherwise>
						</c:choose>
						</td>
						<!--
						<td>
							<a href="${base}/process/system/site/backup?siteId=${site.id}">backup</a>
							|
							<a href="${base}/process/system/site/restore?siteId=${site.id}">restore</a>
						</td>
						 -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>