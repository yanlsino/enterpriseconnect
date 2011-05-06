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
		<div id="folder-tree${id}" class="tree"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#folder-tree${id}').zTree({
		isSimpleData: true,
		treeNodeKey: 'id',
		treeNodeParentKey: 'pId',
		callback: {
			beforeCollapse: function(){return false;},
			click: function(event, id, node){
				window.location.href='${base}/${project.uniqueId}/document/folder/' + node.id;
			}
		}
	}, ${folderTree});
});
</script>