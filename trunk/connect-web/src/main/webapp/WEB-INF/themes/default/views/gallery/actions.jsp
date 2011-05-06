<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<ul class="actions">
		<u:security code="gallery-album-add" project="${project}" userRequired="true">
			<li><a href="${base}/${project.uniqueId}/gallery/album/form">添加相册</a></li>
		</u:security>
		<u:security code="gallery-photo-add" project="${project}" userRequired="true">
			<li class="last"><a href="${base}/${project.uniqueId}/gallery/photo/form<c:if test="${not empty param.albumId}">?albumId=${param.albumId}</c:if>">上传相片</a></li>
		</u:security>
		</ul>
	</div>
</div>