<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="event-form${id}" class="event-form" 
			action="${base}/process/calendar/event" commandName="event">
			<div>
				<label for="title" class="title">
					<fmt:message key="calendar.event_form.title"/>
					<span class="required">*</span>
				</label>
				<br/>
				<form:input path="title" id="title${id}" cssClass="text "/>
			</div>
			<div>
				<label for="start">
					<fmt:message key="calendar.event_form.startDate"/>
					<span class="required">*</span>
				</label>
				<br/>
				<input id="start-date${id}" readonly="readonly" class="calendar"
				value='<fmt:formatDate value="${event.start}" pattern="yyyy-MM-dd"/>'>
				<select id="start-hour${id}" class="hours">
				<c:set var="startHour"><fmt:formatDate value="${event.start}" pattern="HH"/></c:set>
				<c:forTokens var="hour" items="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23" delims=",">
					<option <c:if test="${startHour eq hour}">selected="selected"</c:if> >${hour}</option>
				</c:forTokens>
				</select>
				:
				<select id="start-minute${id}" class="minutes">
				<c:set var="startMinute"><fmt:formatDate value="${event.start}" pattern="m"/></c:set>
				<c:forTokens var="minute" items="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59" delims=",">
					<option <c:if test="${startMinute eq minute}">selected="selected"</c:if> >${minute}</option>
				</c:forTokens>
				</select>
			</div>
			<div>
				<label for="end">
					<fmt:message key="calendar.event_form.endDate"/>
					<span class="required">*</span>
				</label>
				<br/>
				<input id="end-date${id}" readonly="readonly" class="calendar"
				value='<fmt:formatDate value="${event.end}" pattern="yyyy-MM-dd"/>'>
				<select id="end-hour${id}" class="hours">
				<c:set var="endHour"><fmt:formatDate value="${event.end}" pattern="HH"/></c:set>
				<c:forTokens var="hour" items="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23" delims=",">
					<option <c:if test="${endHour eq hour}">selected="selected"</c:if> >${hour}</option>
				</c:forTokens>
				</select>
				:
				<select id="end-minute${id}" class="minutes">
				<c:set var="endMinute"><fmt:formatDate value="${event.end}" pattern="m"/></c:set>
				<c:forTokens var="minute" items="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59" delims=",">
					<option <c:if test="${endMinute eq minute}">selected="selected"</c:if> >${minute}</option>
				</c:forTokens>				
				</select>
			</div>
			<div>
				<label for="url"><fmt:message key="calendar.event_form.url"/></label>
				<br/>
				<form:input path="url" cssClass="text "/>
			</div>
			<div>
				<label for="enabled"><fmt:message key="calendar.event_form.enabled"/></label>
				<br/>
				<form:checkbox path="enabled"/>
			</div>
			<div>
				<button type="submit" class="button">
					<span id="status1${id}">
						<fmt:message key="calendar.event_form.submit"/>
					</span>
					<span id="status2${id}" style="display: none">
						<img src="${base}/static/images/loading.gif"/>正在处理...
					</span>
				</button>
				<form:hidden path="id"/>
				<form:hidden path="projectId"/>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="start" id="start${id}"/>
				<form:hidden path="end" id="end${id}"/>
				<input type="hidden" name="entered" value="<fmt:formatDate value="${event.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</div>
		</form:form>
		
		<div id="overlay${id}">
	   		<div id="calendar${id}"></div>
	   	</div>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var eventForm = Y.one('#event-form${id}');
	eventForm.on('submit', function(e){
		Y.one('#start${id}').set('value', getStartDate());
		Y.one('#end${id}').set('value', getEndDate());
		//
		var startDate = Y.one('#start-date${id}').get('value');
		var endDate = Y.one('#end-date${id}').get('value');
		if(startDate.trim()!='' && endDate.trim()!='') {
			Y.one('#status1${id}').hide();
			Y.one('#status2${id}').show();
			//
			Y.on('io:complete', function(id, o){
				try {
					var event = Y.JSON.parse(o.responseText);
					setTimeout('window.location.href="?eventId='+event.id+'"', 500);
				} catch(e) {
					// TODO alert message username or password invalid
				}
			});
			Y.io(eventForm.get('action'), {
				method: 'POST',
				form: {
					id: eventForm
				}
			});
		}
		e.halt();
	});
	
	function getStartDate() {
		var dateStr = Y.one('#start-date${id}').get('value');
		var hourStr = Y.one('#start-hour${id}').get('value');
		var minuteStr = Y.one('#start-minute${id}').get('value');
		return dateStr + ' ' + hourStr + ':' + minuteStr;
	}
	
	function getEndDate() {
		var dateStr = Y.one('#end-date${id}').get('value');
		var hourStr = Y.one('#end-hour${id}').get('value');
		var minuteStr = Y.one('#end-minute${id}').get('value');
		return dateStr + ' ' + hourStr + ':' + minuteStr;
	}
});
</script>
<%-- 
<script type="text/javascript">
YUI().use('node', function(Y){
	Y.all('.hours').each(function(){
		for(i=0; i<24;i++) {
			this.insert('<option>'+i+'</option>'); 
		}
	});
	Y.all('.minutes').each(function(){
		for(i=0; i<60;i++) {
			this.insert('<option>'+i+'</option>');
		}
	});
});
</script>
--%>

<script type="text/javascript">
YUI().use('node', 'yui2-calendar', 'overlay', function(Y) {
	var YAHOO = Y.YUI2;
	//
	var overlay = new Y.Overlay({
        srcNode:"#overlay${id}",
        visible:false
    });
	
	overlay.render();
	
	Y.all('input.calendar').on('click', function(e){
		var cal = new YAHOO.widget.Calendar('calendar', 'calendar${id}', {
			close: false
		});
		cal.selectEvent.subscribe(function(d){
			var dateArray = cal.getSelectedDates();
			for(i in dateArray) {
				var d = dateArray[i];
				var dateStr = d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate();
				e.currentTarget.set('value', dateStr);
				overlay.hide();
			}
		});
		cal.render();
		//
		overlay.set("align", {node: e.currentTarget,
    		points: [Y.WidgetPositionAlign.TL, Y.WidgetPositionAlign.TR]});
		overlay.show();
	});
});
</script>

<%--
<script type="text/javascript">
$(document).ready(function(){
	$('#eventForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '<div class="notice">正在处理...<div>',
				overlayCSS: { backgroundColor: '#F6F6F6' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(event){
					setTimeout('window.location.href="?eventId='+event.id+'"', 500);
				}
			});
			return false;	
		},
		meta: "validate"
	});
});
</script>
--%>