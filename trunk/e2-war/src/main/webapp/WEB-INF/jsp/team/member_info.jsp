<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="members-list">
		<c:forEach var="member" items="${needApprove}" varStatus="status">
			<li>
				<a href="${base}/${member.user.project.uniqueId}/profile">
				<c:choose>
					<c:when test="${not empty member.user.project.profile.logo}">
					<img class="thumbnail" src="${base}/logo/download/${member.user.project.profile.logo.id}/35x35"/>
					</c:when>
					<c:otherwise>
					<img class="thumbnail"src="${base}/themes/${theme.name}/stock/${member.user.project.category.code}.png" with="35" height="35"/>
					</c:otherwise>
				</c:choose>	
				</a>
				<div class="desc">
					<div>
						<a target="_blank" href="${base}/${member.user.project.uniqueId}/profile">
							${member.user.project.title}
						</a>
						${member.user.project.profile.shortDescription}
					</div>
					<div>
						<a id="approveMemberAction" href="${base}/process/team/approve?memberId=${member.id}">
						<fmt:message key="team.member_info.approve"/>
						</a>
						|
						<a href="#">
						<fmt:message key="team.member_info.disapprove"/>
						</a>
					</div>
					<div class="clear"></div>
				</div>
			</li>
		</c:forEach>
		<c:forEach var="member" items="${needAccept}" varStatus="status">
			<li>
				<a href="${base}/${member.user.project.uniqueId}/profile">
				<c:choose>
					<c:when test="${not empty member.project.profile.logo}">
					<img class="thumbnail" src="${base}/logo/download/${member.project.profile.logo.id}/35x35"/>
					</c:when>
					<c:otherwise>
					<img class="thumbnail"src="${base}/themes/${theme.name}/stock/${member.project.category.code}.png" with="35" height="35"/>
					</c:otherwise>
				</c:choose>	
				</a>
				<div class="desc">
					<div>
						<a target="_blank" href="${base}/${member.project.uniqueId}/profile">
							${member.project.title}
						</a>
						${member.project.profile.shortDescription}
					</div>
					<div>
						<a id="approveMemberAction" href="${base}/process/team/approve?memberId=${member.id}">
						<fmt:message key="team.member_info.accept"/>
						</a>
						|
						<a href="#">
						<fmt:message key="team.member_info.refuse"/>
						</a>
					</div>
					<div class="clear"></div>
				</div>
			</li>
		</c:forEach>
		</ul>
		<div class="clear"></div>
	</div>
</div>
<script type="text/javascript">
(function(){
	$('#approveMemberAction').click(function(){
		var url = $(this).attr('href');
		$.get(url, function(id){
			window.location.reload();
		});
		return false;
	});
})();
</script>