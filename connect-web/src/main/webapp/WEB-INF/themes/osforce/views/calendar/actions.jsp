<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="actions">
		<e2:security code="calendar-event-add">
			<li class="last">
				<a href="${base}/${project.uniqueId}/calendar/event/form">添加日程</a>
			</li>
		</ul>
		</e2:security>
	</div>
</div>