<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<form:form id="folderForm" action="${base}/process/document/folder" commandName="folder">
			<fieldset>
				<div>
					<label>目录名</label>
					<form:input path="name" cssClass="{validate:{required:true, messages:{required:'目录名不能为空！'}}}"/>
				</div>
				<div>
					<label>根目录</label>
					<form:checkbox id="checkRoot" path="root"/>
				</div>
				<div id="folderChooser">
					<label>上一级目录</label>
					<input id="parentFolder" value="${folder.parent.name}" readonly="readonly">
					<form:hidden path="parentId"/>
					<a href="#" onclick="javascript:showMenu();return false">选择</a>
				</div>
				<div id="dropdownMenuBackground" style="display:none; position:absolute; height:200px; min-width:150px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto;">
					<ul id="dropdownMenu" class="tree"></ul>
				</div>
				<div>
					<input type="submit" value=" 提交 ">
					<form:hidden path="enteredId"/>
					<form:hidden path="modifiedId"/>
					<form:hidden path="projectId"/>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#folderForm').validate({
		submitHandler: function(form) {
			$('#${fragmentConfig.id}').block({ 
				message: '正在处理...',
				overlayCSS: { backgroundColor: '#EEE' }
			});
			$(form).ajaxSubmit({
				dataType:'json',
				success:function(folder){
					setTimeout('window.location.href="?folderId='+folder.id+'"', 500);
				}
			});
			return false;
		},
		meta: "validate"
	});
	$('#${fragmentConfig.id} #dropdownMenu').zTree({
		async: true,
		asyncUrl: "${base}/process/document/folders",  //获取节点数据的URL地址
		asyncParam: ["id"],  //获取节点数据时，必须的数据名称，例如：id、name
		asyncParamOther: ["projectId", "${project.id}", "tree", true],
		callback: {
			beforeExpand: function(){return false;},
			beforeCollapse: function(){return false;},
			click: zTreeOnClick
		}
	});
	$('#checkRoot').click(function(){
		$('#folderChooser').toggle();
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
		$('#parentId').val(treeNode.id);
		$('#parentFolder').val(treeNode.name);
		hideMenu();
	}
}
</script>