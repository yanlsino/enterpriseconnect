<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
	<c:choose>
		<c:when test="${not empty siteLinksMap}">
		<ul>
		<c:forEach var="categoryCode" items="${categoryCodes}">
			<c:forEach var="siteLink" items="${siteLinksMap[categoryCode]}">
			<li><a href="${siteLink.href}">${siteLink.text}</a></li>
			</c:forEach>
		</c:forEach>
		</ul>
		</c:when>
		<c:otherwise>
		<table class="tableList">
			<thead>
				<tr>
					<th class="first">ID</th>
					<th>显示名</th>
					<th>分类编码</th>
					<th class="last">URL</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="siteLink" items="${siteLinks}" varStatus="status">
				<tr>
					<td class="first"><a href="${base}/system/links/link/form?siteId=${param.siteId}&linkId=${siteLink.id}">${siteLink.id}</a></td>
					<td>${siteLink.text}</td>
					<td>${siteLink.code}</td>
					<td class="last"><a href="${siteLink.href}" target="_blank">${siteLink.href}</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:otherwise>
	</c:choose>
	</div>
</div>