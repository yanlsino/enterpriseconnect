<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
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
		<c:choose>
			<c:when test="${empty fileItem.folder}">
			<u:security code="document-folder-add" project="${project}" userRequired="true">
			<div class="notice">
				当前无上传目录，请先添加...
				<a href="${base}/${project.uniqueId}/document/folder/form">添加目录</a>
			</div>
			</u:security>
			</c:when>
			<c:otherwise>
			<form:form id="file-form${id}" action="${base}/process/commons/attachment" 
				cssClass="file-form" commandName="fileItem">
				<div>
					<label>上传目录</label>
					<br/>
					<input id="folder${id}" class="text" value="${fileItem.folder.name}" readonly="readonly"/>
					<input id="folderId${id}" type="hidden" name="folderId" value="${fileItem.folderId}"/>
					<a href="#" id="selectAction${id}">选择</a>
				</div>
				<div>
					<input type="file" id="select-file${id}" name="file" />
				</div>
				<div id="file-queue${id}" class="file-queue"></div>
				<div>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<input type="hidden" name="forward" value="/document/file"/>
				</div>
			</form:form>
			</c:otherwise>
		</c:choose>
		<div id="folder-tree${id}" class="tree" style="display: none"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#select-file${id}').change(function(){
		$('#file-form${id}').ajaxSubmit({
			dataType: 'json',
			beforeSubmit: function(formData, $form){
				var folderId = $.trim(formData[0].value);
				if(folderId=='') {
					return false;
				}
				$form.find('#select-file${id}').busy({
					img: '${base}/static/images/loading.gif'
				});
			},
			success: function(fileItem){
				setTimeout(function(){
					window.location.href='?folderId=' + $('#folderId${id}').val();
				}, 500);
			}
		});
	});
});
</script>

<script type="text/javascript">
$(document).ready(function(){
	$("#folder-tree${id}").zTree({
		isSimpleData: true,
		treeNodeKey: "id",
		treeNodeParentKey: "pId",
		showLine: true,
		callback: {
			beforeCollapse: function(){return false;},
			click: function(event, id, node){
				$('#folder${id}').val(node.name);
				$('#folderId${id}').val(node.id);
			}
		}
	}, ${folderTree});

	$('#selectAction${id}').aqLayer({
        margin: '0',
        object: '#folder-tree${id}',
        onLoad: function() {}
    });
});
</script>