<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="profiles-list">
		<c:forEach var="profile" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<a href="${base}/${profile.project.uniqueId}/profile">
					<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${profile.project.category.code}.png" with="40" height="40"/>
				</a>
				<div class="profile-body">
					<div class="profile-content">
						<ul class="float-right">
							<e2:entity name="Profile" user="${user}" project="${profile.project}" exist="false">
							<li><a class="concernAction" href="${base}/process/commons/link?fromId=${user.project.id}&toId=${profile.id}&entity=Profile">
								<fmt:message key="search.search_result.addConcern"/>
							</a></li>
							</e2:entity>
							<e2:security code="message-message-add" userRequired="true">
							<li><a id="${profile.project.uniqueId}" class="leaveMessageAction" href="${base}/app/message/form?popup=true&fromId=${user.project.id}&toId=${profile.project.id}">
								<fmt:message key="search.search_result.sendMessage"/>
							</a></li>
							</e2:security>
						</ul>
						<a href="${base}/${profile.project.uniqueId}/profile">${profile.title}</a>
						<p>${profile.shortDescription}</p>
					</div>
				</div>
				<div class="clear"></div>
			</li>
		</c:forEach>
		</ul>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('.concernAction').click(function(){
		var url = $(this).attr('href');
		$.get(url, function(link){
			window.location.reload();
		});
		return false;
	});
	$('.leaveMessageAction').click(function(){
		var url = $(this).attr('href');
		$.fn.nyroModalManual({
			bgColor: '#DDD',
			url:url
		});
		return false;
	});
});
</script>