<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<div id="calendar"></div>
	</div>
</div>

<c:set var="editable">
	<e2:security code="calendar-event-edit">true</e2:security>
</c:set>

<script type="text/javascript">
$(document).ready(function(){
	var today = new Date();
	$('#calendar').fullCalendar({
		theme: true,
		header: {
			left: 'month, agendaWeek, agendaDay',
			center: 'title',
			right: 'prev, next, today'
		},
		editable: ${editable},
		events: '${base}/process/calendar/events?uniqueId=${project.uniqueId}',
		dayClick: function(date, allDay, jsEvent, view){
			//if(date.getTime() > today.getTime()) {
				var url='${base}/${project.uniqueId}/calendar/event/form?date=' + $.fullCalendar.formatDate(date,'yyyy-MM-dd');
				// redirect
				window.location.href = url;
			//}
		},
		eventClick: function(event, jsEvent, view){
			var url='${base}/${project.uniqueId}/calendar/event/form?eventId='+event.id;
			// redirect
			window.location.href = url;
		},
		eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {
			var url = '${base}/process/calendar/event';
			//alert($.fullCalendar.formatDate(event.start,'yyyy-MM-dd HH:mm:ss'));
			var params = {
				'id':event.id,
				'start':$.fullCalendar.formatDate(event.start,'yyyy-MM-dd HH:mm'),
				'end':$.fullCalendar.formatDate(event.end,'yyyy-MM-dd HH:mm'),
				'allDay':allDay
			};
			$.get(url, params, function(e){
				var eventId = e.id;
			});
		}
	});
});
</script>