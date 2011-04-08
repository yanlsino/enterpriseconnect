<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<e2:security code="document-file-add">
		<div class="float-right">
			<a href="${base}/${project.uniqueId}/document/file/form?folderId=${folder.id}">上传文件</a>
		</div>
		</e2:security>
		<div class="path">
		<c:if test="${not empty folders}">
			路径:
			<c:forEach var="folder" items="${folders}">
			/<a id="${folder.id}" href="#" class="pathFolder">${folder.name}</a>
			</c:forEach>
		</c:if>
		</div>
		<c:choose>
			<c:when test="${empty fileItems}">
			当前目录无文档可显示!
			</c:when>
			<c:otherwise>
			<table width="100%">
			<thead>
				<tr>
					<th>选择(<a href="#">全选</a>)</th>
					<th>文档名</th>
					<th>上传日期</th>
					<th>修改日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="fileItem" items="${fileItems}" varStatus="status">
				<tr>
					<td><input type="checkbox"/></td>
					<td>${fileItem.realFile.fileName}</td>
					<td>${fileItem.entered}</td>
					<td>${fileItem.modified}</td>
					<td>
					<e2:security code="document-file-download">
						<a href="${base}/process/document/file?fileId=${fileItem.id}" target="_blank">下载</a>
					</e2:security>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
			</c:otherwise>
		</c:choose>
		
		<div class="clear"></div>	
	</div>
</div>


<script type="text/javascript">
$(document).ready(function() {
	$('.pathFolder').click(function(){
		window.location.href='?folderId='+$(this).attr('id');
	});
});
</script>