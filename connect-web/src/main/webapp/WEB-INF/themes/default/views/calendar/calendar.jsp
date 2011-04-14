<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>
	</c:if>	
	<div>
		<table class="calendar">
			<tbody>
				<tr class="first">
					<td class="first"></td>
					<td>周日</td>
					<td>周一</td>
					<td>周二</td>
					<td>周三</td>
					<td>周四</td>
					<td>周五</td>
					<td>周六</td>
				</tr>
				<c:forEach var="week" items="${month}" varStatus="status">
				<tr>
					<td class="first week">
						<a href="#" title='<fmt:formatDate value="${week[0]}" pattern="yyyy/M/d"/>~<fmt:formatDate value="${week[6]}" pattern="yyyy/M/d"/>'>
							<img src="${base}/themes/${theme.name}/icons/forward.png">
						</a>
					</td>
					<c:forEach var="day" items="${week}">
					<c:set var="dateStr"><fmt:formatDate value="${date}" pattern="yyyy/M/d"/></c:set>
					<c:set var="dayStr"><fmt:formatDate value="${day}" pattern="yyyy/M/d"/></c:set>
					<c:set var="startEndStr"><fmt:formatDate value="${week[0]}" pattern="yyyy/M/d"/>~<fmt:formatDate value="${week[6]}" pattern="yyyy/M/d"/></c:set>
					<td class='day <c:if test="${dateStr==dayStr or param.date==startEndStr}">active</c:if>'>
						<a href="#" id="" title='<fmt:formatDate value="${day}" pattern="yyyy/M/d"/>'>
							<fmt:formatDate value="${day}" pattern="d"/>
						</a>
					</td>
					</c:forEach>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
YUI().use('node', function(Y){
	Y.all('table.calendar .day a').on('click', function(e){
		window.location.href="?date=" + e.currentTarget.get('title');
		e.halt();
	});
	Y.all('table.calendar .week a').on('click', function(e){
		window.location.href="?date=" + e.currentTarget.get('title');
		e.halt();
	});
});
</script>