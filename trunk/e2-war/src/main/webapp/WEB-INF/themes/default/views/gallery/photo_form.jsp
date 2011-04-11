<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form id="photo-form${id}" action="${base}/process/commons/attachment" method="post" enctype="multipart/form-data">
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
				<input type="file" name="file" id="select-file${id}"/>
			</div>
			<div id="file-queue${id}" class="file-queue">
				
			</div>
			<div>
				<input type="hidden" name="forward" value="/gallery/photo"/>
				<input type="hidden" name="enteredId" value="${user.id}"/>
				<input type="hidden" name="modifiedId" value="${user.id}"/>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-upload-iframe', 'json', function(Y){
	var fileQueue = Y.one('#file-queue${id}');
	Y.one('#select-file${id}').on('change', function(e){
		var path = e.currentTarget.get('value');
		//
		var photoForm = Y.one('#photo-form${id}');
		Y.on('io:complete', function(id, o){
			var photo = Y.JSON.parse(o.responseText);	
			var html = '<div><span>'+ path +'<span><a class="removeAction"></a></div>';
			Y.one('#file-queue${id}').insert(html);
			bindEvent();
			e.currentTarget.set('value', '');
		});
		Y.io(photoForm.get('action'), {
			method: 'POST',
			form: {
				id: photoForm,
				upload: true
			}
		});
		e.halt();
	});
	function bindEvent() {
		Y.all('.removeAction').on('click', function(e){
			var parent = e.currentTarget.get('parentNode');
			parent.remove();
			e.halt();
		});
	}
});
</script>