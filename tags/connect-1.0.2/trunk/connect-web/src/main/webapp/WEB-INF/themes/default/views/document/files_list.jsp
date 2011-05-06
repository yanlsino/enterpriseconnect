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
		<u:security code="document-file-add">
		<a class="top right" href="${base}/${project.uniqueId}/document/file/form?folderId=${folder.id}">上传文件</a>
		</u:security>
		<p>
		<c:if test="${not empty folders}">
			路径:
			<c:forEach var="folder" items="${folders}">
			/<a id="${folder.id}" href="#" class="pathFolder">${folder.name}</a>
			</c:forEach>
		</c:if>
		</p>
		<c:choose>
			<c:when test="${empty fileItems}">
			<div class="notice">当前目录无文档可显示!</div>
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
					<u:security code="document-file-download">
						<a href="${base}/process/document/file?fileId=${fileItem.id}" target="_blank">下载</a>
					</u:security>
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