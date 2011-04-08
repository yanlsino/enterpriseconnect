<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div id="${fragmentConfig.id}" class="moduleblock">
	<div class="fragment mod-util">
		<div class="fragment-head">
			<h3>${fragmentConfig.title}
			</h3>
		</div>
		<div class="fragment-body">
			<form:form id="albumForm" action="${base}/process/gallery/album" commandName="album">
				<div>
					<label>相册名</label>
					<form:input path="name" cssClass="{validate:{required:true, messages:{required:'相册名不能为空！'}}}"/>
				</div>
				<div>
					<input type="submit" value=" 提交 ">
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