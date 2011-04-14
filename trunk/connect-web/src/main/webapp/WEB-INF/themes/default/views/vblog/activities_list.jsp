<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
	<c:choose>
		<c:when test="${empty page.result}">
		<div class="info"><fmt:message key="vblog.activities_list.noActivityToDisplay"/></div>
		</c:when>
		<c:otherwise>	
		<ul class="activities-list">
			<c:forEach var="activity" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<a href="${base}/${activity.enteredBy.project.uniqueId}/profile">
				<c:choose>
					<c:when test="${not empty activity.enteredBy.project.profile.logo}">
					<img class="top left thumbnail" src="${base}/logo/download/${activity.enteredBy.project.profile.logo.id}/40x40"/>
					</c:when>
					<c:otherwise>
					<img class="top left thumbnail" src="${base}/themes/${theme.name}/stock/${activity.enteredBy.project.category.code}.png" width="40" height="40"/>
					</c:otherwise>
				</c:choose>
				</a>
				<div class="activity-body">
					<div>
						<c:choose>
							<c:when test="${activity.type eq 'site-chat' or activity.type eq 'user-input'}">
								<u:vBlogRender text="${activity.description}" facesBase="${base}/static/images/faces"/>
							</c:when>
							<c:otherwise>${activity.description}</c:otherwise>
						</c:choose>
						<div class="right">
							<u:prettyTime date="${activity.entered}"/>
							<a class="comment-action" id="${activity.id}">
								<fmt:message key="vblog.activities_list.comment"><fmt:param value="${activity.commentCount}"/></fmt:message>
							</a>
						</div>
						<div class="clear"></div>
					</div>
					<div id="comments-list${activity.id}">
					<%-- replace use ajax load 
					<c:forEach var="comment" items="${activity.comments}" varStatus="status">
						<li>
							<a href="${base}/${comment.enteredBy.project.uniqueId}/profile">
							<c:choose>
								<c:when test="${not empty comment.enteredBy.project.profile.logo}">
								<img class="thumbnail" src="${base}/logo/download/${comment.enteredBy.project.profile.logo.id}/35x35"/>
								</c:when>
								<c:otherwise>
								<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${comment.enteredBy.project.category.code}.png" width="35" height="35"/>
								</c:otherwise>
							</c:choose>
							</a>
							<div class="comment-content">${comment.content}</div>
							<span class="float-right"><e2:prettyTime date="${comment.entered}"/></span>
							<div class="clear"></div>
						</li>
					</c:forEach>
					--%>
					</div>
					<form id="activity-comment-form${activity.id}" class="activity-comment-form" style="display: none;" action="${base}/process/commons/comment" method="post">
					<c:choose>
						<c:when test="${empty user}">
							<div class="notice">添加评论，请先 <a href="${base}/login">登录</a></div>
						</c:when>
						<c:otherwise>
							<div>
								<textarea name="content"></textarea>
							</div>
							<div class="last">
								<!-- 
								<span class="float-right"><fmt:message key="vblog.activities_list.maxCharacters"/></span>
								-->
								<button class="button" type="submit"><fmt:message key="vblog.activities_list.submit"/></button>
								<button id="${activity.id}" class="button cancel-action" type="reset"><fmt:message key="vblog.activities_list.cancel"/></button>
								<input type="hidden" name="linkedId" value="${activity.id}"/>
								<input type="hidden" name="enteredId" value="${user.id}"/>
								<input type="hidden" name="modifiedId" value="${user.id}"/>
								<input type="hidden" name="entity" value="Activity">
								<br class="clear"/>
							</div>
						</c:otherwise>
					</c:choose>
					</form>
				</div>
			</li>
			</c:forEach>
		</ul>
		<c:if test="${page.totalPages gt 1}">
		<div style="text-align: center">
			<a href="#" class="more-action${id}">更多</a>
		</div>
		</c:if>
		</c:otherwise>
	</c:choose>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var pageNo = 1;
	var totalPages = ${page.totalPages};
	var moreAction = Y.one('.more-action${id}');
	if(moreAction!=null) {
		moreAction.on('click', function(e){
			if(++pageNo<=totalPages) {
				Y.on('io:complete', function(id, o){
					Y.one('.activities-list').append(
							Y.Node.create(o.responseText).one('.activities-list'));
				});
				Y.io('${base}/process/fragment/${id}?uniqueId=${project.uniqueId}&pageNo='+pageNo);
			} else {
				moreAction.setStyle('display', 'none');
			}
			e.halt();
		});
	}
	Y.all('.comment-action').on('click', function(e){
		var activityId = e.target.get('id');
		showCommentForm(activityId);
		showCommentList(activityId);
	});
	Y.all('.cancel-action').on('click', function(e){
		var activityId = e.target.get('id');
		hideCommentForm(activityId);
	});
	Y.all('.activity-comment-form').on('submit', function(e){
		var commentForm = e.currentTarget;
		Y.io.header('Content-Type', 'application/json');
		Y.on('io:complete', function(id, o){
			//
		});
		Y.io(commentForm.get('action'), {
			method: 'POST',
			form: {
				id: commentForm
			}
		});
		window.location.reload();
		e.halt();
	});
	function showCommentForm(activityId) {
		Y.one('#activity-comment-form'+activityId).setStyle('display', 'block');
	}
	function hideCommentForm(activityId) {
		Y.one('#activity-comment-form'+activityId).setStyle('display', 'none');
	}
	function showCommentList(activityId) {
		Y.on('io:complete', function(id, o){
			var commentList = Y.JSON.parse(o.responseText);
			for(i in commentList) {
				var comment = commentList[i];
				_fillCommentListContainer(comment);
			}
		});
		Y.io('${base}/process/commons/comments?entity=Activity&linkedId='+activityId);
	}
	function _fillCommentListContainer(comment) {
		var container = Y.one('#comments-list'+comment.linkedId);
		var html = '<ul class="comments-list">' +
						'<li>' + 
							'<a href="${base}/'+ comment.enteredBy_project_uniqueId +'/profile">';
		if(comment.enteredBy_project_profile_logo_id!=null) {
			html += '<img class="top left thumbnail" src="${base}/logo/download/'+ comment.enteredBy_project_profile_logo_id +'/35x35"/>';
		} else {
			html += '<img class="top left thumbnail" src="${base}/themes/${theme.name}/stock/'+ comment.enteredBy_project_category_code +'.png" width="35" height="35"/>';
		}
		html +=     '</a>'; 
		html += 	'<p class="comment-content">'+comment.content+'</p>' + 
					'<span class="top right">'+comment.entered_pretty+'</span>' +
					'<br class="clear"/>' +
					'</li>' +
				'</ul>'
		//container.append(html);
		container.set('innerHTML', html);
	}
});
</script>