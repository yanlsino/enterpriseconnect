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
		<ul id="activities-list${id}" class="activities-list">
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
							<a class="comment-action" id="${activity.id}" href="${base}/process/commons/comments?entity=Activity&linkedId=${activity.id}">
								<fmt:message key="vblog.activities_list.comment"><fmt:param value="${activity.commentCount}"/></fmt:message>
							</a>
						</div>
						<div class="clear"></div>
					</div>
					<ul id="comments-list${activity.id}" class="comments-list">
					</ul>
					<form id="activity-comment-form${activity.id}" class="activity-comment-form" style="display: none;" action="${base}/process/commons/comment" method="post">
					<c:choose>
						<c:when test="${empty user}">
							<div class="notice">添加评论，请先 <a href="${base}/app/login/form" class="loginAction">登录</a></div>
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
			<a id="more-action${id}" href="${base}/process/fragment/${id}">更多</a>
		</div>
		</c:if>
		</c:otherwise>
	</c:choose>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var pageNo = 2;
	$('#more-action${id}').click(function(){
		var self = this;
		var url = $(this).attr('href');
		var params = {'pageNo':pageNo,'uniqueId':'${project.uniqueId}'};
		$.get(url, params, function(html){
			$(html).find('.activities-list').contents().appendTo($('#activities-list${id}'));
			if((pageNo+1)<${page.totalPages}) {
				pageNo++;
			} else {
				$(self).hide();
			}
		});
		return false;
	});	
	
	$('.activity-comment-form').ajaxForm({
		dataType: 'json',
		clearForm: true,
		beforeSubmit: function(formData, $form) {
			var content = $.trim(formData[0].value);
			if(content=='') {
				return false;
			}
		},
		success: function(comment){
			_fillCommentListContainer(comment)
		}
	});

	$('.comment-action').click(function(){
		showCommentList(this);
		showCommentForm(this);
		return false;
	});

	function showCommentForm(link) {
		var id = $(link).attr('id');
		$('#activity-comment-form'+id).show();
	}

	function showCommentList(link) {
		var id = $(link).attr('id');
		if($('#comments-list'+id).find('li').size()==0) {
			var url = $(link).attr('href');
			$.get(url, function(comments){
				for(i in comments) {
					_fillCommentListContainer(comments[i]);	
				}
			});
		}
	}

	function _fillCommentListContainer(comment) {
		var item = '<li>' + 
							'<a href="${base}/'+ comment.enteredBy_project_uniqueId +'/profile">';
		if(comment.enteredBy_project_profile_logo_id!=null) {
			item += '<img class="top left thumbnail" src="${base}/logo/download/'+ comment.enteredBy_project_profile_logo_id +'/35x35"/>';
		} else {
			item += '<img class="top left thumbnail" src="${base}/themes/${theme.name}/stock/'+ comment.enteredBy_project_category_code +'.png" width="35" height="35"/>';
		}
		item +=     '</a>'; 
		item += 	'<p class="comment-content">'+comment.content+'</p>' + 
					'<span class="top right">'+comment.entered_pretty+'</span>' +
					'<br class="clear"/>' +
					'</li>';
		$('#comments-list'+comment.linkedId).append(item);
	}
});
</script>