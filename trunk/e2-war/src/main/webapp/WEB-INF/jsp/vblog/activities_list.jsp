<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
	<c:choose>
		<c:when test="${empty page.result}">
		<p><fmt:message key="vblog.activities_list.noActivityToDisplay"/>当前无动态信息可显示！</p>
		</c:when>
		<c:otherwise>	
		<ul class="activities-list">
			<c:forEach var="activity" items="${page.result}" varStatus="status">
			<li <c:if test="${status.last}">class="last"</c:if>>
				<a href="${base}/${activity.enteredBy.project.uniqueId}/profile">
				<c:choose>
					<c:when test="${not empty activity.enteredBy.project.profile.logo}">
					<img class="thumbnail" src="${base}/logo/download/${activity.enteredBy.project.profile.logo.id}/40x40"/>
					</c:when>
					<c:otherwise>
					<img class="thumbnail" src="${base}/themes/${theme.name}/stock/${activity.enteredBy.project.category.code}.png" width="40" height="40"/>
					</c:otherwise>
				</c:choose>
				</a>
				<div class="activity-body">
					<div class="activity-content">
						<c:choose>
							<c:when test="${activity.type eq 'site-chat' or activity.type eq 'user-input'}">
								<e2:wikiRender text="${activity.description}"/>
							</c:when>
							<c:otherwise>${activity.description}</c:otherwise>
						</c:choose>
						<div class="float-right">
							<e2:prettyTime date="${activity.entered}"/>
							<a class="activityCommentAction" href="#${activity.id}">
								<fmt:message key="vblog.activities_list.comment"><fmt:param value="${activity.commentCount}"/></fmt:message>
							</a>
						</div>
						<div class="clear"></div>
					</div>
					<e2:security code="vblog-comment-view">
					<div id="commentContainer${activity.id}">
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
					</e2:security>
					<e2:security code="vblog-comment-add" userRequired="true">
					<form id="activityCommentForm${activity.id}" class="activityCommentForm" style="display: none;" action="${base}/process/commons/comment" method="post">
						<div>
							<textarea name="content"></textarea>
						</div>
						<div class="last">
							<!-- 
							<span class="float-right"><fmt:message key="vblog.activities_list.maxCharacters"/></span>
							-->
							<input class="button" type="submit" value='<fmt:message key="vblog.activities_list.submit"/>'>
							<input class="button cancel" type="button" value="<fmt:message key="vblog.activities_list.cancel"/>">
							<input type="hidden" name="linkedId" value="${activity.id}"/>
							<input type="hidden" name="enteredId" value="${user.id}"/>
							<input type="hidden" name="modifiedId" value="${user.id}"/>
							<input type="hidden" name="entity" value="Activity">
						</div>
					</form>
					</e2:security>					
				</div>
			</li>
			</c:forEach>
		</ul>
		<div style="text-align: center">
			<a href="#" class="more">更多</a>
		</div>
		</c:otherwise>
	</c:choose>
	</div>
</div>

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