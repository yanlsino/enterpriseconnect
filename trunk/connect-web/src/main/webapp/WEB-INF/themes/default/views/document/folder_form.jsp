<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<form:form id="folder-form${id}" action="${base}/process/document/folder" 
			commandName="folder" cssClass="folder-form${id}">
				<div>
					<label for="name" class="title">目录名</label>
					<br/>
					<form:input path="name" cssClass="text {validate:{required:true, messages:{required:'目录名不能为空！'}}}"/>
				</div>
				<div>
					<label for="root" class="title">根目录</label>
					<br/>
					<form:checkbox id="checkRoot${id}" path="root"/>
				</div>
				<div id="folderChooser${id}">
					<label for="parent" class="title">上一级目录</label>
					<br/>
					<input id="parentFolder${id}" class="text" value="${folder.parent.name}" readonly="readonly">
					<form:hidden path="parentId" id="parentId${id}"/>
					<a href="#" id="selectAction${id}">选择</a>
				</div>
				<div>
					<button type="submit" class="button">提交</button>
					<form:hidden path="id"/>
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="projectId"/>
				</div>
		</form:form>
		
		<div id="folder-tree${id}" class="tree"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#checkRoot${id}').click(function(){
		$('#folderChooser${id}').toggle();
	});
	$('#folder-form${id}').ajaxForm({
		beforeSubmit: function(formData, $form){
			var name = $.trim(formData[0].value);
			if(name=='') {
				return false;
			}
			$form.find('.button').busy({
				img: '${base}/static/images/loading.gif'
			});
		},
		success: function(folder){
			setTimeout(function(){
				window.location.href='?folderId=' + folder.id; 
			}, 500);
		}
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
				$('#parentFolder${id}').val(node.name);
				$('#parentId${id}').val(node.id);
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