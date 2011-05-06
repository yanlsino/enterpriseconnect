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
					<th>用户名</th>
					<th>昵称</th>
					<th>域名</th>
					<th class="last">状态</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="user" items="${page.result}" varStatus="status">
				<tr class="<c:if test='${status.last}'>last</c:if> <c:if test='${status.count%2==0}'>alt</c:if>">
					<td class="first">${user.id}</td>
					<td>${user.username}</td>
					<td>
						<a href="${base}/${user.project.uniqueId}/profile" target="_blank">${user.nickname}</a>
					</td>
					<td>${user.project.category.site.domain}</td>
					<td class="last">
					<c:choose>
						<c:when test="${user.enabled}">激活</c:when>
						<c:otherwise>未激活</c:otherwise>	
					</c:choose>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>	
	</div>
</div>