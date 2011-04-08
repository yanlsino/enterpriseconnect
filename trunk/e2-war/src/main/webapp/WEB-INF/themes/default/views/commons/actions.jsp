<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<ul class="actions">
			<c:if test="${not empty fragmentConfig.preferences['projectAdd']}">
			<li>
				<a href="${base}/${fragmentConfig.preferences['categoryCode']}/form">
					${fragmentConfig.preferences['projectAdd']}
				</a>
			</li>
			</c:if>
			<c:if test="${not empty fragmentConfig.preferences['projectConcerned']}">
			<li>
				<a href="${base}/${fragmentConfig.preferences['categoryCode']}/concerned">
					${fragmentConfig.preferences['projectConcerned']}
				</a>
			</li>
			</c:if>
			<c:if test="${not empty fragmentConfig.preferences['projectAll']}">
			<li class="last">
				<a href="${base}/${fragmentConfig.preferences['categoryCode']}/all">
					${fragmentConfig.preferences['projectAll']}
				</a>
			</li>
			</c:if>
		</ul>
	</div>
</div>