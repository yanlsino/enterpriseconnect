<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>
	</c:if>
	<div class="body">
		<c:if test="${not empty photo}">
		<div id="photo${id}" class="photo">
			<img class="thumbnail" src="${base}/photo/download/${photo.realFile.id}/500x500" id="${photo.id}"/>
		</div>
		<div class="navgator-bar">
			<span><button id="previous${id}" href="#">上一张</button></span>
			<span><button id="next${id}" href="#">下一张</button></span>
			<br class="clear"/>
		</div>
		</c:if>
		<div class="photos-list">
		<c:forEach var="photo" items="${page.result}">
			<a id="${photo.id}" href="${base}/photo/download/${photo.realFile.id}/500x500" class="<c:if test="${param.photoId eq photo.id}">current</c:if>">
				<img class="thumbnail" src="${base}/photo/download/${photo.realFile.id}/100x100"/>
			</a>
		</c:forEach>
		</div>
		<div class="clear"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	//
	$('.photos-list a').click(function(){
		changeImage(this);
		return false;
	});
	$('#previous${id}').click(function(){
		var current = $('.photos-list a.current');
		if(current.size()==0) {
			current = $('.photos-list a:first');
		}
		changeImage(current.prev());
		return false;
	});
	$('#next${id}').click(function(){
		var current = $('.photos-list a.current');
		if(current.size()==0) {
			current = $('.photos-list a:first');
		}
		changeImage(current.next());
		return false;
	});

	function changeImage(node) {
		$('.photos-list a').removeClass('current');
		var id = $(node).attr('id');
		$(node).addClass('current');
		var imgSrc = $(node).attr('href');
		$('#photo${id} img').attr({'src':imgSrc, 'id': id});
	}
});
</script>