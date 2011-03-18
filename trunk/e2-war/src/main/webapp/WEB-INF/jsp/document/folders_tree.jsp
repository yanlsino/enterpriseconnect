<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<div id="folders-tree" class="tree">
		
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#${fragmentConfig.id} #folders-tree').zTree({
		async: true,
		asyncUrl: "${base}/process/document/folders",  //获取节点数据的URL地址
		asyncParam: ["id"],  //获取节点数据时，必须的数据名称，例如：id、name
		asyncParamOther: ["projectId", "${project.id}"],
		callback: {
			beforeExpand: function(){return true;},
			beforeCollapse: function(){return true;},
			click: function(event, treeId, treeNode) {
				if (treeNode) {
					window.location.href='${base}/${project.uniqueId}/document?folderId='+treeNode.id;
				}
			}		
		}
	});
});
</script
>