<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css">
.slideshow { height: 182px; width: 182px; margin: 0 5px; float: left }
.slideshow img { padding: 15px; border: 1px solid #ccc; background-color: #eee; }
</style>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
	<c:choose>
		<c:when test="${empty page.result}">当前无相册可显示！</c:when>
		<c:otherwise>
		<div class="albums-list">
			<c:forEach var="album" items="${page.result}" varStatus="status">
			<div class="slideshow">
			<c:choose>
				<c:when test="${not empty album.photos}">
					<c:forEach var="photo" items="${album.photos}">
					<a href="${base}/${project.uniqueId}/gallery/photos?albumId=${album.id}" title="${photo.name}">
						<img src="${base}/photo/download/${photo.realFile.id}/150x150"/>
					</a>	
					</c:forEach>
				</c:when>
				<c:otherwise>
					<a href='<e2:security code="gallery-photo-add">${base}/${project.uniqueId}/gallery/photo/upload?albumId=${album.id}</e2:security>' title="${album.name}">
						<img src="${base}/static/images/nophoto.jpg" width="150" height="150"/>
					</a>
				</c:otherwise>
			</c:choose>
			</div>
			</c:forEach>
		</div>
		</c:otherwise>
	</c:choose>
		<div class="clear"></div>
	</div>
</div>

<script type="text/javascript">
YUI().use('gallery-yui-slideshow', function(Y){
	Y.all('.slideshow').each(function(){
		var slideshow = new Y.Slideshow(this);
	});
});
</script>