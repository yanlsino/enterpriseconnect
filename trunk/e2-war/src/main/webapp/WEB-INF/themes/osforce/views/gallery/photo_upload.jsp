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
			<div>
				<label>目标相册</label>
				<select id="albumId" name="albumId">
				<c:forEach var="album" items="${albums}">
					<option value="${album.id}" <c:if test="${param.albumId eq album.id}">selected="selected""</c:if>>${album.name}</option>
				</c:forEach>	
				</select>
			</div>
			<div id="uploader"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#uploader").pluploadQueue({
		// General settings
		runtimes : 'html4',
		url : '${base}/process/commons/attachment',
		//max_file_size : '10mb',
		//chunk_size : '1mb',
		//unique_names : true,
		// Resize images on clientside if we can
		//resize : {width : 320, height : 240, quality : 90},
		// Specify what files to browse for
		//filters : [
			//{title : "Image files", extensions : "jpg,gif,png"}
		//],
		// Flash settings
		//flash_swf_url : '${base}/components/plupload/js/plupload.flash.swf',
		preinit: {
			UploadFile: function(up, file) {
				var albumId = $('#albumId').val();
				up.settings.multipart_params = {
					'forward' : '/gallery/photo', 
					'albumId' : albumId,
					'enteredId': ${user.id},
					'modifiedId': ${user.id}
				};
			}
		}
	});
});
</script>