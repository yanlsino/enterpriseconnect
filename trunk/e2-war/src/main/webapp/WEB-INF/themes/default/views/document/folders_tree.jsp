<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<div id="folder-tree${id}"></div>
	</div>
</div>

<script type="text/javascript">
YUI().use('yui2-treeview', function(Y) {
	var YAHOO = Y.YUI2;	
	//
	var tree = new YAHOO.widget.TreeView("folder-tree${id}", ${folderTree});
	tree.subscribe("labelClick", function(node) {
		window.location.href='${base}/${project.uniqueId}/document?folderId='+node.data.id;
    });
	tree.render();
});
</script>

<%-- 
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
</script>
--%>