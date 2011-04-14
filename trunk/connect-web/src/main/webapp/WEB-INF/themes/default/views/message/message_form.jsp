<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
	<form:form id="message-form${id}" cssClass="message-form" 
		action="${base}/process/message/message" commandName="message">
		<fieldset>
			<div>
				<label for="recipient"><fmt:message key="message.message_form.recipient"/></label>
				<br/>
				<form:input path="to.title" readonly="true" cssClass="text"/>
			</div>
			<div>
				<label for="subject"><fmt:message key="message.message_form.subject"/></label>
				<br/>
				<c:choose>
					<c:when test="${message.reply}">
					<form:input path="subject" value="RE:${message.subject}" cssClass="text"/>
					</c:when>
					<c:otherwise>
					<form:input path="subject" cssClass="text"/>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<label for="content"><fmt:message key="message.message_form.content"/></label>
				<br/>
				<form:textarea path="content" cssClass="text"/>
			</div>
			<div>
				<button class="button" type="submit">
					<span id="status1${id}">
						<fmt:message key="message.message_form.submit"/>
					</span>
					<span id="status2${id}" style="display: none">
						<img src="${base}/static/images/loading.gif"/>正在处理...
					</span>
				</button>
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