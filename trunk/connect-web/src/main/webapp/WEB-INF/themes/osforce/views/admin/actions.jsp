<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://www.opensourceforce.org/e2/tags" prefix="e2" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="${fragmentConfig.id}" class="fragment mod-util">
	<div class="fragment-head">
		<h3>${fragmentConfig.title}</h3>
	</div>	
	<div class="fragment-body">
		<ul class="actions">
			<li><a href="${base}/${project.uniqueId}/admin/project/form">空间设置</a></li>
			<li class="last"><a href="${base}/${project.uniqueId}/admin/features/form">模块设置</a></li>
		</ul>	
	</div>
</div>