<%@page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:set var="id" value="${fragmentConfig.id}"/>
<c:set var="title" value="${fragmentConfig.title}"/>

<div id="${id}" class="fragment">
	<c:if test="${not empty title}">
	<div class="head">
		<h3>${title}</h3>
	</div>	
	</c:if>
	<div class="body">
		<table>
			<thead>
				<tr>
					<th>显示名</th>
					<th>编码</th>
					<th>排序值</th>
					<th>状态</th>
					<th>权限</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="feature" items="${features}" varStatus="status">
				<tr>
				<c:set var="feature" value="${feature}" scope="request"/>
				<form:form id="feature-form${status.count}" cssClass="feature-form" 
					action="${base}/process/admin/feature" commandName="feature">
					<td>
						<form:input path="label" cssClass="{validate:{required:true, messages:{required:'显示名不能为空！'}}}"/>
					</td>
					<td>
						<form:input path="code" readonly="true"/>
					</td>
					<td>
						<form:input path="level" size="2"/>
					</td>
					<td>
						<form:checkbox path="show"/>
					</td>
					<td>
						<form:select path="roleId" items="${roles}" itemLabel="name" itemValue="id"/>
						<form:hidden path="id"/>
						<form:hidden path="projectId"/>
					</td>
				</form:form>
				</tr>
				</c:forEach>
			</tbody>		
			<tfoot>
				<tr>
					<td>
					<button id="submit${id}" class="button">
						提交
					</button>
					</td>
				</tr>
			</tfoot>
		</table>
		<div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#submit${id}').click(function(){
		$('.feature-form').each(function(){
			$('.feature-form').ajaxSubmit({
				forceSync: true,
				dataType: 'json',
				beforeSubmit: function(formData){
					var label = $.trim(formData[0].value);
					var code = $.trim(formData[1].value);
					if(label=='' || code=='') {
						return false;
					}
				}
			});
			window.location.reload();
		});
	});
});
</script>