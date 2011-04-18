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
$(document).ready(function(){
	$('#event-form${id}').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSerialize: function(){
			var start = getStartDate();
			var end = getEndDate();
			$('#start${id}').val(start);
			$('#end${id}').val(end);
		},
		beforeSubmit: function(formData, $form){
			var title = $.trim($('#title${id}').val());
			var startDate = $.trim($('#start-date${id}').val());
			var endDate = $.trim($('#end-date${id}').val());
			if(title=='' || startDate=='' || endDate=='') {
				return false
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(event){
			setTimeout(function(){
				window.location.href='?eventId=' + event.id;				
			}, 500);
		}
	});
	
	function getStartDate() {
		var dateStr = $('#start-date${id}').val();
		var hourStr = $('#start-hour${id}').val();
		var minuteStr = $('#start-minute${id}').val();
		return dateStr + ' ' + hourStr + ':' + minuteStr;
	}
	
	function getEndDate() {
		var dateStr = $('#end-date${id}').val();
		var hourStr = $('#end-hour${id}').val();
		var minuteStr = $('#end-minute${id}').val();
		return dateStr + ' ' + hourStr + ':' + minuteStr;
	}

	Date.format = 'yyyy-m-d';
	$('#start-date${id}').datePicker();
	$('#end-date${id}').datePicker();
});
</script>