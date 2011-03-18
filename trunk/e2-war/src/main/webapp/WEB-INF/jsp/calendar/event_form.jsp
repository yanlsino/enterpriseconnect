<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="eventForm" action="${base}/process/calendar/event" commandName="event">
			<fieldset>
				<div>
					<label for="title"><fmt:message key="calendar.event_form.title"/></label>
					<form:input path="title" cssClass="{validate:{required:true, messages:{required:'标题不能为空！'}}}"/>
				</div>
				<div>
					<label for="start"><fmt:message key="calendar.event_form.startDate"/></label>
					<input id="start" name="start" readonly="readonly" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
					value='<fmt:formatDate value="${event.start}" pattern="yyyy-MM-dd HH:mm"/>' class="{validate:{required:true, messages:{required:'开始日期不能为空！'}}}">
					<img onclick="WdatePicker({el:'start', dateFmt:'yyyy-MM-dd HH:mm'})" 
					src="${base}/components/datepicker/skin/datePicker.gif" 
					style="border:0"width="16" height="22" align="absmiddle">
				</div>
				<div>
					<label for="end"><fmt:message key="calendar.event_form.endDate"/></label>
					<input id="end" name="end" readonly="readonly"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" 
					value='<fmt:formatDate value="${event.end}" pattern="yyyy-MM-dd HH:mm"/>' class="{validate:{required:true, messages:{required:'结束日期不能为空！'}}}">
					<img onclick="WdatePicker({el:'end', dateFmt:'yyyy-MM-dd HH:mm'})" 
					src="${base}/components/datepicker/skin/datePicker.gif" 
					style="border:0"width="16" height="22" align="absmiddle"> 
				</div>
				<div>
					<label for="url"><fmt:message key="calendar.event_form.url"/></label>
					<form:input path="url" cssClass="{validate:{url:true, messages:{url:'请输入正确的URL！'}}}"/>
				</div>
				<div>
					<label for="enabled"><fmt:message key="calendar.event_form.enabled"/></label>
					<form:checkbox path="enabled"/>
				</div>
				<div>
					<input type="submit" value='<fmt:message key="calendar.event_form.submit"/>'/>
					<form:hidden path="id"/>
					<form:hidden path="projectId"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<input type="hidden" name="entered" value="<fmt:formatDate value="${event.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#eventForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#F6F6F6' }
			});
			$(form).ajaxSubmit({
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