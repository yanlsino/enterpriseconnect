<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form action="${base}/process/commons/attachment" method="post" enctype="multipart/form-data">
			<div>
				<label>目标相册</label>
				<br/>
				<select id="albumId" name="albumId">
				<c:forEach var="album" items="${albums}">
					<option value="${album.id}" <c:if test="${param.albumId eq album.id}">selected="selected""</c:if>>${album.name}</option>
				</c:forEach>	
				</select>
			</div>
			<div>
				<input type="file" id="select-file${id}"/>
			</div>
			<div id="file-queue${id}" class="file-queue">
				
			</div>
			<div>
				<button type="submit">上传</button>
				<input type="hidden" name="forward" value="/gallery/photo">
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
YUI().use('node', function(Y){
	var fileQueue = Y.one('#file-queue${id}');
	Y.one('#select-file${id}').on('change', function(e){
		var path = e.currentTarget.get('value');
		var html = '<input type="file" name="file" value="'+ path +'">';
		fileQueue.insert(html);
	});
});
</script>