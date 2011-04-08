<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
	<form:form id="messageForm" action="${base}/process/message/message" commandName="message">
		<fieldset>
			<div>
				<label for="recipient"><fmt:message key="message.message_form.recipient"/></label>
				<form:input path="to.title" readonly="true"/>
			</div>
			<div>
				<label for="subject"><fmt:message key="message.message_form.subject"/></label>
				<c:set var="subjectRequired"><fmt:message key='message.message_form.subjectRequired'/></c:set>
				<c:choose>
					<c:when test="${message.reply}">
					<form:input path="subject" value="RE:${message.subject}" cssClass="{validate:{required:true, messages:{required:'${subjectRequired}'}}}"/>
					</c:when>
					<c:otherwise>
					<form:input path="subject" cssClass="{validate:{required:true, messages:{required:'${subjectRequired}'}}}"/>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<label for="content"><fmt:message key="message.message_form.content"/></label>
				<c:set var="contentRequired"><fmt:message key='message.message_form.contentRequired'/></c:set>
				<form:textarea path="content" cssClass="{validate:{required:true, messages:{required:'${contentRequired}'}}}"/>
			</div>
			<div>
				<input class="button" type="submit" value='<fmt:message key="message.message_form.submit"/>'>
				<form:hidden path="id"/>
				<form:hidden path="fromId"/>
				<form:hidden path="toId"/>
				<form:hidden path="enteredId"/>
				<c:if test="${not empty message.entered}">
				<input type="hidden" name="entered" value='<fmt:formatDate value="${message.entered}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
				</c:if>
			</div>
		</fieldset>
	</form:form>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#messageForm').validate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(message){
					window.location.href='?messageId='+message.id;
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>