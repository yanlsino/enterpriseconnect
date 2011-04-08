<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="actions">
		<e2:security code="team-member-add" userRequired="true">
			<li class="last">
				<a href="${base}/${project.uniqueId}/team/members/invite">邀请</a>
			</li>
		</e2:security>
		</ul>
	</div>
</div>