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
		<form:form id="albumForm" action="${base}/process/gallery/album" commandName="album">
			<div>
				<label for="name" class="title">相册名</label>
				<br/>
				<form:input path="name" cssClass="text {validate:{required:true, messages:{required:'相册名不能为空！'}}}"/>
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
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#albumForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(album){
					setTimeout('window.location.href="?albumId='+album.id+'"', 500);
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>