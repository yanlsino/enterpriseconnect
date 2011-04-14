<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<div class="events-list">
			<span class="span-2">
				<div class="calendar">
					<div class="month"><fmt:formatDate value="${start}" pattern="MMM"/></div>
					<div class="date"><fmt:formatDate value="${start}" pattern="d"/></div>
					<div class="day"><fmt:formatDate value="${start}" pattern="E"/></div>
				</div>
				<c:if test="${fn:contains(param.date, '~')}">
				<div class="calendar">
					<div class="month"><fmt:formatDate value="${end}" pattern="MMM"/></div>
					<div class="date"><fmt:formatDate value="${end}" pattern="d"/></div>
					<div class="day"><fmt:formatDate value="${end}" pattern="E"/></div>
				</div>
				</c:if>
			</span>
			<c:choose>
				<c:when test="${empty events}">
					<div class="info">当前无活动可显示！</div>
				</c:when>
				<c:otherwise>
				<ul>
				<c:forEach var="event" items="${events}" varStatus="status">
					<li <c:if test="${status.last}">class="last"</c:if>>
						<span class="right">
							<a href="${base}/${project.uniqueId}/calendar/event/form?eventId=${event.id}">修改</a>
						</span>
						<h4><a href="#">${event.title}</a></h4>
						<dl>
							<dt>时间</dt>
							<dd>
								<fmt:formatDate value="${event.start}" type="both"/> 
								~ 
								<fmt:formatDate value="${event.end}" type="both"/>
							</dd>
							<c:if test="${not empty event.url}">
							<dt>Link</dt>
							<dd>${event.url}</dd>
							</c:if>
						</dl>
					</li>
				</c:forEach>
				</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>