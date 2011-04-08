<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
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
			<a id="${photo.id}" href="${base}/photo/download/${photo.realFile.id}/500x500" class="loadImage">
				<img class="thumbnail" src="${base}/photo/download/${photo.realFile.id}/100x100"/>
			</a>
		</c:forEach>
		</div>
		<div class="clear"></div>
	</div>
</div>

<script type="text/javascript">
YUI().use('node', function(Y){
	Y.all('.loadImage').on('click', function(e){
		changeImage(e.currentTarget);
		e.halt();
	});
	Y.one('#previous${id}').on('click', function(e){
		var id = Y.one('#photo${id} img').get('id');
		preOrNextImage(id, 'pre');
		e.halt();
	});
	Y.one('#next${id}').on('click', function(e){
		var id = Y.one('#photo${id} img').get('id');
		preOrNextImage(id, 'next');
		e.halt();
	});
	function preOrNextImage(id, mode) {
		var imgLinks = Y.all('.photos-list a');
		var pre = null, current = null, next = null;
		for(i=0; i<imgLinks.size(); i++) {
			if(i>0) {
				pre = imgLinks.item(i-1);
			}
			var current = imgLinks.item(i);
			if(i<imgLinks.size()-1) {
				next = imgLinks.item(i+1);
			}
			if(current.get('id') == id) {
				break;
			}
		}
		if(pre!=null && mode=='pre') {
			changeImage(pre);
		}
		if(next!=null && mode=='next') {
			changeImage(next);
		}
	}
	
	function changeImage(node) {
		var id = node.get('id');
		var imgSrc = node.get('href');
		Y.one('#photo${id} img').set('src', imgSrc).set('id', id);
	}
})
</script>