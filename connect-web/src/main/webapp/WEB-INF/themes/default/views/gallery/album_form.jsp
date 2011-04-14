<%@page pageEncoding="UTF-8" %>
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
		<form:form id="album-form${id}" cssClass="album-form" 
			action="${base}/process/gallery/album" commandName="album">
			<div>
				<label for="name" class="title">相册名</label>
				<br/>
				<form:input path="name" cssClass="text"/>
			</div>
			<div>
				<button type="submit" class="button">
					<span id="status1${id}">
						提交
					</span>
					<span id="status2${id}" style="display: none">
						<img src="${base}/static/images/loading.gif"/>正在处理...
					</span>
				</button>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="projectId"/>
			</div>
		</form:form>
		</div>
	</div>
</div>

<script type="text/javascript">
YUI().use('io-form', 'json', function(Y){
	var albumForm = Y.one('#album-form${id}');
	albumForm.on('submit', function(e){
		Y.one('#status1${id}').hide();
		Y.one('#status2${id}').show();
		//
		Y.io.header('Content-Type', 'application/json');
		Y.on('io:complete', function(id, o){
			try {
				var album = Y.JSON.parse(o.responseText);
				setTimeout('window.location.href="?albumId='+album.id+'"', 500);
			} catch(e) {
				// TODO alert message username or password invalid
			}
		});
		Y.io(albumForm.get('action'), {
			method: 'POST',
			form: {
				id: albumForm
			}
		});
		e.halt();
	});
});
</script>