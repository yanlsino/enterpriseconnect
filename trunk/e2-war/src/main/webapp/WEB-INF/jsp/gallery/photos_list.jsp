<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<div class="photos-list">
		<c:forEach var="photo" items="${page.result}">
			<img class="thumbnail" src="${base}/photo/download/${photo.realFile.id}/150x150"/>
		</c:forEach>
		</div>
		<div class="clear"></div>
	</div>
</div>