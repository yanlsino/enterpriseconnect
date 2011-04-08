<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="actions">
		<e2:security code="document-file-add" userRequired="true">
			<li>
				<a href="${base}/${project.uniqueId}/document/file/form?folderId=${param.folderId}">上传文档</a>
			</li>
		</e2:security>
		<e2:security code="document-folder-add" userRequired="true">
			<li class="last">
				<a href="${base}/${project.uniqueId}/document/folder/form">添加目录</a>
			</li>
		</e2:security>
		</ul>
	</div>
</div>