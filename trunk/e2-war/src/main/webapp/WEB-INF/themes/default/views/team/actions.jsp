<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<ul class="actions">
		<e2:security code="team-member-add" userRequired="true">
			<li class="last">
				<a href="${base}/${project.uniqueId}/team/members/invite">邀请</a>
			</li>
		</e2:security>
		</ul>
	</div>
</div>