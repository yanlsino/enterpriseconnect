<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form id="attachmentForm" action="${base}/process/commons/attachment" enctype="multipart/form-data">
			<fieldset>
				<div>
					<label>上传路径</label>
					<input type="file" name="file" class="{validate:{required:true, messages:{required:'上传路径不能为空！'}}}"/>
				</div>
				<div>
					<input type="submit" value=" 上传 "/>
					<input type="hidden" name="profileId" value="${param.profileId}"/>
					<input type="hidden" name="forward" value="${param.forward}"/>
				</div>
			</fieldset>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#attachmentForm').validate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				success:function(attachmentId){
					window.location.reload();
				}
			});
			return false;
		},
		meta: "validate"
	});
});
</script>