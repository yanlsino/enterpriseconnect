<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="events-list">
			<c:forEach var="event" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<a href="${base}/${event.project.uniqueId}/calendar/event/${event.id}" 
				title="${event.title}">
					${event.title} - <fmt:formatDate value="${event.start}" pattern="yyyy/M/d"/>
				</a>
			</li>
			</c:forEach>
		</ul>
	</div>
</div>