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
				<button type="submit" class="button">提交</button>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<form:hidden path="projectId"/>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#album-form${id}').ajaxForm({
		dataType: 'json',
		beforeSubmit: function(formData, $form){
			var name = $.trim(formData[0].value);
			if(name=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(album){
			setTimeout(function(){
				window.location.href='?albumId=' + album.id;
			}, 500);
		}
	});
});
</script>