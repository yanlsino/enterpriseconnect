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
					<th>主题名</th>
					<th class="last">状态</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="theme" items="${page.result}" varStatus="status">
				<tr class="<c:if test='${status.last}'>last</c:if> <c:if test='${status.count%2==0}'>alt</c:if>">
					<td class="first"><a href="${base}/system/themes/theme/form?themeId=${theme.id}">${theme.id}</a></td>
					<td>${theme.name}</td>
					<td class="last">
					<c:choose>
						<c:when test="${theme.enabled}">启用</c:when>
						<c:otherwise>禁用</c:otherwise>		
					</c:choose>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>