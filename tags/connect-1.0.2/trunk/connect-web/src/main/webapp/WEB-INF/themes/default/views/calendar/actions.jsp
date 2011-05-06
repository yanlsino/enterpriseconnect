<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<ul class="actions">
		<u:security code="calendar-event-add" project="${project}" userRequired="true">
			<li class="last">
				<a href="${base}/${project.uniqueId}/calendar/event/form">添加日程</a>
			</li>
		</u:security>
		</ul>
	</div>
</div>