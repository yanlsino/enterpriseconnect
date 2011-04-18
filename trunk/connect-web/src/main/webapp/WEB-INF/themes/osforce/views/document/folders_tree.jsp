<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<div class="fragment-head">
		<h3>${title}</h3>
	</div>	
	<div class="fragment-body">
		<div id="folders-tree${id}" class="tree"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#folder-tree${id}").zTree({
		isSimpleData: true,
		treeNodeKey: "id",
		treeNodeParentKey: "pId"
	}, ${folderTree});
});
</script>