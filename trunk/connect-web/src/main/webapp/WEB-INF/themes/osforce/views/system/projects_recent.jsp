<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="projects-recent">
		<c:forEach var="project" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<a href="${base}/${project.uniqueId}/profile">
					<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${project.category.code}.png" with="40" height="40"/>
				</a>
				<div class="project-body">
					<a href="${base}/${project.uniqueId}/profile">${project.title}</a>
					<p>${project.profile.shortDescription}</p>
				</div>
				<div class="clear"></div>
			</li>
		</c:forEach>
		</ul>
	</div>
</div>