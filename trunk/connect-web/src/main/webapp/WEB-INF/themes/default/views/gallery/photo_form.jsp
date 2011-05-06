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
		<form:form id="photo-form${id}" cssClass="photo-form"
			action="${base}/process/commons/attachment" commandName="photo">
			<div>
				<label>目标相册</label>
				<br/>
				<form:select path="albumId" items="${albums}" itemLabel="name" itemValue="id"/>
			</div>
			<div>
				<input type="file" name="file" id="select-file${id}"/>
			</div>
			<div id="file-queue${id}" class="file-queue"></div>
			<div>
				<form:hidden path="enteredId"/>
				<form:hidden path="modifiedId"/>
				<input type="hidden" name="forward" value="/gallery/photo"/>				
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#select-file${id}').change(function(){
		$('#photo-form${id}').ajaxSubmit({
			dataType: 'json',
			beforeSubmit: function(formData, $form){
				$form.find('#select-file${id}').busy({
					img: '${base}/static/images/loading.gif'
				});
			},
			success: function(photo){
				setTimeout(function(){
					window.location.href='?albumId=' + photo.albumId;
				}, 500);
			}
		});
	});
});
</script>