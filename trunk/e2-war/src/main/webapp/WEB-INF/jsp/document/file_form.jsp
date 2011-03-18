<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<c:choose>
			<c:when test="${empty fileItem.folder}">
			<e2:security code="document-folder-add" userRequired="true">
			<p>
				当前无上传目录，请先添加...
				<a href="${base}/${project.uniqueId}/document/folder/form">添加目录</a>
			</p>
			</e2:security>
			</c:when>
			<c:otherwise>
			<div>
				<label>上传目录</label>
				<input id="folder" value="${fileItem.folder.name}" readonly="readonly"/>
				<input id="folderId" type="hidden" value="${fileItem.folderId}"/>
				<a href="#" onclick="javascript:showMenu();return false">选择</a>
			</div>
			<div id="dropdownMenuBackground" style="display:none; position:absolute; height:200px; min-width:150px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto;z-index: 9999">
				<ul id="dropdownMenu" class="tree"></ul>
			</div>
			<div id="uploader"></div>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#uploader").pluploadQueue({
	  // General settings
	  runtimes : 'flash',
	  url : '${base}/process/commons/attachment',
	  max_file_size : '10mb',
	  chunk_size : '1mb',
	  unique_names : true,
	 // Resize images on clientside if we can
	  resize : {width : 320, height : 240, quality : 90},
	 // Specify what files to browse for
	 filters : [
	 	{title : "Image files", extensions : "jpg,gif,png"},
	 	{title : "Zip files", extensions : "zip"}
	 ],
	 // Flash settings
		flash_swf_url : '${base}/components/plupload/js/plupload.flash.swf',
		preinit: {
			UploadFile: function(up, file) {
				var folderId = $('#folderId').val();
				up.settings.multipart_params = {
					'forward' : '/document/file', 
					'folderId' : folderId,
					'enteredId': ${fileItem.enteredId},
					'modifiedId': ${fileItem.modifiedId}
				};
			}
		}
	});
	$('#${fragmentConfig.id} #dropdownMenu').zTree({
		async: true,
		asyncUrl: "${base}/process/document/folders",  //获取节点数据的URL地址
		asyncParam: ["id"],  //获取节点数据时，必须的数据名称，例如：id、name
		asyncParamOther: ["projectId", "${project.id}"],
		callback: {
			click: zTreeOnClick
		}

	});
});
function hideMenu() {
	$("#dropdownMenuBackground").fadeOut("fast");
}
function showMenu() {
	$("#dropdownMenuBackground").slideDown("fast");
}
function zTreeOnClick(event, treeId, treeNode) {
	if (treeNode) {
		$('#folderId').val(treeNode.id);
		$('#folder').val(treeNode.name);
		hideMenu();
	}
}
</script>
