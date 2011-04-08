<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

<c:set var="id" value="${fragmentConfig.id}"/>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<c:choose>
			<c:when test="${empty fileItem.folder}">
			<e2:security code="document-folder-add" userRequired="true">
			<div class="notice">
				当前无上传目录，请先添加...
				<a href="${base}/${project.uniqueId}/document/folder/form">添加目录</a>
			</div>
			</e2:security>
			</c:when>
			<c:otherwise>
			<div>
				<label>上传目录</label>
				<br/>
				<input id="folder${id}" class="text" value="${fileItem.folder.name}" readonly="readonly"/>
				<input id="folderId${id}" type="hidden" value="${fileItem.folderId}"/>
				<a href="#" id="selectAction${id}">选择</a>
			</div>
			<div>
				<div id="uploader"></div>
			</div>
			</c:otherwise>
		</c:choose>
		
		<div id="folder-tree${id}"></div>
	</div>
</div>
<link rel="stylesheet" href="${base}/components/plupload/examples/css/plupload.queue.css" type="text/css">
<script type="text/javascript" src="${base}/components/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${base}/components/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${base}/components/plupload/js/jquery.plupload.queue.min.js"></script>
<script type="text/javascript">
YUI().use('yui2-treeview', 'overlay', function(Y){
	var YAHOO = Y.YUI2;
	//
	var overlay = new Y.Overlay({
        srcNode:"#folder-tree${id}",
        visible:false,
        width:"20em",
        align: {
    		node: '#selectAction${id}',
    		points: [Y.WidgetPositionAlign.TL, Y.WidgetPositionAlign.TR]
    	}
    });
	overlay.render();
	//
	Y.one('#selectAction${id}').on('click', function(e){
		overlay.show();
		e.halt();
	});
	//
	var tree = new YAHOO.widget.TreeView("folder-tree${id}", ${folderTree});
	tree.subscribe("labelClick", function(node) {
		Y.one('#folder${id}').set('value', node.label);
		Y.one('#folderId${id}').set('value', node.data.id);
		overlay.hide();
    });
	tree.render();
});
</script>

<script type="text/javascript">
$(document).ready(function(){
	$("#uploader").pluploadQueue({
	  // General settings
		runtimes : 'html4',
	  	url : '${base}/process/commons/attachment',
		preinit: {
			UploadFile: function(up, file) {
				up.settings.multipart_params = {
					'forward' : '/document/file', 
					'folderId' : $('#folderId${id}').val(),
					'enteredId': '${fileItem.enteredId}',
					'modifiedId': '${fileItem.modifiedId}'
				};
			}
		}
	});
});
</script>