<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<div id="message">
			<c:choose>
				<c:when test="${not empty message.enteredBy.project.profile.logo}">
				<img class="thumbnail" src="${base}/logo/download/${message.enteredBy.project.profile.logo.id}/75x75"/>
				</c:when>
				<c:otherwise>
				<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${message.enteredBy.project.category.code}.png" with="75" height="75"/>
				</c:otherwise>
			</c:choose>
			<div class="content">
				${message.content}
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>