<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

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
								<e2:vBlogRender text="${activity.description}" facesBase="${base}/static/images/faces"/>
							</c:when>
							<c:otherwise>${activity.description}</c:otherwise>
						</c:choose>
						<div class="right">
							<e2:prettyTime date="${activity.entered}"/>
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
YUI().use('io-form', 'json', 'node', function(Y){
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


<%-- 
<script type="text/javascript">
$(document).ready(function(){
	var pageNo = 2;
	var totalPages = ${page.totalPages};
	// for activity comment
	$('#${fragmentConfig.id} .activityCommentAction').click(function(){
		var id = $(this).attr('href').substring(1);
		$('#${fragmentConfig.id} #activityCommentForm'+id).show();
		showComments(id);
		return false;
	});
	
	$('#${fragmentConfig.id} .more').click(function(){
		self = this;
		if(pageNo<=totalPages) {
			var url = '${base}/process/fragment/${fragmentConfig.id}';
			var params = {
				pageNo:pageNo,
				uniqueId:"${project.uniqueId}"
			};
			$.get(url,params,function(view){
				$('#${fragmentConfig.id} .activities-list').append(
						$(view).find('.activities-list').contents());
				pageNo++;
				if(pageNo>totalPages) {
					$(self).hide();
				}
			});
		} 
		return false;
	});
	
	$('#${fragmentConfig.id} .cancel').click(function(){
		$(this).parent().parent().hide();
		return false;
	});
	
	$('#${fragmentConfig.id} .activityCommentForm').submit(function(){
		var content = $(this).find('textarea').val();
		self = this;
		if($.trim(content)!='') {
			$(this).ajaxSubmit({
				dataType:'json',
				clearForm:true,
				success:function(comment){
					showComments(comment.linkedId);
				}
			});
		} else {
			$(this).block({ 
	            message: '<h3>评论内容不能为空！</h3>',
	            overlayCSS: { backgroundColor: '#EEE' },
	            timeout: 1500
	        });
		}
		return false;
	});
	
	function showComments(id) {
		var container = $('#${fragmentConfig.id} #commentContainer'+id);
		// fetch comments and fill comment list
		var url = '${base}/process/commons/comments';
		var params = {linkedId:id,entity:'Activity'};
		$.get(url,params,function(view){
			container.html(view);
		});
	}
});
</script>
--%>