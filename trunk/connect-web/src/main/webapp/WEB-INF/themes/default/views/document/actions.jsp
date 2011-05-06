<%@page pageEncoding="UTF-8" %>
<%@taglib uri="/META-INF/utils.tld" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment">
	<c:if test="${not empty fragmentConfig.title}">
	<div class="head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<ul class="actions">
		<u:security code="document-file-add" project="${project}" userRequired="true">
			<li>
				<a href="${base}/${project.uniqueId}/document/file/form?folderId=${param.folderId}">上传文档</a>
			</li>
		</u:security>
		<u:security code="document-folder-add" project="${project}" userRequired="true">
			<li class="last">
				<a href="${base}/${project.uniqueId}/document/folder/form">添加目录</a>
			</li>
		</u:security>
		</ul>
	</div>
</div>