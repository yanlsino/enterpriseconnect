<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<fmt:message key="message.message_info.infomation">
			<fmt:param value="${base}"/>
			<fmt:param value="${project.uniqueId}"/>
			<fmt:param value="${unreadCount}"/>
		</fmt:message>
	</div>
</div>